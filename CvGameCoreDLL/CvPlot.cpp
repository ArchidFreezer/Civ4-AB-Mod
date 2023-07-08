// plot.cpp

#include "CvGameCoreDLL.h"
#include "CvPlot.h"
#include "CvCity.h"
#include "CvUnit.h"
#include "CvGlobals.h"
#include "CvArea.h"
#include "CvGameAI.h"
#include "CvDLLInterfaceIFaceBase.h"
#include "CvDLLSymbolIFaceBase.h"
#include "CvDLLEntityIFaceBase.h"
#include "CvDLLPlotBuilderIFaceBase.h"
#include "CvDLLEngineIFaceBase.h"
#include "CvDLLFlagEntityIFaceBase.h"
#include "CvMap.h"
#include "CvPlayerAI.h"
#include "CvTeamAI.h"
#include "CvGameCoreUtils.h"
#include "CvRandom.h"
#include "CvDLLFAStarIFaceBase.h"
#include "CvInfos.h"
#include "FProfiler.h"
#include "CvArtFileMgr.h"
#include "CyArgsList.h"
#include "CvDLLPythonIFaceBase.h"
#include "CvEventReporter.h"
#include "CvPopupInfo.h"
#include "FAStarNode.h"
#include <vector>
#include <map>

#define STANDARD_MINIMAP_ALPHA		(0.6f)


// Public Functions...

CvPlot::CvPlot() {
	m_aiYield = new short[NUM_YIELD_TYPES];

	// Plot danger cache
	m_abBorderDangerCache = new bool[MAX_TEAMS];

	m_aiCulture = NULL;
	m_aiFoundValue = NULL;
	m_aiPlayerCityRadiusCount = NULL;
	m_aiPlotGroup = NULL;
	m_aiVisibilityCount = NULL;
	m_aiStolenVisibilityCount = NULL;
	m_aiBlockadedCount = NULL;
	m_aiRevealedOwner = NULL;
	m_abRiverCrossing = NULL;
	m_abRevealed = NULL;
	m_aeRevealedImprovementType = NULL;
	m_aeRevealedRouteType = NULL;
	m_paiBuildProgress = NULL;
	m_apaiCultureRangeCities = NULL;
	m_apaiInvisibleVisibilityCount = NULL;

	m_pFeatureSymbol = NULL;
	m_pPlotBuilder = NULL;
	m_pRouteSymbol = NULL;
	m_pRiverSymbol = NULL;
	m_pFlagSymbol = NULL;
	m_pFlagSymbolOffset = NULL;
	m_pCenterUnit = NULL;

	m_szScriptData = NULL;

	reset(0, 0, true);
}


CvPlot::~CvPlot() {
	uninit();

	SAFE_DELETE_ARRAY(m_aiYield);

	// Plot danger cache
	SAFE_DELETE_ARRAY(m_abBorderDangerCache);

}

void CvPlot::init(int iX, int iY) {
	//--------------------------------
	// Init saved data
	reset(iX, iY);

	//--------------------------------
	// Init non-saved data

	//--------------------------------
	// Init other game data
}


void CvPlot::uninit() {
	SAFE_DELETE_ARRAY(m_szScriptData);

	gDLL->getFeatureIFace()->destroy(m_pFeatureSymbol);
	if (m_pPlotBuilder) {
		gDLL->getPlotBuilderIFace()->destroy(m_pPlotBuilder);
	}
	gDLL->getRouteIFace()->destroy(m_pRouteSymbol);
	gDLL->getRiverIFace()->destroy(m_pRiverSymbol);
	gDLL->getFlagEntityIFace()->destroy(m_pFlagSymbol);
	gDLL->getFlagEntityIFace()->destroy(m_pFlagSymbolOffset);
	m_pCenterUnit = NULL;

	deleteAllSymbols();

	SAFE_DELETE_ARRAY(m_aiCulture);
	SAFE_DELETE_ARRAY(m_aiFoundValue);
	SAFE_DELETE_ARRAY(m_aiPlayerCityRadiusCount);
	SAFE_DELETE_ARRAY(m_aiPlotGroup);

	SAFE_DELETE_ARRAY(m_aiVisibilityCount);
	SAFE_DELETE_ARRAY(m_aiStolenVisibilityCount);
	SAFE_DELETE_ARRAY(m_aiBlockadedCount);
	SAFE_DELETE_ARRAY(m_aiRevealedOwner);

	SAFE_DELETE_ARRAY(m_abRiverCrossing);
	SAFE_DELETE_ARRAY(m_abRevealed);

	SAFE_DELETE_ARRAY(m_aeRevealedImprovementType);
	SAFE_DELETE_ARRAY(m_aeRevealedRouteType);

	SAFE_DELETE_ARRAY(m_paiBuildProgress);

	if (NULL != m_apaiCultureRangeCities) {
		for (int iI = 0; iI < MAX_PLAYERS; ++iI) {
			SAFE_DELETE_ARRAY(m_apaiCultureRangeCities[iI]);
		}
		SAFE_DELETE_ARRAY(m_apaiCultureRangeCities);
	}

	if (NULL != m_apaiInvisibleVisibilityCount) {
		for (int iI = 0; iI < MAX_TEAMS; ++iI) {
			SAFE_DELETE_ARRAY(m_apaiInvisibleVisibilityCount[iI]);
		}
		SAFE_DELETE_ARRAY(m_apaiInvisibleVisibilityCount);
	}

	m_units.clear();
}

// FUNCTION: reset()
// Initializes data members that are serialized.
void CvPlot::reset(int iX, int iY, bool bConstructorCall) {
	//--------------------------------
	// Uninit class
	uninit();

	m_iX = iX;
	m_iY = iY;
	m_iArea = FFreeList::INVALID_INDEX;
	m_pPlotArea = NULL;
	m_iFeatureVariety = 0;
	m_iOwnershipDuration = 0;
	m_iImprovementDuration = 0;
	m_iUpgradeProgress = 0;
	m_iForceUnownedTimer = 0;
	m_iCityRadiusCount = 0;
	m_iRiverID = -1;
	m_iMinOriginalStartDist = -1;
	m_iReconCount = 0;
	m_iRiverCrossingCount = 0;

	m_bStartingPlot = false;
	m_bHills = false;
	m_bNOfRiver = false;
	m_bWOfRiver = false;
	m_bIrrigated = false;
	m_bPotentialCityWork = false;
	m_bShowCitySymbols = false;
	m_bFlagDirty = false;
	m_bPlotLayoutDirty = false;
	m_bLayoutStateWorked = false;
	m_bPeaks = false;

	m_eOwner = NO_PLAYER;
	m_ePlotType = PLOT_OCEAN;
	m_eTerrainType = NO_TERRAIN;
	m_eFeatureType = NO_FEATURE;
	m_eBonusType = NO_BONUS;
	m_eImprovementType = NO_IMPROVEMENT;
	m_eRouteType = NO_ROUTE;
	m_eRiverNSDirection = NO_CARDINALDIRECTION;
	m_eRiverWEDirection = NO_CARDINALDIRECTION;

	m_plotCity.reset();
	m_workingCity.reset();
	m_workingCityOverride.reset();

	for (int iI = 0; iI < NUM_YIELD_TYPES; ++iI) {
		m_aiYield[iI] = 0;
	}

	m_iActivePlayerSafeRangeCache = -1;

	for (int iI = 0; iI < MAX_TEAMS; iI++) {
		m_abBorderDangerCache[iI] = false;
	}
}


//////////////////////////////////////
// graphical only setup
//////////////////////////////////////
void CvPlot::setupGraphical() {
	PROFILE_FUNC();

	if (!GC.IsGraphicsInitialized()) {
		return;
	}

	updateSymbols();
	updateFeatureSymbol();
	updateRiverSymbol();
	updateMinimapColor();

	updateVisibility();
	updateCenterUnit(); // K-Mod (This is required now that CvMap::updateCenterUnit doesn't always update the whole map.)
}

void CvPlot::updateGraphicEra() {
	if (m_pRouteSymbol != NULL)
		gDLL->getRouteIFace()->updateGraphicEra(m_pRouteSymbol);

	if (m_pFlagSymbol != NULL)
		gDLL->getFlagEntityIFace()->updateGraphicEra(m_pFlagSymbol);
}

void CvPlot::erase() {
	CLinkList<IDInfo> oldUnits;
	oldUnits.clear();
	CLLNode<IDInfo>* pUnitNode = headUnitNode();
	while (pUnitNode != NULL) {
		oldUnits.insertAtEnd(pUnitNode->m_data);
		pUnitNode = nextUnitNode(pUnitNode);
	}

	pUnitNode = oldUnits.head();

	while (pUnitNode != NULL) {
		CvUnit* pLoopUnit = ::getUnit(pUnitNode->m_data);
		pUnitNode = oldUnits.next(pUnitNode);

		if (pLoopUnit != NULL) {
			pLoopUnit->kill(false);
		}
	}

	// kill cities
	CvCity* pCity = getPlotCity();
	if (pCity != NULL) {
		pCity->kill(false);
	}

	setBonusType(NO_BONUS);
	setImprovementType(NO_IMPROVEMENT);
	setRouteType(NO_ROUTE, false);
	setFeatureType(NO_FEATURE);

	// disable rivers
	setNOfRiver(false, NO_CARDINALDIRECTION);
	setWOfRiver(false, NO_CARDINALDIRECTION);
	setRiverID(-1);
}


float CvPlot::getPointX() const {
	return GC.getMapINLINE().plotXToPointX(getX_INLINE());
}


float CvPlot::getPointY() const {
	return GC.getMapINLINE().plotYToPointY(getY_INLINE());
}


NiPoint3 CvPlot::getPoint() const {
	NiPoint3 pt3Point;

	pt3Point.x = getPointX();
	pt3Point.y = getPointY();
	pt3Point.z = 0.0f;

	pt3Point.z = gDLL->getEngineIFace()->GetHeightmapZ(pt3Point);

	return pt3Point;
}


float CvPlot::getSymbolSize() const {
	if (isVisibleWorked()) {
		if (isShowCitySymbols()) {
			return 1.6f;
		} else {
			return 1.2f;
		}
	} else {
		if (isShowCitySymbols()) {
			return 1.2f;
		} else {
			return 0.8f;
		}
	}
}


float CvPlot::getSymbolOffsetX(int iOffset) const {
	return ((40.0f + (((float)iOffset) * 28.0f * getSymbolSize())) - (GC.getPLOT_SIZE() / 2.0f));
}


float CvPlot::getSymbolOffsetY(int iOffset) const {
	return (-(GC.getPLOT_SIZE() / 2.0f) + 50.0f);
}


TeamTypes CvPlot::getTeam() const {
	if (isOwned()) {
		return GET_PLAYER(getOwnerINLINE()).getTeam();
	} else {
		return NO_TEAM;
	}
}


void CvPlot::doTurn() {
	PROFILE_FUNC();

	if (getForceUnownedTimer() > 0) {
		changeForceUnownedTimer(-1);
	}

	if (isOwned()) {
		changeOwnershipDuration(1);
	}

	if (getImprovementType() != NO_IMPROVEMENT) {
		changeImprovementDuration(1);
	}

	doFeature();

	doCulture();

	doImprovementSpawn();

	verifyUnitValidPlot();

	doFortAttack();

	// XXX
#ifdef _DEBUG
	{
		CLLNode<IDInfo>* pUnitNode;
		CvUnit* pLoopUnit;

		pUnitNode = headUnitNode();

		while (pUnitNode != NULL) {
			pLoopUnit = ::getUnit(pUnitNode->m_data);
			pUnitNode = nextUnitNode(pUnitNode);

			FAssertMsg(pLoopUnit->atPlot(this), "pLoopUnit is expected to be at the current plot instance");
		}
	}
#endif
	// XXX
}


void CvPlot::doImprovement() {
	PROFILE_FUNC();

	FAssert(isBeingWorked() && isOwned());

	if (getImprovementType() != NO_IMPROVEMENT) {
		if (getBonusType() == NO_BONUS) {
			FAssertMsg((0 < GC.getNumBonusInfos()), "GC.getNumBonusInfos() is not greater than zero but an array is being allocated in CvPlot::doImprovement");
			for (int iI = 0; iI < GC.getNumBonusInfos(); ++iI) {
				if (GET_TEAM(getTeam()).isHasTech((TechTypes)(GC.getBonusInfo((BonusTypes)iI).getTechReveal()))) {
					int iOdds = GC.getImprovementInfo(getImprovementType()).getImprovementBonusDiscoverRand(iI);

					if (iOdds > 0) {
						iOdds *= GC.getGameSpeedInfo(GC.getGameINLINE().getGameSpeedType()).getVictoryDelayPercent();
						iOdds /= 100;

						if (GC.getGameINLINE().getSorenRandNum(iOdds, "Bonus Discovery") == 0) {
							setBonusType((BonusTypes)iI);

							CvCity* pCity = GC.getMapINLINE().findCity(getX_INLINE(), getY_INLINE(), getOwnerINLINE(), NO_TEAM, false);

							if (pCity != NULL) {
								CvWString szBuffer;
								szBuffer = gDLL->getText("TXT_KEY_MISC_DISCOVERED_NEW_RESOURCE", GC.getBonusInfo((BonusTypes)iI).getTextKeyWide(), pCity->getNameKey());
								gDLL->getInterfaceIFace()->addHumanMessage(getOwnerINLINE(), false, GC.getEVENT_MESSAGE_TIME(), szBuffer, "AS2D_DISCOVERBONUS", MESSAGE_TYPE_MINOR_EVENT, GC.getBonusInfo((BonusTypes)iI).getButton(), (ColorTypes)GC.getInfoTypeForString("COLOR_WHITE"), getX_INLINE(), getY_INLINE(), true, true);
							}

							break;
						}
					}
				}
			}
		}
	}

	doImprovementUpgrade();
}

void CvPlot::doImprovementUpgrade() {
	if (getImprovementType() != NO_IMPROVEMENT) {
		const CvImprovementInfo& kImprovement = GC.getImprovementInfo(getImprovementType());
		ImprovementTypes eImprovementUpdrade = (ImprovementTypes)kImprovement.getImprovementUpgrade();
		if (eImprovementUpdrade != NO_IMPROVEMENT) {
			TechTypes eUpgradeTech = (TechTypes)kImprovement.getUpgradeTech();
			if (eUpgradeTech != NO_TECH) {
				TeamTypes eTeam = getTeam();
				if (eTeam == NO_TEAM || !GET_TEAM(eTeam).isHasTech(eUpgradeTech)) {
					return;
				}
			}

			if (isBeingWorked() || GC.getImprovementInfo(eImprovementUpdrade).isOutsideBorders()) {
				changeUpgradeProgress(GET_PLAYER(getOwnerINLINE()).getImprovementUpgradeRate());

				if (getUpgradeProgress() >= GC.getGameINLINE().getImprovementUpgradeTime(getImprovementType())) {
					setImprovementType(eImprovementUpdrade);
				}
			}
		}
	}
}

void CvPlot::updateCulture(bool bBumpUnits, bool bUpdatePlotGroups) {
	if (!isCity()) {
		setOwner(calculateCulturalOwner(), bBumpUnits, bUpdatePlotGroups);
	}
}


void CvPlot::updateFog() {
	if (!GC.IsGraphicsInitialized()) {
		return;
	}

	FAssert(GC.getGameINLINE().getActiveTeam() != NO_TEAM);

	if (isRevealed(GC.getGameINLINE().getActiveTeam(), false)) {
		if (gDLL->getInterfaceIFace()->isBareMapMode()) {
			gDLL->getEngineIFace()->LightenVisibility(getFOWIndex());
		} else {
			int cityScreenFogEnabled = GC.getDefineINT("CITY_SCREEN_FOG_ENABLED");
			if (cityScreenFogEnabled && gDLL->getInterfaceIFace()->isCityScreenUp() && (gDLL->getInterfaceIFace()->getHeadSelectedCity() != getWorkingCity())) {
				gDLL->getEngineIFace()->DarkenVisibility(getFOWIndex());
			} else if (isActiveVisible(false)) {
				gDLL->getEngineIFace()->LightenVisibility(getFOWIndex());
			} else {
				gDLL->getEngineIFace()->DarkenVisibility(getFOWIndex());
			}
		}
	} else {
		gDLL->getEngineIFace()->BlackenVisibility(getFOWIndex());
	}
}


void CvPlot::updateVisibility() {
	PROFILE("CvPlot::updateVisibility");

	if (!GC.IsGraphicsInitialized()) {
		return;
	}

	setLayoutDirty(true);

	updateSymbolVisibility();
	updateFeatureSymbolVisibility();
	updateRouteSymbol();

	CvCity* pCity = getPlotCity();
	if (pCity != NULL) {
		pCity->updateVisibility();
	}
}


void CvPlot::updateSymbolDisplay() {
	if (!GC.IsGraphicsInitialized()) {
		return;
	}

	for (int iLoop = 0; iLoop < getNumSymbols(); iLoop++) {
		CvSymbol* pLoopSymbol = getSymbol(iLoop);

		if (pLoopSymbol != NULL) {
			if (isShowCitySymbols()) {
				gDLL->getSymbolIFace()->setAlpha(pLoopSymbol, (isVisibleWorked()) ? 1.0f : 0.8f);
			} else {
				gDLL->getSymbolIFace()->setAlpha(pLoopSymbol, (isVisibleWorked()) ? 0.8f : 0.6f);
			}
			gDLL->getSymbolIFace()->setScale(pLoopSymbol, getSymbolSize());
			gDLL->getSymbolIFace()->updatePosition(pLoopSymbol);
		}
	}
}


void CvPlot::updateSymbolVisibility() {
	if (!GC.IsGraphicsInitialized()) {
		return;
	}

	for (int iLoop = 0; iLoop < getNumSymbols(); iLoop++) {
		CvSymbol* pLoopSymbol = getSymbol(iLoop);

		if (pLoopSymbol != NULL) {
			if (isRevealed(GC.getGameINLINE().getActiveTeam(), true) &&
				(isShowCitySymbols() ||
					(gDLL->getInterfaceIFace()->isShowYields() && !(gDLL->getInterfaceIFace()->isCityScreenUp())))) {
				gDLL->getSymbolIFace()->Hide(pLoopSymbol, false);
			} else {
				gDLL->getSymbolIFace()->Hide(pLoopSymbol, true);
			}
		}
	}
}


void CvPlot::updateSymbols() {
	if (!GC.IsGraphicsInitialized()) {
		return;
	}

	deleteAllSymbols();

	int yieldAmounts[NUM_YIELD_TYPES];
	int maxYield = 0;
	for (int iYieldType = 0; iYieldType < NUM_YIELD_TYPES; iYieldType++) {
		int iYield = calculateYield(((YieldTypes)iYieldType), true);
		yieldAmounts[iYieldType] = iYield;
		if (iYield > maxYield) {
			maxYield = iYield;
		}
	}

	if (maxYield > 0) {
		int maxYieldStack = GC.getDefineINT("MAX_YIELD_STACK");
		int layers = maxYield / maxYieldStack + 1;

		CvSymbol* pSymbol = NULL;
		for (int i = 0; i < layers; i++) {
			pSymbol = addSymbol();
			for (int iYieldType = 0; iYieldType < NUM_YIELD_TYPES; iYieldType++) {
				int iYield = yieldAmounts[iYieldType] - (maxYieldStack * i);
				LIMIT_RANGE(0, iYield, maxYieldStack);
				if (yieldAmounts[iYieldType]) {
					gDLL->getSymbolIFace()->setTypeYield(pSymbol, iYieldType, iYield);
				}
			}
		}
		for (int i = 0; i < getNumSymbols(); i++) {
			SymbolTypes eSymbol = (SymbolTypes)0;
			pSymbol = getSymbol(i);
			gDLL->getSymbolIFace()->init(pSymbol, gDLL->getSymbolIFace()->getID(pSymbol), i, eSymbol, this);
		}
	}

	updateSymbolDisplay();
	updateSymbolVisibility();
}


void CvPlot::updateMinimapColor() {
	if (!GC.IsGraphicsInitialized()) {
		return;
	}

	gDLL->getInterfaceIFace()->setMinimapColor(MINIMAPMODE_TERRITORY, getX_INLINE(), getY_INLINE(), plotMinimapColor(), STANDARD_MINIMAP_ALPHA);
}


void CvPlot::updateCenterUnit() {
	if (!GC.IsGraphicsInitialized()) {
		return;
	}

	if (!isActiveVisible(true)) {
		setCenterUnit(NULL);
		return;
	}

	setCenterUnit(getSelectedUnit());

	if (getCenterUnit() == NULL) {
		setCenterUnit(getBestDefender(GC.getGameINLINE().getActivePlayer(), NO_PLAYER, NULL, false, false, true));
	}

	if (getCenterUnit() == NULL) {
		setCenterUnit(getBestDefender(GC.getGameINLINE().getActivePlayer()));
	}

	if (getCenterUnit() == NULL) {
		setCenterUnit(getBestDefender(NO_PLAYER, GC.getGameINLINE().getActivePlayer(), gDLL->getInterfaceIFace()->getHeadSelectedUnit()));
	}

	if (getCenterUnit() == NULL) {
		setCenterUnit(getBestDefender(NO_PLAYER, GC.getGameINLINE().getActivePlayer()));
	}
}


void CvPlot::verifyUnitValidPlot() {
	PROFILE_FUNC();

	std::vector<std::pair<PlayerTypes, int> > bumped_groups; // K-Mod

	std::vector<CvUnit*> aUnits;
	CLLNode<IDInfo>* pUnitNode = headUnitNode();
	while (pUnitNode != NULL) {
		CvUnit* pLoopUnit = ::getUnit(pUnitNode->m_data);
		pUnitNode = nextUnitNode(pUnitNode);
		if (NULL != pLoopUnit) {
			aUnits.push_back(pLoopUnit);
		}
	}

	std::vector<CvUnit*>::iterator it = aUnits.begin();
	while (it != aUnits.end()) {
		CvUnit* pLoopUnit = *it;
		bool bErased = false;

		if (pLoopUnit != NULL) {
			if (pLoopUnit->atPlot(this)) {
				if (!(pLoopUnit->isCargo())) {
					if (!(pLoopUnit->isCombat())) {
						if (!isValidDomainForLocation(*pLoopUnit) || !(pLoopUnit->canEnterArea(getTeam(), area()))) {
							if (!pLoopUnit->jumpToNearestValidPlot(true)) {
								bErased = true;
							} else
								bumped_groups.push_back(std::make_pair(pLoopUnit->getOwnerINLINE(), pLoopUnit->getGroupID()));
						}
					}
				}
			}
		}

		if (bErased) {
			it = aUnits.erase(it);
		} else {
			++it;
		}
	}

	if (isOwned()) {
		it = aUnits.begin();
		while (it != aUnits.end()) {
			CvUnit* pLoopUnit = *it;
			bool bErased = false;

			if (pLoopUnit != NULL) {
				if (pLoopUnit->atPlot(this)) {
					if (!(pLoopUnit->isCombat())) {
						if (pLoopUnit->getTeam() != getTeam() && (getTeam() == NO_TEAM || !GET_TEAM(getTeam()).isVassal(pLoopUnit->getTeam()))) {
							if (isVisibleEnemyUnit(pLoopUnit)) {
								if (!(pLoopUnit->isInvisible(getTeam(), false))) {
									if (!pLoopUnit->jumpToNearestValidPlot(true)) {
										bErased = true;
									} else
										bumped_groups.push_back(std::make_pair(pLoopUnit->getOwnerINLINE(), pLoopUnit->getGroupID()));
								}
							}
						}
					}
				}
			}

			if (bErased) {
				it = aUnits.erase(it);
			} else {
				++it;
			}
		}
	}

	// first remove duplicate group numbers
	std::sort(bumped_groups.begin(), bumped_groups.end());
	bumped_groups.erase(std::unique(bumped_groups.begin(), bumped_groups.end()), bumped_groups.end());
	// now divide the broken groups
	for (size_t i = 0; i < bumped_groups.size(); i++) {
		CvSelectionGroup* pLoopGroup = GET_PLAYER(bumped_groups[i].first).getSelectionGroup(bumped_groups[i].second);
		if (pLoopGroup) {
			pLoopGroup->regroupSeparatedUnits();
		}
	}
}

// Forces all units off the plot, onto a nearby plot
void CvPlot::forceBumpUnits() {
	// Note: this function is almost certainly not optimal.
	// I just took the code from another function and I don't want to mess it up.
	std::vector<CvUnit*> aUnits;
	CLLNode<IDInfo>* pUnitNode = headUnitNode();
	while (pUnitNode != NULL) {
		CvUnit* pLoopUnit = ::getUnit(pUnitNode->m_data);
		pUnitNode = nextUnitNode(pUnitNode);
		if (NULL != pLoopUnit) {
			aUnits.push_back(pLoopUnit);
		}
	}

	std::vector<CvUnit*>::iterator it = aUnits.begin();
	while (it != aUnits.end()) {
		CvUnit* pLoopUnit = *it;
		bool bErased = false;

		if (pLoopUnit != NULL) {
			if (pLoopUnit->atPlot(this)) {
				if (!(pLoopUnit->isCargo())) {
					if (!(pLoopUnit->isCombat())) {
						if (!pLoopUnit->jumpToNearestValidPlot(true, true)) {
							bErased = true;
						}
					}
				}
			}
		}

		if (bErased) {
			it = aUnits.erase(it);
		} else {
			++it;
		}
	}
}

// K-Mod. Added bBomb argument.
// bBomb signals that the explosion should damage units, buildings, and city population.
// (I've also tided up the code a little bit)
void CvPlot::nukeExplosion(int iRange, CvUnit* pNukeUnit, bool bBomb) {
	for (int iDX = -(iRange); iDX <= iRange; iDX++) {
		for (int iDY = -(iRange); iDY <= iRange; iDY++) {
			CvPlot* pLoopPlot = plotXY(getX_INLINE(), getY_INLINE(), iDX, iDY);

			if (pLoopPlot == NULL)
				continue;

			// if we remove roads, don't remove them on the city... XXX

			CvCity* pLoopCity = pLoopPlot->getPlotCity();

			if (pLoopCity == NULL) {
				if (!(pLoopPlot->isWater()) && !(pLoopPlot->isImpassable())) {
					if (NO_FEATURE == pLoopPlot->getFeatureType() || !GC.getFeatureInfo(pLoopPlot->getFeatureType()).isNukeImmune()) {
						if (GC.getGameINLINE().getSorenRandNum(100, "Nuke Fallout") < GC.getDefineINT("NUKE_FALLOUT_PROB")) {
							pLoopPlot->setImprovementType(NO_IMPROVEMENT);
							pLoopPlot->setFeatureType((FeatureTypes)(GC.getDefineINT("NUKE_FEATURE")));
						}
					}
				}
			}
			// K-Mod. If this is not a bomb, then we're finished with this plot.
			if (!bBomb)
				continue;

			CLinkList<IDInfo> oldUnits;

			CLLNode<IDInfo>* pUnitNode = pLoopPlot->headUnitNode();

			while (pUnitNode != NULL) {
				oldUnits.insertAtEnd(pUnitNode->m_data);
				pUnitNode = pLoopPlot->nextUnitNode(pUnitNode);
			}

			pUnitNode = oldUnits.head();

			while (pUnitNode != NULL) {
				CvUnit* pLoopUnit = ::getUnit(pUnitNode->m_data);
				pUnitNode = oldUnits.next(pUnitNode);

				if (pLoopUnit == NULL || pLoopUnit == pNukeUnit)
					continue;

				if (!pLoopUnit->isNukeImmune() && !pLoopUnit->isDelayedDeath()) {
					int iNukeDamage = (GC.getDefineINT("NUKE_UNIT_DAMAGE_BASE") + GC.getGameINLINE().getSorenRandNum(GC.getDefineINT("NUKE_UNIT_DAMAGE_RAND_1"), "Nuke Damage 1") + GC.getGameINLINE().getSorenRandNum(GC.getDefineINT("NUKE_UNIT_DAMAGE_RAND_2"), "Nuke Damage 2"));

					if (pLoopCity != NULL) {
						iNukeDamage *= std::max(0, (pLoopCity->getNukeModifier() + 100));
						iNukeDamage /= 100;
					}

					if (pLoopUnit->canFight() || pLoopUnit->airBaseCombatStr() > 0) {
						pLoopUnit->changeDamage(iNukeDamage, ((pNukeUnit != NULL) ? pNukeUnit->getOwnerINLINE() : NO_PLAYER));
					} else if (iNukeDamage >= GC.getDefineINT("NUKE_NON_COMBAT_DEATH_THRESHOLD")) {
						pLoopUnit->kill(false, ((pNukeUnit != NULL) ? pNukeUnit->getOwnerINLINE() : NO_PLAYER));
					}
				}
			}

			if (pLoopCity != NULL) {
				for (int iI = 0; iI < GC.getNumBuildingInfos(); ++iI) {
					if (pLoopCity->getNumRealBuilding((BuildingTypes)iI) > 0) {
						if (!(GC.getBuildingInfo((BuildingTypes)iI).isNukeImmune())) {
							if (GC.getGameINLINE().getSorenRandNum(100, "Building Nuked") < GC.getDefineINT("NUKE_BUILDING_DESTRUCTION_PROB")) {
								pLoopCity->setNumRealBuilding(((BuildingTypes)iI), pLoopCity->getNumRealBuilding((BuildingTypes)iI) - 1);
							}
						}
					}
				}

				int iNukedPopulation = ((pLoopCity->getPopulation() * (GC.getDefineINT("NUKE_POPULATION_DEATH_BASE") + GC.getGameINLINE().getSorenRandNum(GC.getDefineINT("NUKE_POPULATION_DEATH_RAND_1"), "Population Nuked 1") + GC.getGameINLINE().getSorenRandNum(GC.getDefineINT("NUKE_POPULATION_DEATH_RAND_2"), "Population Nuked 2"))) / 100);

				iNukedPopulation *= std::max(0, (pLoopCity->getNukeModifier() + 100));
				iNukedPopulation /= 100;

				pLoopCity->changePopulation(-(std::min((pLoopCity->getPopulation() - 1), iNukedPopulation)));
			}
		}
	}

	if (bBomb) // K-Mod
	{
		GC.getGameINLINE().changeNukesExploded(1);
		CvEventReporter::getInstance().nukeExplosion(this, pNukeUnit);
	}
}


bool CvPlot::isConnectedTo(const CvCity* pCity) const {
	FAssert(isOwned());
	return ((getPlotGroup(getOwnerINLINE()) == pCity->plotGroup(getOwnerINLINE())) || (getPlotGroup(pCity->getOwnerINLINE()) == pCity->plotGroup(pCity->getOwnerINLINE())));
}


bool CvPlot::isConnectedToCapital(PlayerTypes ePlayer) const {
	if (ePlayer == NO_PLAYER) {
		ePlayer = getOwnerINLINE();
	}

	if (ePlayer != NO_PLAYER) {
		CvCity* pCapitalCity = GET_PLAYER(ePlayer).getCapitalCity();

		if (pCapitalCity != NULL) {
			return isConnectedTo(pCapitalCity);
		}
	}

	return false;
}


int CvPlot::getPlotGroupConnectedBonus(PlayerTypes ePlayer, BonusTypes eBonus) const {
	FAssertMsg(ePlayer != NO_PLAYER, "Player is not assigned a valid value");
	FAssertMsg(eBonus != NO_BONUS, "Bonus is not assigned a valid value");

	CvPlotGroup* pPlotGroup = getPlotGroup(ePlayer);

	if (pPlotGroup != NULL) {
		return pPlotGroup->getNumBonuses(eBonus);
	} else {
		return 0;
	}
}


bool CvPlot::isPlotGroupConnectedBonus(PlayerTypes ePlayer, BonusTypes eBonus) const {
	return (getPlotGroupConnectedBonus(ePlayer, eBonus) > 0);
}


bool CvPlot::isAdjacentPlotGroupConnectedBonus(PlayerTypes ePlayer, BonusTypes eBonus) const {
	// K-Mod. Allow this plot to have whatever resources are available in the city working the plot.
	// (The purpose of this is to allow railroads to be built the 'oil' from Standard Ethonol.)
	CvCity* pCity = getWorkingCity();
	if (pCity && pCity->getOwnerINLINE() == ePlayer && pCity->hasBonus(eBonus))
		return true;

	for (int iI = 0; iI < NUM_DIRECTION_TYPES; ++iI) {
		CvPlot* pAdjacentPlot = plotDirection(getX_INLINE(), getY_INLINE(), ((DirectionTypes)iI));

		if (pAdjacentPlot != NULL) {
			if (pAdjacentPlot->isPlotGroupConnectedBonus(ePlayer, eBonus)) {
				return true;
			}
		}
	}

	return false;
}


void CvPlot::updatePlotGroupBonus(bool bAdd) {
	PROFILE_FUNC();

	if (!isOwned()) {
		return;
	}

	CvPlotGroup* pPlotGroup = getPlotGroup(getOwnerINLINE());

	if (pPlotGroup != NULL) {
		CvCity* pPlotCity = getPlotCity();

		if (pPlotCity != NULL) {
			for (int iI = 0; iI < GC.getNumBonusInfos(); ++iI) {
				if (!GET_TEAM(getTeam()).isBonusObsolete((BonusTypes)iI)) {
					pPlotGroup->changeNumBonuses(((BonusTypes)iI), (pPlotCity->getFreeBonus((BonusTypes)iI) * ((bAdd) ? 1 : -1)));
				}
			}

			if (pPlotCity->isCapital()) {
				for (int iI = 0; iI < GC.getNumBonusInfos(); ++iI) {
					pPlotGroup->changeNumBonuses(((BonusTypes)iI), (GET_PLAYER(getOwnerINLINE()).getBonusExport((BonusTypes)iI) * ((bAdd) ? -1 : 1)));
					pPlotGroup->changeNumBonuses(((BonusTypes)iI), (GET_PLAYER(getOwnerINLINE()).getBonusImport((BonusTypes)iI) * ((bAdd) ? 1 : -1)));
				}
			}
		}

		// K-Mod. I'm just trying to standardize the code to reduce the potential for mistakes. There are no functionality changes here.
		BonusTypes eBonus = getNonObsoleteBonusType(getTeam(), true);
		if (eBonus != NO_BONUS && pPlotGroup && isBonusNetwork(getTeam())) {
			pPlotGroup->changeNumBonuses(eBonus, bAdd ? 1 : -1);
		}
	}
}


bool CvPlot::isAdjacentToArea(int iAreaID) const {
	PROFILE_FUNC();

	for (int iI = 0; iI < NUM_DIRECTION_TYPES; ++iI) {
		CvPlot* pAdjacentPlot = plotDirection(getX_INLINE(), getY_INLINE(), ((DirectionTypes)iI));

		if (pAdjacentPlot != NULL) {
			if (pAdjacentPlot->getArea() == iAreaID) {
				return true;
			}
		}
	}

	return false;
}

bool CvPlot::isAdjacentToArea(const CvArea* pArea) const {
	return isAdjacentToArea(pArea->getID());
}


bool CvPlot::shareAdjacentArea(const CvPlot* pPlot) const {
	PROFILE_FUNC();

	int iLastArea = FFreeList::INVALID_INDEX;

	for (int iI = 0; iI < NUM_DIRECTION_TYPES; ++iI) {
		CvPlot* pAdjacentPlot = plotDirection(getX_INLINE(), getY_INLINE(), ((DirectionTypes)iI));

		if (pAdjacentPlot != NULL) {
			int iCurrArea = pAdjacentPlot->getArea();

			if (iCurrArea != iLastArea) {
				if (pPlot->isAdjacentToArea(iCurrArea)) {
					return true;
				}

				iLastArea = iCurrArea;
			}
		}
	}

	return false;
}


bool CvPlot::isAdjacentToLand() const {
	PROFILE_FUNC();

	for (int iI = 0; iI < NUM_DIRECTION_TYPES; ++iI) {
		CvPlot* pAdjacentPlot = plotDirection(getX_INLINE(), getY_INLINE(), ((DirectionTypes)iI));

		if (pAdjacentPlot != NULL) {
			if (!(pAdjacentPlot->isWater())) {
				return true;
			}
		}
	}

	return false;
}


bool CvPlot::isCoastalLand(int iMinWaterSize, int iMaxWaterSize) const {
	PROFILE_FUNC();

	if (isWater()) {
		return false;
	}

	for (int iI = 0; iI < NUM_DIRECTION_TYPES; ++iI) {
		CvPlot* pAdjacentPlot = plotDirection(getX_INLINE(), getY_INLINE(), ((DirectionTypes)iI));

		if (pAdjacentPlot != NULL) {
			if (pAdjacentPlot->isWater()) {
				int iNumWaterTiles = pAdjacentPlot->area()->getNumTiles();
				if (iNumWaterTiles >= iMinWaterSize && iNumWaterTiles <= iMaxWaterSize) {
					return true;
				}
			}
		}
	}

	return false;
}


bool CvPlot::isVisibleWorked() const {
	if (isBeingWorked()) {
		if ((getTeam() == GC.getGameINLINE().getActiveTeam()) || GC.getGameINLINE().isDebugMode()) {
			return true;
		}
	}

	return false;
}


bool CvPlot::isWithinTeamCityRadius(TeamTypes eTeam, PlayerTypes eIgnorePlayer) const {
	PROFILE_FUNC();

	for (int iI = 0; iI < MAX_PLAYERS; ++iI) {
		if (GET_PLAYER((PlayerTypes)iI).isAlive()) {
			if (GET_PLAYER((PlayerTypes)iI).getTeam() == eTeam) {
				if ((eIgnorePlayer == NO_PLAYER) || (((PlayerTypes)iI) != eIgnorePlayer)) {
					if (isPlayerCityRadius((PlayerTypes)iI)) {
						return true;
					}
				}
			}
		}
	}

	return false;
}


bool CvPlot::isLake() const {
	CvArea* pArea = area();
	if (pArea != NULL) {
		return pArea->isLake();
	}

	return false;
}


// XXX if this changes need to call updateIrrigated() and pCity->updateFreshWaterHealth()
// XXX precalculate this???
bool CvPlot::isFreshWater() const {
	if (isWater()) {
		return false;
	}

	if (isImpassable()) {
		return false;
	}

	if (isRiver()) {
		return true;
	}

	for (int iDX = -1; iDX <= 1; iDX++) {
		for (int iDY = -1; iDY <= 1; iDY++) {
			CvPlot* pLoopPlot = plotXY(getX_INLINE(), getY_INLINE(), iDX, iDY);

			if (pLoopPlot != NULL) {
				if (pLoopPlot->isLake()) {
					return true;
				}

				if (pLoopPlot->getFeatureType() != NO_FEATURE) {
					if (GC.getFeatureInfo(pLoopPlot->getFeatureType()).isAddsFreshWater()) {
						return true;
					}
				}
			}
		}
	}

	return false;
}


bool CvPlot::isPotentialIrrigation() const {
	if ((isCity() && !(isHills() || isPeak())) || ((getImprovementType() != NO_IMPROVEMENT) && (GC.getImprovementInfo(getImprovementType()).isCarriesIrrigation()))) {
		if ((getTeam() != NO_TEAM) && GET_TEAM(getTeam()).isIrrigation()) {
			return true;
		}
	}

	return false;
}


bool CvPlot::canHavePotentialIrrigation() const {
	if (isCity() && !(isHills() || isPeak())) {
		return true;
	}

	for (int iI = 0; iI < GC.getNumImprovementInfos(); ++iI) {
		if (GC.getImprovementInfo((ImprovementTypes)iI).isCarriesIrrigation()) {
			if (canHaveImprovement(((ImprovementTypes)iI), NO_TEAM, true)) {
				return true;
			}
		}
	}

	return false;
}


bool CvPlot::isIrrigationAvailable(bool bIgnoreSelf) const {
	if (!bIgnoreSelf && isIrrigated()) {
		return true;
	}

	if (isFreshWater()) {
		return true;
	}

	for (int iI = 0; iI < NUM_DIRECTION_TYPES; ++iI) {
		CvPlot* pAdjacentPlot = plotDirection(getX_INLINE(), getY_INLINE(), ((DirectionTypes)iI));

		if (pAdjacentPlot != NULL) {
			if (pAdjacentPlot->isIrrigated()) {
				return true;
			}
		}
	}

	return false;
}


bool CvPlot::isRiverMask() const {
	if (isNOfRiver()) {
		return true;
	}

	if (isWOfRiver()) {
		return true;
	}

	CvPlot* pPlot = plotDirection(getX_INLINE(), getY_INLINE(), DIRECTION_EAST);
	if ((pPlot != NULL) && pPlot->isNOfRiver()) {
		return true;
	}

	pPlot = plotDirection(getX_INLINE(), getY_INLINE(), DIRECTION_SOUTH);
	if ((pPlot != NULL) && pPlot->isWOfRiver()) {
		return true;
	}

	return false;
}


bool CvPlot::isRiverCrossingFlowClockwise(DirectionTypes eDirection) const {
	CvPlot* pPlot;
	switch (eDirection) {
	case DIRECTION_NORTH:
		pPlot = plotDirection(getX_INLINE(), getY_INLINE(), DIRECTION_NORTH);
		if (pPlot != NULL) {
			return (pPlot->getRiverWEDirection() == CARDINALDIRECTION_EAST);
		}
		break;
	case DIRECTION_EAST:
		return (getRiverNSDirection() == CARDINALDIRECTION_SOUTH);
		break;
	case DIRECTION_SOUTH:
		return (getRiverWEDirection() == CARDINALDIRECTION_WEST);
		break;
	case DIRECTION_WEST:
		pPlot = plotDirection(getX_INLINE(), getY_INLINE(), DIRECTION_WEST);
		if (pPlot != NULL) {
			return (pPlot->getRiverNSDirection() == CARDINALDIRECTION_NORTH);
		}
		break;
	default:
		FAssert(false);
		break;
	}

	return false;
}


bool CvPlot::isRiverSide() const {
	for (int iI = 0; iI < NUM_CARDINALDIRECTION_TYPES; ++iI) {
		CvPlot* pLoopPlot = plotCardinalDirection(getX_INLINE(), getY_INLINE(), ((CardinalDirectionTypes)iI));

		if (pLoopPlot != NULL) {
			if (isRiverCrossing(directionXY(this, pLoopPlot))) {
				return true;
			}
		}
	}

	return false;
}


bool CvPlot::isRiver() const {
	return (getRiverCrossingCount() > 0);
}


bool CvPlot::isRiverConnection(DirectionTypes eDirection) const {
	if (eDirection == NO_DIRECTION) {
		return false;
	}

	switch (eDirection) {
	case DIRECTION_NORTH:
		return (isRiverCrossing(DIRECTION_EAST) || isRiverCrossing(DIRECTION_WEST));
		break;

	case DIRECTION_NORTHEAST:
		return (isRiverCrossing(DIRECTION_NORTH) || isRiverCrossing(DIRECTION_EAST));
		break;

	case DIRECTION_EAST:
		return (isRiverCrossing(DIRECTION_NORTH) || isRiverCrossing(DIRECTION_SOUTH));
		break;

	case DIRECTION_SOUTHEAST:
		return (isRiverCrossing(DIRECTION_SOUTH) || isRiverCrossing(DIRECTION_EAST));
		break;

	case DIRECTION_SOUTH:
		return (isRiverCrossing(DIRECTION_EAST) || isRiverCrossing(DIRECTION_WEST));
		break;

	case DIRECTION_SOUTHWEST:
		return (isRiverCrossing(DIRECTION_SOUTH) || isRiverCrossing(DIRECTION_WEST));
		break;

	case DIRECTION_WEST:
		return (isRiverCrossing(DIRECTION_NORTH) || isRiverCrossing(DIRECTION_SOUTH));
		break;

	case DIRECTION_NORTHWEST:
		return (isRiverCrossing(DIRECTION_NORTH) || isRiverCrossing(DIRECTION_WEST));
		break;

	default:
		FAssert(false);
		break;
	}

	return false;
}


CvPlot* CvPlot::getNearestLandPlotInternal(int iDistance) const {
	if (iDistance > GC.getMapINLINE().getGridHeightINLINE() && iDistance > GC.getMapINLINE().getGridWidthINLINE()) {
		return NULL;
	}

	for (int iDX = -iDistance; iDX <= iDistance; iDX++) {
		for (int iDY = -iDistance; iDY <= iDistance; iDY++) {
			if (abs(iDX) + abs(iDY) == iDistance) {
				CvPlot* pPlot = plotXY(getX_INLINE(), getY_INLINE(), iDX, iDY);
				if (pPlot != NULL) {
					if (!pPlot->isWater()) {
						return pPlot;
					}
				}
			}
		}
	}
	return getNearestLandPlotInternal(iDistance + 1);
}


int CvPlot::getNearestLandArea() const {
	CvPlot* pPlot = getNearestLandPlot();
	return pPlot ? pPlot->getArea() : -1;
}


CvPlot* CvPlot::getNearestLandPlot() const {
	return getNearestLandPlotInternal(0);
}


int CvPlot::seeFromLevel(TeamTypes eTeam) const {
	FAssertMsg(getTerrainType() != NO_TERRAIN, "TerrainType is not assigned a valid value");

	int iLevel = GC.getTerrainInfo(getTerrainType()).getSeeFromLevel();

	if (isPeak()) {
		iLevel += GC.getPEAK_SEE_FROM_CHANGE();
	}

	if (isHills()) {
		iLevel += GC.getHILLS_SEE_FROM_CHANGE();
	}

	if (isWater()) {
		iLevel += GC.getSEAWATER_SEE_FROM_CHANGE();

		if (GET_TEAM(eTeam).isExtraWaterSeeFrom()) {
			iLevel++;
		}
	}

	return iLevel;
}


int CvPlot::seeThroughLevel() const {
	FAssertMsg(getTerrainType() != NO_TERRAIN, "TerrainType is not assigned a valid value");

	int iLevel = GC.getTerrainInfo(getTerrainType()).getSeeThroughLevel();

	if (getFeatureType() != NO_FEATURE) {
		iLevel += GC.getFeatureInfo(getFeatureType()).getSeeThroughChange();
	}

	if (isPeak()) {
		iLevel += GC.getPEAK_SEE_THROUGH_CHANGE();
	}

	if (isHills()) {
		iLevel += GC.getHILLS_SEE_THROUGH_CHANGE();
	}

	if (isWater()) {
		iLevel += GC.getSEAWATER_SEE_FROM_CHANGE();
	}

	return iLevel;
}



void CvPlot::changeAdjacentSight(TeamTypes eTeam, int iRange, bool bIncrement, CvUnit* pUnit, bool bUpdatePlotGroups) {
	// Most original code moved to 
	// void CvPlot::changeAdjacentSight(TeamTypes eTeam, int iRange, bool bIncrement, CvUnit* pUnit, bool bUpdatePlotGroups, std::vector<InvisibleTypes>& vSeeInvisibles)

	//fill invisible types
	std::vector<InvisibleTypes> aSeeInvisibleTypes;
	if (NULL != pUnit) {
		for (int i = 0; i < pUnit->getNumSeeInvisibleTypes(); i++) {
			aSeeInvisibleTypes.push_back(pUnit->getSeeInvisibleType(i));
		}
	}

	changeAdjacentSight(eTeam, iRange, bIncrement, pUnit, bUpdatePlotGroups, aSeeInvisibleTypes);
}

void CvPlot::changeAdjacentSight(TeamTypes eTeam, int iRange, bool bIncrement, CvUnit* pUnit, bool bUpdatePlotGroups, std::vector<InvisibleTypes>& vSeeInvisibles) {
	bool bAerial = (pUnit != NULL && pUnit->getDomainType() == DOMAIN_AIR);

	DirectionTypes eFacingDirection = NO_DIRECTION;
	if (!bAerial && NULL != pUnit) {
		eFacingDirection = pUnit->getFacingDirection(true);
	}

	if (vSeeInvisibles.size() == 0) {
		vSeeInvisibles.push_back(NO_INVISIBLE);
	}

	//check one extra outer ring
	if (!bAerial) {
		iRange++;
	}

	for (int i = 0; i < (int)vSeeInvisibles.size(); i++) {
		for (int dx = -iRange; dx <= iRange; dx++) {
			for (int dy = -iRange; dy <= iRange; dy++) {
				//check if in facing direction
				if (bAerial || shouldProcessDisplacementPlot(dx, dy, iRange - 1, eFacingDirection)) {
					bool outerRing = false;
					if ((abs(dx) == iRange) || (abs(dy) == iRange)) {
						outerRing = true;
					}

					//check if anything blocking the plot
					if (bAerial || canSeeDisplacementPlot(eTeam, dx, dy, dx, dy, true, outerRing)) {
						CvPlot* pPlot = plotXY(getX_INLINE(), getY_INLINE(), dx, dy);
						if (NULL != pPlot) {
							pPlot->changeVisibilityCount(eTeam, ((bIncrement) ? 1 : -1), vSeeInvisibles[i], bUpdatePlotGroups);
						}
					}
				}

				if (eFacingDirection != NO_DIRECTION) {
					if ((abs(dx) <= 1) && (abs(dy) <= 1)) //always reveal adjacent plots when using line of sight
					{
						CvPlot* pPlot = plotXY(getX_INLINE(), getY_INLINE(), dx, dy);
						if (NULL != pPlot) {
							pPlot->changeVisibilityCount(eTeam, 1, vSeeInvisibles[i], bUpdatePlotGroups);
							pPlot->changeVisibilityCount(eTeam, -1, vSeeInvisibles[i], bUpdatePlotGroups);
						}
					}
				}
			}
		}
	}
}

bool CvPlot::canSeePlot(CvPlot* pPlot, TeamTypes eTeam, int iRange, DirectionTypes eFacingDirection) const {
	iRange++;

	if (pPlot == NULL) {
		return false;
	}

	//find displacement
	int dx = pPlot->getX() - getX();
	int dy = pPlot->getY() - getY();
	dx = dxWrap(dx); //world wrap
	dy = dyWrap(dy);

	//check if in facing direction
	if (shouldProcessDisplacementPlot(dx, dy, iRange - 1, eFacingDirection)) {
		bool outerRing = false;
		if ((abs(dx) == iRange) || (abs(dy) == iRange)) {
			outerRing = true;
		}

		//check if anything blocking the plot
		if (canSeeDisplacementPlot(eTeam, dx, dy, dx, dy, true, outerRing)) {
			return true;
		}
	}

	return false;
}

bool CvPlot::canSeeDisplacementPlot(TeamTypes eTeam, int dx, int dy, int originalDX, int originalDY, bool firstPlot, bool outerRing) const {
	CvPlot* pPlot = plotXY(getX_INLINE(), getY_INLINE(), dx, dy);
	if (pPlot != NULL) {
		//base case is current plot
		if ((dx == 0) && (dy == 0)) {
			return true;
		}

		//find closest of three points (1, 2, 3) to original line from Start (S) to End (E)
		//The diagonal is computed first as that guarantees a change in position
		// -------------
		// |   | 2 | S |
		// -------------
		// | E | 1 | 3 |
		// -------------

		int displacements[3][2] = { {dx - getSign(dx), dy - getSign(dy)}, {dx - getSign(dx), dy}, {dx, dy - getSign(dy)} };
		int allClosest[3];
		int closest = -1;
		for (int i = 0; i < 3; i++) {
			//int tempClosest = abs(displacements[i][0] * originalDX - displacements[i][1] * originalDY); //more accurate, but less structured on a grid
			allClosest[i] = abs(displacements[i][0] * dy - displacements[i][1] * dx); //cross product
			if ((closest == -1) || (allClosest[i] < closest)) {
				closest = allClosest[i];
			}
		}

		//iterate through all minimum plots to see if any of them are passable
		for (int i = 0; i < 3; i++) {
			int nextDX = displacements[i][0];
			int nextDY = displacements[i][1];
			if ((nextDX != dx) || (nextDY != dy)) //make sure we change plots
			{
				if (allClosest[i] == closest) {
					if (canSeeDisplacementPlot(eTeam, nextDX, nextDY, originalDX, originalDY, false, false)) {
						int fromLevel = seeFromLevel(eTeam);
						int throughLevel = pPlot->seeThroughLevel();
						if (outerRing) //check strictly higher level
						{
							CvPlot* passThroughPlot = plotXY(getX_INLINE(), getY_INLINE(), nextDX, nextDY);
							int passThroughLevel = passThroughPlot->seeThroughLevel();
							if (fromLevel >= passThroughLevel) {
								if ((fromLevel > passThroughLevel) || (pPlot->seeFromLevel(eTeam) > fromLevel)) //either we can see through to it or it is high enough to see from far
								{
									return true;
								}
							}
						} else {
							if (fromLevel >= throughLevel) //we can clearly see this level
							{
								return true;
							} else if (firstPlot) //we can also see it if it is the first plot that is too tall
							{
								return true;
							}
						}
					}
				}
			}
		}
	}

	return false;
}

bool CvPlot::shouldProcessDisplacementPlot(int dx, int dy, int range, DirectionTypes eFacingDirection) const {
	if (eFacingDirection == NO_DIRECTION) {
		return true;
	} else if ((dx == 0) && (dy == 0)) //always process this plot
	{
		return true;
	} else {
		//							N		NE		E		SE			S		SW		W			NW
		int displacements[8][2] = { {0, 1}, {1, 1}, {1, 0}, {1, -1}, {0, -1}, {-1, -1}, {-1, 0}, {-1, 1} };

		int directionX = displacements[eFacingDirection][0];
		int directionY = displacements[eFacingDirection][1];

		//compute angle off of direction
		int crossProduct = directionX * dy - directionY * dx; //cross product
		int dotProduct = directionX * dx + directionY * dy; //dot product

		float theta = atan2((float)crossProduct, (float)dotProduct);
		float spread = 60 * (float)M_PI / 180;
		if ((abs(dx) <= 1) && (abs(dy) <= 1)) //close plots use wider spread
		{
			spread = 90 * (float)M_PI / 180;
		}

		if ((theta >= -spread / 2) && (theta <= spread / 2)) {
			return true;
		} else {
			return false;
		}

	}
}

void CvPlot::updateSight(bool bIncrement, bool bUpdatePlotGroups) {
	CvCity* pCity = getPlotCity();

	if (pCity != NULL) {
		// Vassal
		for (TeamTypes eTeam = (TeamTypes)0; eTeam < MAX_TEAMS; eTeam = (TeamTypes)(eTeam + 1)) {
			if (GET_TEAM(getTeam()).isVassal(eTeam)) {
				changeAdjacentSight(eTeam, GC.getDefineINT("PLOT_VISIBILITY_RANGE"), bIncrement, NULL, bUpdatePlotGroups);
			}
		}

		// EspionageEffect
		for (TeamTypes eTeam = (TeamTypes)0; eTeam < MAX_CIV_TEAMS; eTeam = (TeamTypes)(eTeam + 1)) {
			if (pCity->getEspionageVisibility(eTeam)) {
				// Passive Effect: enough EPs gives you visibility into someone's cities
				changeAdjacentSight(eTeam, GC.getDefineINT("PLOT_VISIBILITY_RANGE"), bIncrement, NULL, bUpdatePlotGroups);
			}
		}

		// Embassy
		if (pCity->isCapital()) {
			TeamTypes pTeam = pCity->getTeam();
			for (TeamTypes eTeam = (TeamTypes)0; eTeam < MAX_CIV_TEAMS; eTeam = (TeamTypes)(eTeam + 1)) {
				if (GET_TEAM(pTeam).isHasEmbassy(eTeam)) {
					changeAdjacentSight(eTeam, GC.getDefineINT("PLOT_VISIBILITY_RANGE"), bIncrement, NULL, bUpdatePlotGroups);
				}
			}
		}
	}

	// Owned
	if (isOwned()) {
		changeAdjacentSight(getTeam(), GC.getDefineINT("PLOT_VISIBILITY_RANGE"), bIncrement, NULL, bUpdatePlotGroups);
	}

	CLLNode<IDInfo>* pUnitNode = headUnitNode();

	// Unit
	while (pUnitNode != NULL) {
		CvUnit* pLoopUnit = ::getUnit(pUnitNode->m_data);
		pUnitNode = nextUnitNode(pUnitNode);


		changeAdjacentSight(pLoopUnit->getTeam(), pLoopUnit->visibilityRange(), bIncrement, pLoopUnit, bUpdatePlotGroups);
	}

	if (getReconCount() > 0) {
		int iRange = GC.getDefineINT("RECON_VISIBILITY_RANGE");
		for (int iI = 0; iI < MAX_PLAYERS; ++iI) {
			int iLoop;
			for (CvUnit* pLoopUnit = GET_PLAYER((PlayerTypes)iI).firstUnit(&iLoop); pLoopUnit != NULL; pLoopUnit = GET_PLAYER((PlayerTypes)iI).nextUnit(&iLoop)) {
				if (pLoopUnit->getReconPlot() == this) {
					changeAdjacentSight(pLoopUnit->getTeam(), iRange, bIncrement, pLoopUnit, bUpdatePlotGroups);
				}
			}
		}
	}
}


void CvPlot::updateSeeFromSight(bool bIncrement, bool bUpdatePlotGroups) {
	int iRange = GC.getDefineINT("UNIT_VISIBILITY_RANGE") + 1;
	for (int iPromotion = 0; iPromotion < GC.getNumPromotionInfos(); ++iPromotion) {
		iRange += GC.getPromotionInfo((PromotionTypes)iPromotion).getVisibilityChange();
	}

	iRange = std::max(GC.getDefineINT("RECON_VISIBILITY_RANGE") + 1, iRange);

	for (int iDX = -iRange; iDX <= iRange; iDX++) {
		for (int iDY = -iRange; iDY <= iRange; iDY++) {
			CvPlot* pLoopPlot = plotXY(getX_INLINE(), getY_INLINE(), iDX, iDY);

			if (pLoopPlot != NULL) {
				pLoopPlot->updateSight(bIncrement, bUpdatePlotGroups);
			}
		}
	}
}


bool CvPlot::canHaveBonus(BonusTypes eBonus, bool bIgnoreLatitude) const {
	FAssertMsg(getTerrainType() != NO_TERRAIN, "TerrainType is not assigned a valid value");

	if (eBonus == NO_BONUS) {
		return true;
	}

	if (getBonusType() != NO_BONUS) {
		return false;
	}

	const CvBonusInfo& kBonus = GC.getBonusInfo(eBonus);
	if (getFeatureType() != NO_FEATURE) {
		if (!kBonus.isFeature(getFeatureType())) {
			return false;
		}

		if (!kBonus.isFeatureTerrain(getTerrainType())) {
			return false;
		}
	} else {
		if (!kBonus.isTerrain(getTerrainType())) {
			return false;
		}
	}

	if (isHills() && !kBonus.isHills()) {
		return false;
	} else if (isPeak() && !kBonus.isPeaks()) {
		return false;
	} else if (isFlatlands() && !kBonus.isFlatlands()) {
		return false;
	}

	if (kBonus.isNoRiverSide() && isRiverSide()) {
		return false;
	}

	if (kBonus.getMinAreaSize() != -1) {
		if (area()->getNumTiles() < kBonus.getMinAreaSize()) {
			return false;
		}
	}

	if (!bIgnoreLatitude) {
		if (getLatitude() > kBonus.getMaxLatitude()) {
			return false;
		}

		if (getLatitude() < kBonus.getMinLatitude()) {
			return false;
		}
	}

	if (!isPotentialCityWork()) {
		return false;
	}

	return true;
}


bool CvPlot::canHaveImprovement(ImprovementTypes eImprovement, TeamTypes eTeam, bool bPotential) const {
	if (eImprovement == NO_IMPROVEMENT)
		return true;
	FAssertMsg(getTerrainType() != NO_TERRAIN, "TerrainType is not assigned a valid value");

	bool bValid = false;

	if (isCity()) {
		return false;
	}

	if (isImpassable(getTeam())) {
		return false;
	}

	const CvImprovementInfo& kImprovement = GC.getImprovementInfo(eImprovement);
	if (kImprovement.isWater() != isWater()) {
		return false;
	}

	if (getFeatureType() != NO_FEATURE) {
		if (GC.getFeatureInfo(getFeatureType()).isNoImprovement()) {
			return false;
		}
	}

	if ((getBonusType(eTeam) != NO_BONUS) && kImprovement.isImprovementBonusMakesValid(getBonusType(eTeam))) {
		return true;
	}

	if (kImprovement.isNoFreshWater() && isFreshWater()) {
		return false;
	}

	if (kImprovement.isRequiresFlatlands() && !isFlatlands()) {
		return false;
	}

	if (kImprovement.isRequiresFeature() && (getFeatureType() == NO_FEATURE)) {
		return false;
	}

	if (kImprovement.isHillsMakesValid() && isHills()) {
		bValid = true;
	}

	if (kImprovement.isFreshWaterMakesValid() && isFreshWater()) {
		bValid = true;
	}

	if (kImprovement.isRiverSideMakesValid() && isRiverSide()) {
		bValid = true;
	}

	if (kImprovement.getTerrainMakesValid(getTerrainType())) {
		bValid = true;
	}

	if ((getFeatureType() != NO_FEATURE) && kImprovement.getFeatureMakesValid(getFeatureType())) {
		bValid = true;
	}

	if (!bValid) {
		return false;
	}

	if (kImprovement.isRequiresRiverSide()) {
		bValid = false;

		for (CardinalDirectionTypes eDirection = (CardinalDirectionTypes)0; eDirection < NUM_CARDINALDIRECTION_TYPES; eDirection = (CardinalDirectionTypes)(eDirection + 1)) {
			CvPlot* pLoopPlot = plotCardinalDirection(getX_INLINE(), getY_INLINE(), eDirection);

			if (pLoopPlot != NULL) {
				if (isRiverCrossing(directionXY(this, pLoopPlot))) {
					if (pLoopPlot->getImprovementType() != eImprovement) {
						bValid = true;
						break;
					}
				}
			}
		}

		if (!bValid) {
			return false;
		}
	}

	for (YieldTypes eYield = (YieldTypes)0; eYield < NUM_YIELD_TYPES; eYield = (YieldTypes)(eYield + 1)) {
		if (calculateNatureYield(eYield, eTeam) < kImprovement.getPrereqNatureYield(eYield)) {
			return false;
		}
	}

	if (getTeam() == NO_TEAM || !GET_TEAM(getTeam()).isIgnoreIrrigation()) {
		if (!bPotential && kImprovement.isRequiresIrrigation() && !isIrrigationAvailable()) {
			return false;
		}
	}

	return true;
}


bool CvPlot::canBuild(BuildTypes eBuild, PlayerTypes ePlayer, bool bTestVisible) const {
	if (GC.getUSE_CAN_BUILD_CALLBACK()) {
		CyArgsList argsList;
		argsList.add(getX_INLINE());
		argsList.add(getY_INLINE());
		argsList.add((int)eBuild);
		argsList.add((int)ePlayer);
		long lResult = 0;
		gDLL->getPythonIFace()->callFunction(PYGameModule, "canBuild", argsList.makeFunctionArgs(), &lResult);
		if (lResult >= 1) {
			return true;
		} else if (lResult == 0) {
			return false;
		}
	}

	if (eBuild == NO_BUILD) {
		return false;
	}

	bool bValid = false;

	ImprovementTypes eImprovement = ((ImprovementTypes)(GC.getBuildInfo(eBuild).getImprovement()));

	if (eImprovement != NO_IMPROVEMENT) {
		if (!canHaveImprovement(eImprovement, GET_PLAYER(ePlayer).getTeam(), bTestVisible)) {
			return false;
		}

		if (getImprovementType() != NO_IMPROVEMENT) {
			if (GC.getImprovementInfo(getImprovementType()).isPermanent()) {
				return false;
			}

			if (getImprovementType() == eImprovement) {
				return false;
			}

			ImprovementTypes eFinalImprovementType = finalImprovementUpgrade(getImprovementType());

			if (eFinalImprovementType != NO_IMPROVEMENT) {
				if (eFinalImprovementType == finalImprovementUpgrade(eImprovement)) {
					return false;
				}
			}
		}

		if (!bTestVisible) {
			if (GET_PLAYER(ePlayer).getTeam() != getTeam()) {
				//outside borders can't be built in other's culture
				if (GC.getImprovementInfo(eImprovement).isOutsideBorders()) {
					if (getTeam() != NO_TEAM) {
						return false;
					}
				} else //only buildable in own culture
				{
					return false;
				}
			}
		}

		bValid = true;
	}

	RouteTypes eRoute = ((RouteTypes)(GC.getBuildInfo(eBuild).getRoute()));

	if (eRoute != NO_ROUTE) {
		const CvRouteInfo& kRoute = GC.getRouteInfo(eRoute);
		if (getRouteType() != NO_ROUTE) {
			if (GC.getRouteInfo(getRouteType()).getValue() >= kRoute.getValue()) {
				return false;
			}
		}

		if (!bTestVisible) {
			if (kRoute.getPrereqBonus() != NO_BONUS) {
				if (!isAdjacentPlotGroupConnectedBonus(ePlayer, (BonusTypes)kRoute.getPrereqBonus())) {
					return false;
				}
			}

			bool bFoundValid = true;
			for (int i = 0; i < kRoute.getNumPrereqOrBonuses(); ++i) {
				if (NO_BONUS != kRoute.getPrereqOrBonus(i)) {
					bFoundValid = false;

					if (isAdjacentPlotGroupConnectedBonus(ePlayer, (BonusTypes)kRoute.getPrereqOrBonus(i))) {
						bFoundValid = true;
						break;
					}
				}
			}

			if (!bFoundValid) {
				return false;
			}
		}

		bValid = true;
	}

	if (getFeatureType() != NO_FEATURE) {
		if (GC.getBuildInfo(eBuild).isFeatureRemove(getFeatureType())) {
			if (isOwned() && (GET_PLAYER(ePlayer).getTeam() != getTeam()) && !atWar(GET_PLAYER(ePlayer).getTeam(), getTeam())) {
				return false;
			}

			bValid = true;
		}
	}

	return bValid;
}


int CvPlot::getBuildTime(BuildTypes eBuild) const {
	FAssertMsg(getTerrainType() != NO_TERRAIN, "TerrainType is not assigned a valid value");

	int iTime = GC.getBuildInfo(eBuild).getTime();

	if (getFeatureType() != NO_FEATURE) {
		iTime += GC.getBuildInfo(eBuild).getFeatureTime(getFeatureType());
	}

	if (isPeak()) {
		iTime *= std::max(0, (GC.getDefineINT("PEAK_BUILD_TIME_MODIFIER") + 100));
		iTime /= 100;
	}

	iTime *= std::max(0, (GC.getTerrainInfo(getTerrainType()).getBuildModifier() + 100));
	iTime /= 100;

	iTime *= GC.getGameSpeedInfo(GC.getGameINLINE().getGameSpeedType()).getBuildPercent();
	iTime /= 100;

	iTime *= GC.getEraInfo(GC.getGameINLINE().getStartEra()).getBuildPercent();
	iTime /= 100;

	return iTime;
}


int CvPlot::getBuildTurnsLeft(BuildTypes eBuild, PlayerTypes ePlayer) const {
	int iWorkRate = GET_PLAYER(ePlayer).getWorkRate(eBuild);
	if (iWorkRate > 0) {
		return getBuildTurnsLeft(eBuild, iWorkRate, iWorkRate, false);
	} else {
		return MAX_INT;
	}
}

int CvPlot::getBuildTurnsLeft(BuildTypes eBuild, int iNowExtra, int iThenExtra, bool bIncludeUnits) const {

	int iNowBuildRate = iNowExtra;
	int iThenBuildRate = iThenExtra;

	if (bIncludeUnits) {
		CLLNode<IDInfo>* pUnitNode = headUnitNode();
		while (pUnitNode != NULL) {
			CvUnit* pLoopUnit = ::getUnit(pUnitNode->m_data);
			pUnitNode = nextUnitNode(pUnitNode);

			if (pLoopUnit->getBuildType() == eBuild) {
				if (pLoopUnit->canMove()) {
					iNowBuildRate += pLoopUnit->workRate(false);
				}
				iThenBuildRate += pLoopUnit->workRate(true);
			}
		}
	}

	if (iThenBuildRate == 0) {
		//this means it will take forever under current circumstances
		return MAX_INT;
	}

	int iBuildLeft = getBuildTime(eBuild);

	iBuildLeft -= getBuildProgress(eBuild);
	iBuildLeft -= iNowBuildRate;

	iBuildLeft = std::max(0, iBuildLeft);

	int iTurnsLeft = (iBuildLeft / iThenBuildRate);

	if ((iTurnsLeft * iThenBuildRate) < iBuildLeft) {
		iTurnsLeft++;
	}

	iTurnsLeft++;

	return std::max(1, iTurnsLeft);
}


int CvPlot::getFeatureProduction(BuildTypes eBuild, TeamTypes eTeam, CvCity** ppCity) const {
	if (getFeatureType() == NO_FEATURE) {
		return 0;
	}

	*ppCity = getWorkingCity();

	if (*ppCity == NULL) {
		*ppCity = GC.getMapINLINE().findCity(getX_INLINE(), getY_INLINE(), NO_PLAYER, eTeam, false);
	}

	if (*ppCity == NULL) {
		return 0;
	}

	int iProduction = (GC.getBuildInfo(eBuild).getFeatureProduction(getFeatureType()) - (std::max(0, (plotDistance(getX_INLINE(), getY_INLINE(), (*ppCity)->getX_INLINE(), (*ppCity)->getY_INLINE()) - 2)) * 5));

	iProduction *= std::max(0, (GET_PLAYER((*ppCity)->getOwnerINLINE()).getFeatureProductionModifier() + 100));
	iProduction /= 100;

	iProduction *= GC.getGameSpeedInfo(GC.getGameINLINE().getGameSpeedType()).getFeatureProductionPercent();
	iProduction /= 100;

	iProduction *= std::min((GC.getDefineINT("BASE_FEATURE_PRODUCTION_PERCENT") + (GC.getDefineINT("FEATURE_PRODUCTION_PERCENT_MULTIPLIER") * (*ppCity)->getPopulation())), 100);
	iProduction /= 100;

	if (getTeam() != eTeam) {
		iProduction *= GC.getDefineINT("DIFFERENT_TEAM_FEATURE_PRODUCTION_PERCENT");
		iProduction /= 100;
	}

	return std::max(0, iProduction);
}


CvUnit* CvPlot::getBestDefender(PlayerTypes eOwner, PlayerTypes eAttackingPlayer, const CvUnit* pAttacker, bool bTestAtWar, bool bTestPotentialEnemy, bool bTestCanMove) const {

	int iBestUnitRank = -1;
	CvUnit* pBestUnit = NULL;
	CLLNode<IDInfo>* pUnitNode = headUnitNode();

	while (pUnitNode != NULL) {
		CvUnit* pLoopUnit = ::getUnit(pUnitNode->m_data);
		pUnitNode = nextUnitNode(pUnitNode);

		if ((eOwner == NO_PLAYER) || (pLoopUnit->getOwnerINLINE() == eOwner)) {
			if ((eAttackingPlayer == NO_PLAYER) || !(pLoopUnit->isInvisible(GET_PLAYER(eAttackingPlayer).getTeam(), false))) {
				if (!bTestAtWar || eAttackingPlayer == NO_PLAYER || pLoopUnit->isEnemy(GET_PLAYER(eAttackingPlayer).getTeam(), this) || (NULL != pAttacker && pAttacker->isEnemy(GET_PLAYER(pLoopUnit->getOwnerINLINE()).getTeam(), this))) {
					if (!bTestPotentialEnemy || (eAttackingPlayer == NO_PLAYER) || pLoopUnit->isPotentialEnemy(GET_PLAYER(eAttackingPlayer).getTeam(), this) || (NULL != pAttacker && pAttacker->isPotentialEnemy(GET_PLAYER(pLoopUnit->getOwnerINLINE()).getTeam(), this))) {
						if (!bTestCanMove || (pLoopUnit->canMove() && !(pLoopUnit->isCargo()))) {
							if ((pAttacker == NULL) || (pAttacker->getDomainType() != DOMAIN_AIR) || (pLoopUnit->getDamage() < pAttacker->airCombatLimit())) {
								// UncutDragon
								// original
								//if (pLoopUnit->isBetterDefenderThan(pBestUnit, pAttacker))
								// modified (added extra parameter)
								if (pLoopUnit->isBetterDefenderThan(pBestUnit, pAttacker, &iBestUnitRank))
									// /UncutDragon
								{
									pBestUnit = pLoopUnit;
								}
							}
						}
					}
				}
			}
		}
	}

	return pBestUnit;
}

CvUnit* CvPlot::getSelectedUnit() const {
	CLLNode<IDInfo>* pUnitNode = headUnitNode();

	while (pUnitNode != NULL) {
		CvUnit* pLoopUnit = ::getUnit(pUnitNode->m_data);
		pUnitNode = nextUnitNode(pUnitNode);

		if (pLoopUnit->IsSelected()) {
			return pLoopUnit;
		}
	}

	return NULL;
}


int CvPlot::getUnitPower(PlayerTypes eOwner) const {
	int iCount = 0;
	CLLNode<IDInfo>* pUnitNode = headUnitNode();

	while (pUnitNode != NULL) {
		CvUnit* pLoopUnit = ::getUnit(pUnitNode->m_data);
		pUnitNode = nextUnitNode(pUnitNode);

		if ((eOwner == NO_PLAYER) || (pLoopUnit->getOwnerINLINE() == eOwner)) {
			iCount += pLoopUnit->getUnitInfo().getPowerValue();
		}
	}

	return iCount;
}


int CvPlot::defenseModifier(TeamTypes eDefender, bool bIgnoreBuilding, bool bHelp) const {
	FAssertMsg(getTerrainType() != NO_TERRAIN, "TerrainType is not assigned a valid value");

	int iModifier = ((getFeatureType() == NO_FEATURE) ? GC.getTerrainInfo(getTerrainType()).getDefenseModifier() : GC.getFeatureInfo(getFeatureType()).getDefenseModifier());

	if (isHills()) {
		iModifier += GC.getHILLS_EXTRA_DEFENSE();
	}

	if (isPeak()) {
		iModifier += GC.getPEAK_EXTRA_DEFENSE();
	}

	ImprovementTypes eImprovement;
	if (bHelp) {
		eImprovement = getRevealedImprovementType(GC.getGameINLINE().getActiveTeam(), false);
	} else {
		eImprovement = getImprovementType();
	}

	if (eImprovement != NO_IMPROVEMENT) {
		if (eDefender != NO_TEAM && (getTeam() == NO_TEAM || GET_TEAM(eDefender).isFriendlyTerritory(getTeam()))) {
			iModifier += GC.getImprovementInfo(eImprovement).getDefenseModifier();
		}
	}

	if (!bHelp) {
		CvCity* pCity = getPlotCity();

		if (pCity != NULL) {
			iModifier += pCity->getDefenseModifier(bIgnoreBuilding);
		}
	}

	return iModifier;
}


int CvPlot::movementCost(const CvUnit* pUnit, const CvPlot* pFromPlot) const {
	FAssertMsg(getTerrainType() != NO_TERRAIN, "TerrainType is not assigned a valid value");

	if (pUnit->flatMovementCost() || (pUnit->getDomainType() == DOMAIN_AIR)) {
		return GC.getMOVE_DENOMINATOR();
	}

	if (!isRevealed(pUnit->getTeam(), false)) {
		return GC.getMOVE_DENOMINATOR();
	}

	if (!pFromPlot->isValidDomainForLocation(*pUnit)) {
		return pUnit->maxMoves();
	}

	if (!isValidDomainForAction(*pUnit)) {
		return GC.getMOVE_DENOMINATOR();
	}

	FAssert(pUnit->getDomainType() != DOMAIN_IMMOBILE);

	int iRegularCost = 0;
	if (pUnit->ignoreTerrainCost()) {
		iRegularCost = 1;
	} else {
		iRegularCost = ((getFeatureType() == NO_FEATURE) ? GC.getTerrainInfo(getTerrainType()).getMovementCost() : GC.getFeatureInfo(getFeatureType()).getMovementCost());

		if (isHills()) {
			iRegularCost += GC.getHILLS_EXTRA_MOVEMENT();
		}

		if (isPeak()) {
			if (GET_TEAM(pUnit->getTeam()).isMoveFastPeaks()) {
				iRegularCost += 1;
			} else {
				iRegularCost += GC.getPEAK_EXTRA_MOVEMENT();
			}
		}

		if (iRegularCost > 0) {
			iRegularCost = std::max(1, (iRegularCost - pUnit->getExtraMoveDiscount()));
		}
	}

	bool bHasTerrainCost = (iRegularCost > 1);

	iRegularCost = std::min(iRegularCost, pUnit->baseMoves());

	iRegularCost *= GC.getMOVE_DENOMINATOR();

	if (bHasTerrainCost) {
		if (((getFeatureType() == NO_FEATURE) ? pUnit->isTerrainDoubleMove(getTerrainType()) : pUnit->isFeatureDoubleMove(getFeatureType())) ||
			(isHills() && pUnit->isHillsDoubleMove())) {
			iRegularCost /= 2;
		}
	}

	int iRouteCost = 0;
	int iRouteFlatCost = 0;
	if (pFromPlot->isValidRoute(pUnit) && isValidRoute(pUnit) && ((GET_TEAM(pUnit->getTeam()).isBridgeBuilding() || !(pFromPlot->isRiverCrossing(directionXY(pFromPlot, this)))))) {
		iRouteCost = std::max((GC.getRouteInfo(pFromPlot->getRouteType()).getMovementCost() + GET_TEAM(pUnit->getTeam()).getRouteChange(pFromPlot->getRouteType())),
			(GC.getRouteInfo(getRouteType()).getMovementCost() + GET_TEAM(pUnit->getTeam()).getRouteChange(getRouteType())));
		iRouteFlatCost = std::max((GC.getRouteInfo(pFromPlot->getRouteType()).getFlatMovementCost() * pUnit->baseMoves()),
			(GC.getRouteInfo(getRouteType()).getFlatMovementCost() * pUnit->baseMoves()));
	} else {
		iRouteCost = MAX_INT;
		iRouteFlatCost = MAX_INT;
	}

	return std::max(1, std::min(iRegularCost, std::min(iRouteCost, iRouteFlatCost)));
}

int CvPlot::getExtraMovePathCost() const {
	return GC.getGameINLINE().getPlotExtraCost(getX_INLINE(), getY_INLINE());
}


void CvPlot::changeExtraMovePathCost(int iChange) {
	GC.getGameINLINE().changePlotExtraCost(getX_INLINE(), getY_INLINE(), iChange);
}

bool CvPlot::isAdjacentOwned() const {
	for (int iI = 0; iI < NUM_DIRECTION_TYPES; ++iI) {
		CvPlot* pAdjacentPlot = plotDirection(getX_INLINE(), getY_INLINE(), ((DirectionTypes)iI));

		if (pAdjacentPlot != NULL) {
			if (pAdjacentPlot->isOwned()) {
				return true;
			}
		}
	}

	return false;
}


bool CvPlot::isAdjacentPlayer(PlayerTypes ePlayer, bool bLandOnly) const {
	for (int iI = 0; iI < NUM_DIRECTION_TYPES; ++iI) {
		CvPlot* pAdjacentPlot = plotDirection(getX_INLINE(), getY_INLINE(), ((DirectionTypes)iI));

		if (pAdjacentPlot != NULL) {
			if (pAdjacentPlot->getOwnerINLINE() == ePlayer) {
				if (!bLandOnly || !(pAdjacentPlot->isWater())) {
					return true;
				}
			}
		}
	}

	return false;
}


bool CvPlot::isAdjacentTeam(TeamTypes eTeam, bool bLandOnly) const {
	for (int iI = 0; iI < NUM_DIRECTION_TYPES; ++iI) {
		CvPlot* pAdjacentPlot = plotDirection(getX_INLINE(), getY_INLINE(), ((DirectionTypes)iI));

		if (pAdjacentPlot != NULL) {
			if (pAdjacentPlot->getTeam() == eTeam) {
				if (!bLandOnly || !(pAdjacentPlot->isWater())) {
					return true;
				}
			}
		}
	}

	return false;
}


bool CvPlot::isWithinCultureRange(PlayerTypes ePlayer) const {
	for (int iI = 0; iI < GC.getNumCultureLevelInfos(); ++iI) {
		if (isCultureRangeCity(ePlayer, iI)) {
			return true;
		}
	}

	return false;
}


int CvPlot::getNumCultureRangeCities(PlayerTypes ePlayer) const {
	int iCount = 0;

	for (int iI = 0; iI < GC.getNumCultureLevelInfos(); ++iI) {
		iCount += getCultureRangeCities(ePlayer, iI);
	}

	return iCount;
}

bool CvPlot::isHasPathToEnemyCity(TeamTypes eAttackerTeam, bool bIgnoreBarb) {
	PROFILE_FUNC();

	FAssert(eAttackerTeam != NO_TEAM);

	if ((area()->getNumCities() - GET_TEAM(eAttackerTeam).countNumCitiesByArea(area())) == 0) {
		return false;
	}

	// Imitate instatiation of irrigated finder, pIrrigatedFinder
	// Can't mimic step finder initialization because it requires creation from the exe
	std::vector<TeamTypes> teamVec;
	teamVec.push_back(eAttackerTeam);
	teamVec.push_back(NO_TEAM);
	FAStar* pTeamStepFinder = gDLL->getFAStarIFace()->create();
	gDLL->getFAStarIFace()->Initialize(pTeamStepFinder, GC.getMapINLINE().getGridWidthINLINE(), GC.getMapINLINE().getGridHeightINLINE(), GC.getMapINLINE().isWrapXINLINE(), GC.getMapINLINE().isWrapYINLINE(), stepDestValid, stepHeuristic, stepCost, teamStepValid, stepAdd, NULL, NULL);
	gDLL->getFAStarIFace()->SetData(pTeamStepFinder, &teamVec);

	bool bFound = false;

	// First check capitals
	for (int iI = 0; !bFound && iI < MAX_CIV_PLAYERS; iI++) {
		if (GET_PLAYER((PlayerTypes)iI).isAlive() && GET_TEAM(eAttackerTeam).AI_getWarPlan(GET_PLAYER((PlayerTypes)iI).getTeam()) != NO_WARPLAN) {
			if (!bIgnoreBarb || !(GET_PLAYER((PlayerTypes)iI).isBarbarian() || GET_PLAYER((PlayerTypes)iI).isMinorCiv())) {
				CvCity* pLoopCity = GET_PLAYER((PlayerTypes)iI).getCapitalCity();

				if (pLoopCity != NULL) {
					if ((pLoopCity->area() == area())) {
						bFound = gDLL->getFAStarIFace()->GeneratePath(pTeamStepFinder, getX_INLINE(), getY_INLINE(), pLoopCity->getX_INLINE(), pLoopCity->getY_INLINE(), false, 0, true);

						if (bFound) {
							break;
						}
					}
				}
			}
		}
	}

	// Check all other cities
	for (int iI = 0; !bFound && iI < MAX_PLAYERS; iI++) {
		if (GET_PLAYER((PlayerTypes)iI).isAlive() && GET_TEAM(eAttackerTeam).AI_getWarPlan(GET_PLAYER((PlayerTypes)iI).getTeam()) != NO_WARPLAN) {
			if (!bIgnoreBarb || !(GET_PLAYER((PlayerTypes)iI).isBarbarian() || GET_PLAYER((PlayerTypes)iI).isMinorCiv())) {
				int iLoop;
				for (CvCity* pLoopCity = GET_PLAYER((PlayerTypes)iI).firstCity(&iLoop); !bFound && pLoopCity != NULL; pLoopCity = GET_PLAYER((PlayerTypes)iI).nextCity(&iLoop)) {
					if ((pLoopCity->area() == area()) && !(pLoopCity->isCapital())) {
						bFound = gDLL->getFAStarIFace()->GeneratePath(pTeamStepFinder, getX_INLINE(), getY_INLINE(), pLoopCity->getX_INLINE(), pLoopCity->getY_INLINE(), false, 0, true);

						if (bFound) {
							break;
						}
					}
				}
			}
		}
	}

	gDLL->getFAStarIFace()->destroy(pTeamStepFinder);

	return bFound;
}

bool CvPlot::isHasPathToPlayerCity(TeamTypes eMoveTeam, PlayerTypes eOtherPlayer) {
	PROFILE_FUNC();

	FAssert(eMoveTeam != NO_TEAM);

	if ((area()->getCitiesPerPlayer(eOtherPlayer) == 0)) {
		return false;
	}

	// Imitate instatiation of irrigated finder, pIrrigatedFinder
	// Can't mimic step finder initialization because it requires creation from the exe
	std::vector<TeamTypes> teamVec;
	teamVec.push_back(eMoveTeam);
	teamVec.push_back(GET_PLAYER(eOtherPlayer).getTeam());
	FAStar* pTeamStepFinder = gDLL->getFAStarIFace()->create();
	gDLL->getFAStarIFace()->Initialize(pTeamStepFinder, GC.getMapINLINE().getGridWidthINLINE(), GC.getMapINLINE().getGridHeightINLINE(), GC.getMapINLINE().isWrapXINLINE(), GC.getMapINLINE().isWrapYINLINE(), stepDestValid, stepHeuristic, stepCost, teamStepValid, stepAdd, NULL, NULL);
	gDLL->getFAStarIFace()->SetData(pTeamStepFinder, &teamVec);

	bool bFound = false;

	int iLoop;
	for (CvCity* pLoopCity = GET_PLAYER(eOtherPlayer).firstCity(&iLoop); !bFound && pLoopCity != NULL; pLoopCity = GET_PLAYER(eOtherPlayer).nextCity(&iLoop)) {
		if (pLoopCity->area() == area()) {
			bFound = gDLL->getFAStarIFace()->GeneratePath(pTeamStepFinder, getX_INLINE(), getY_INLINE(), pLoopCity->getX_INLINE(), pLoopCity->getY_INLINE(), false, 0, true);

			if (bFound) {
				break;
			}
		}
	}

	gDLL->getFAStarIFace()->destroy(pTeamStepFinder);

	return bFound;
}

int CvPlot::calculatePathDistanceToPlot(TeamTypes eTeam, CvPlot* pTargetPlot) {
	PROFILE_FUNC();

	FAssert(eTeam != NO_TEAM);

	if (pTargetPlot->area() != area()) {
		return false;
	}

	// Imitate instatiation of irrigated finder, pIrrigatedFinder
	// Can't mimic step finder initialization because it requires creation from the exe
	std::vector<TeamTypes> teamVec;
	teamVec.push_back(eTeam);
	teamVec.push_back(NO_TEAM);
	FAStar* pTeamStepFinder = gDLL->getFAStarIFace()->create();
	gDLL->getFAStarIFace()->Initialize(pTeamStepFinder, GC.getMapINLINE().getGridWidthINLINE(), GC.getMapINLINE().getGridHeightINLINE(), GC.getMapINLINE().isWrapXINLINE(), GC.getMapINLINE().isWrapYINLINE(), stepDestValid, stepHeuristic, stepCost, teamStepValid, stepAdd, NULL, NULL);
	gDLL->getFAStarIFace()->SetData(pTeamStepFinder, &teamVec);

	int iPathDistance = -1;
	gDLL->getFAStarIFace()->GeneratePath(pTeamStepFinder, getX_INLINE(), getY_INLINE(), pTargetPlot->getX_INLINE(), pTargetPlot->getY_INLINE(), false, 0, true);

	FAStarNode* pNode = gDLL->getFAStarIFace()->GetLastNode(&GC.getStepFinder());

	if (pNode != NULL) {
		iPathDistance = pNode->m_iData1;
	}

	gDLL->getFAStarIFace()->destroy(pTeamStepFinder);

	return iPathDistance;
}

// I've changed the purpose of this function - because this is the way it is always used.
void CvPlot::invalidateBorderDangerCache() {
	for (int iDX = -BORDER_DANGER_RANGE; iDX <= BORDER_DANGER_RANGE; iDX++) {
		for (int iDY = -BORDER_DANGER_RANGE; iDY <= BORDER_DANGER_RANGE; iDY++) {
			CvPlot* pLoopPlot = plotXY(getX_INLINE(), getY_INLINE(), iDX, iDY);

			if (pLoopPlot) {
				for (TeamTypes i = (TeamTypes)0; i < MAX_TEAMS; i = (TeamTypes)(i + 1)) {
					pLoopPlot->setBorderDangerCache(i, false);
				}
			}
		}
	}
	//
}

PlayerTypes CvPlot::calculateCulturalOwner() const {
	PROFILE("CvPlot::calculateCulturalOwner()")

		if (isForceUnowned()) {
			return NO_PLAYER;
		}

	int iBestCulture = 0;
	PlayerTypes eBestPlayer = NO_PLAYER;

	for (int iI = 0; iI < MAX_PLAYERS; ++iI) {
		if (GET_PLAYER((PlayerTypes)iI).isAlive()) {
			int iCulture = getCulture((PlayerTypes)iI);

			if (iCulture > 0) {
				if (isWithinCultureRange((PlayerTypes)iI)) {
					if ((iCulture > iBestCulture) || ((iCulture == iBestCulture) && (getOwnerINLINE() == iI))) {
						iBestCulture = iCulture;
						eBestPlayer = ((PlayerTypes)iI);
					}
				}
			}
		}
	}

	if (!isCity()) {
		if (eBestPlayer != NO_PLAYER) {
			int iBestPriority = MAX_INT;
			CvCity* pBestCity = NULL;

			for (int iI = 0; iI < NUM_CITY_PLOTS; ++iI) {
				CvPlot* pLoopPlot = plotCity(getX_INLINE(), getY_INLINE(), iI);

				if (pLoopPlot != NULL) {
					CvCity* pLoopCity = pLoopPlot->getPlotCity();

					if (pLoopCity != NULL) {
						if (pLoopCity->getTeam() == GET_PLAYER(eBestPlayer).getTeam() || GET_TEAM(GET_PLAYER(eBestPlayer).getTeam()).isVassal(pLoopCity->getTeam())) {
							if (getCulture(pLoopCity->getOwnerINLINE()) > 0) {
								if (isWithinCultureRange(pLoopCity->getOwnerINLINE())) {
									int iPriority = GC.getCityPlotPriority()[iI];

									if (pLoopCity->getTeam() == GET_PLAYER(eBestPlayer).getTeam()) {
										iPriority += 5; // priority ranges from 0 to 4 -> give priority to Masters of a Vassal
									}

									if ((iPriority < iBestPriority) || ((iPriority == iBestPriority) && (pLoopCity->getOwnerINLINE() == eBestPlayer))) {
										iBestPriority = iPriority;
										pBestCity = pLoopCity;
									}
								}
							}
						}
					}
				}
			}

			if (pBestCity != NULL) {
				eBestPlayer = pBestCity->getOwnerINLINE();
			}
		}
	}

	if (eBestPlayer == NO_PLAYER) {
		bool bValid = true;

		for (int iI = 0; iI < NUM_CARDINALDIRECTION_TYPES; ++iI) {
			CvPlot* pLoopPlot = plotCardinalDirection(getX_INLINE(), getY_INLINE(), ((CardinalDirectionTypes)iI));

			if (pLoopPlot != NULL) {
				if (pLoopPlot->isOwned()) {
					if (eBestPlayer == NO_PLAYER) {
						eBestPlayer = pLoopPlot->getOwnerINLINE();
					} else if (eBestPlayer != pLoopPlot->getOwnerINLINE()) {
						bValid = false;
						break;
					}
				} else {
					bValid = false;
					break;
				}
			}
		}

		if (!bValid) {
			eBestPlayer = NO_PLAYER;
		}
	}

	return eBestPlayer;
}


void CvPlot::plotAction(PlotUnitFunc func, int iData1, int iData2, PlayerTypes eOwner, TeamTypes eTeam) {
	CLLNode<IDInfo>* pUnitNode = headUnitNode();

	while (pUnitNode != NULL) {
		CvUnit* pLoopUnit = ::getUnit(pUnitNode->m_data);
		pUnitNode = nextUnitNode(pUnitNode);

		if ((eOwner == NO_PLAYER) || (pLoopUnit->getOwnerINLINE() == eOwner)) {
			if ((eTeam == NO_TEAM) || (pLoopUnit->getTeam() == eTeam)) {
				func(pLoopUnit, iData1, iData2);
			}
		}
	}
}


int CvPlot::plotCount(ConstPlotUnitFunc funcA, int iData1A, int iData2A, PlayerTypes eOwner, TeamTypes eTeam, ConstPlotUnitFunc funcB, int iData1B, int iData2B) const {
	int iCount = 0;

	CLLNode<IDInfo>* pUnitNode = headUnitNode();

	while (pUnitNode != NULL) {
		CvUnit* pLoopUnit = ::getUnit(pUnitNode->m_data);
		pUnitNode = nextUnitNode(pUnitNode);

		if ((eOwner == NO_PLAYER) || (pLoopUnit->getOwnerINLINE() == eOwner)) {
			if ((eTeam == NO_TEAM) || (pLoopUnit->getTeam() == eTeam)) {
				if ((funcA == NULL) || funcA(pLoopUnit, iData1A, iData2A)) {
					if ((funcB == NULL) || funcB(pLoopUnit, iData1B, iData2B)) {
						iCount++;
					}
				}
			}
		}
	}

	return iCount;
}


CvUnit* CvPlot::plotCheck(ConstPlotUnitFunc funcA, int iData1A, int iData2A, PlayerTypes eOwner, TeamTypes eTeam, ConstPlotUnitFunc funcB, int iData1B, int iData2B) const {
	CLLNode<IDInfo>* pUnitNode = headUnitNode();

	while (pUnitNode != NULL) {
		CvUnit* pLoopUnit = ::getUnit(pUnitNode->m_data);
		pUnitNode = nextUnitNode(pUnitNode);

		if ((eOwner == NO_PLAYER) || (pLoopUnit->getOwnerINLINE() == eOwner)) {
			if ((eTeam == NO_TEAM) || (pLoopUnit->getTeam() == eTeam)) {
				if (funcA(pLoopUnit, iData1A, iData2A)) {
					if ((funcB == NULL) || funcB(pLoopUnit, iData1B, iData2B)) {
						return pLoopUnit;
					}
				}
			}
		}
	}

	return NULL;
}


bool CvPlot::isOwned() const {
	return (getOwnerINLINE() != NO_PLAYER);
}


bool CvPlot::isBarbarian() const {
	return (getOwnerINLINE() == BARBARIAN_PLAYER);
}


bool CvPlot::isRevealedBarbarian() const {
	return (getRevealedOwner(GC.getGameINLINE().getActiveTeam(), true) == BARBARIAN_PLAYER);
}


bool CvPlot::isVisible(TeamTypes eTeam, bool bDebug) const {
	if (bDebug && GC.getGameINLINE().isDebugMode()) {
		return true;
	} else {
		if (eTeam == NO_TEAM) {
			return false;
		}

		return ((getVisibilityCount(eTeam) > 0) || (getStolenVisibilityCount(eTeam) > 0));
	}
}


bool CvPlot::isActiveVisible(bool bDebug) const {
	return isVisible(GC.getGameINLINE().getActiveTeam(), bDebug);
}


bool CvPlot::isVisibleToCivTeam() const {
	for (int iI = 0; iI < MAX_CIV_TEAMS; ++iI) {
		if (GET_TEAM((TeamTypes)iI).isAlive()) {
			if (isVisible(((TeamTypes)iI), false)) {
				return true;
			}
		}
	}

	return false;
}


bool CvPlot::isVisibleToWatchingHuman() const {
	for (int iI = 0; iI < MAX_CIV_PLAYERS; ++iI) {
		if (GET_PLAYER((PlayerTypes)iI).isAlive()) {
			if (GET_PLAYER((PlayerTypes)iI).isHuman()) {
				if (isVisible(GET_PLAYER((PlayerTypes)iI).getTeam(), false)) {
					return true;
				}
			}
		}
	}

	return false;
}


bool CvPlot::isAdjacentVisible(TeamTypes eTeam, bool bDebug) const {
	for (int iI = 0; iI < NUM_DIRECTION_TYPES; ++iI) {
		CvPlot* pAdjacentPlot = plotDirection(getX_INLINE(), getY_INLINE(), ((DirectionTypes)iI));

		if (pAdjacentPlot != NULL) {
			if (pAdjacentPlot->isVisible(eTeam, bDebug)) {
				return true;
			}
		}
	}

	return false;
}

bool CvPlot::isAdjacentNonvisible(TeamTypes eTeam) const {
	for (int iI = 0; iI < NUM_DIRECTION_TYPES; ++iI) {
		CvPlot* pAdjacentPlot = plotDirection(getX_INLINE(), getY_INLINE(), ((DirectionTypes)iI));

		if (pAdjacentPlot != NULL) {
			if (!pAdjacentPlot->isVisible(eTeam, false)) {
				return true;
			}
		}
	}

	return false;
}


bool CvPlot::isGoody(TeamTypes eTeam) const {
	if ((eTeam != NO_TEAM) && GET_TEAM(eTeam).isBarbarian()) {
		return false;
	}

	return ((getImprovementType() == NO_IMPROVEMENT) ? false : GC.getImprovementInfo(getImprovementType()).isGoody());
}


bool CvPlot::isRevealedGoody(TeamTypes eTeam) const {
	if (eTeam == NO_TEAM) {
		return isGoody();
	}

	if (getImprovementType() != NO_IMPROVEMENT) {
		const CvImprovementInfo& kImprovement = GC.getImprovementInfo((ImprovementTypes)getImprovementType());
		if (kImprovement.isAnySpawn() && !kImprovement.isPermanent()) {
			if (atWar(eTeam, GET_PLAYER(BARBARIAN_PLAYER).getTeam())) {
				return true;
			}
		}
	}

	if (GET_TEAM(eTeam).isBarbarian()) {
		return false;
	}

	return ((getRevealedImprovementType(eTeam, false) == NO_IMPROVEMENT) ? false : GC.getImprovementInfo(getRevealedImprovementType(eTeam, false)).isGoody());
}


void CvPlot::removeGoody() {
	setImprovementType(NO_IMPROVEMENT);
}


bool CvPlot::isCity(bool bCheckImprovement, TeamTypes eForTeam) const {
	if (bCheckImprovement && NO_IMPROVEMENT != getImprovementType()) {
		if (GC.getImprovementInfo(getImprovementType()).isActsAsCity()) {
			if (NO_TEAM == eForTeam || (NO_TEAM == getTeam() && GC.getImprovementInfo(getImprovementType()).isOutsideBorders()) || GET_TEAM(eForTeam).isFriendlyTerritory(getTeam())) {
				return true;
			}
		}
	}

	return (getPlotCity() != NULL);
}


bool CvPlot::isFriendlyCity(const CvUnit& kUnit, bool bCheckImprovement) const {
	if (!isCity(bCheckImprovement, kUnit.getTeam())) {
		return false;
	}

	if (isVisibleEnemyUnit(&kUnit)) {
		return false;
	}

	TeamTypes ePlotTeam = getTeam();

	if (NO_TEAM != ePlotTeam) {
		if (kUnit.isEnemy(ePlotTeam)) {
			return false;
		}

		TeamTypes eTeam = GET_PLAYER(kUnit.getCombatOwner(ePlotTeam, this)).getTeam();

		if (eTeam == ePlotTeam) {
			return true;
		}

		if (GET_TEAM(eTeam).isOpenBorders(ePlotTeam)) {
			return true;
		}

		if (GET_TEAM(ePlotTeam).isVassal(eTeam)) {
			return true;
		}
	}

	return false;
}


bool CvPlot::isEnemyCity(const CvUnit& kUnit) const {
	CvCity* pCity = getPlotCity();

	if (pCity != NULL) {
		return kUnit.isEnemy(pCity->getTeam(), this);
	}

	return false;
}


bool CvPlot::isOccupation() const {
	CvCity* pCity = getPlotCity();

	if (pCity != NULL) {
		return pCity->isOccupation();
	}

	return false;
}


bool CvPlot::isBeingWorked() const {
	CvCity* pWorkingCity = getWorkingCity();

	if (pWorkingCity != NULL) {
		return pWorkingCity->isWorkingPlot(this);
	}

	return false;
}


bool CvPlot::isUnit() const {
	return (getNumUnits() > 0);
}


bool CvPlot::isInvestigate(TeamTypes eTeam) const {
	return (plotCheck(PUF_isInvestigate, -1, -1, NO_PLAYER, eTeam) != NULL);
}


bool CvPlot::isVisibleEnemyDefender(const CvUnit* pUnit) const {
	return (plotCheck(PUF_canDefendEnemy, pUnit->getOwnerINLINE(), pUnit->isAlwaysHostile(this), NO_PLAYER, NO_TEAM, PUF_isVisible, pUnit->getOwnerINLINE()) != NULL);
}


CvUnit* CvPlot::getVisibleEnemyDefender(PlayerTypes ePlayer) const {
	return plotCheck(PUF_canDefendEnemy, ePlayer, false, NO_PLAYER, NO_TEAM, PUF_isVisible, ePlayer);
}


int CvPlot::getNumDefenders(PlayerTypes ePlayer) const {
	return plotCount(PUF_canDefend, -1, -1, ePlayer);
}


int CvPlot::getNumVisibleEnemyDefenders(const CvUnit* pUnit) const {
	return plotCount(PUF_canDefendEnemy, pUnit->getOwnerINLINE(), pUnit->isAlwaysHostile(this), NO_PLAYER, NO_TEAM, PUF_isVisible, pUnit->getOwnerINLINE());
}


int CvPlot::getNumVisiblePotentialEnemyDefenders(const CvUnit* pUnit) const {
	return plotCount(PUF_canDefendPotentialEnemy, pUnit->getOwnerINLINE(), pUnit->isAlwaysHostile(this), NO_PLAYER, NO_TEAM, PUF_isVisible, pUnit->getOwnerINLINE());
}


bool CvPlot::isVisibleEnemyUnit(PlayerTypes ePlayer) const {
	return (plotCheck(PUF_isEnemy, ePlayer, false, NO_PLAYER, NO_TEAM, PUF_isVisible, ePlayer) != NULL);
}

// K-Mod
bool CvPlot::isVisiblePotentialEnemyUnit(PlayerTypes ePlayer) const {
	return plotCheck(PUF_isPotentialEnemy, ePlayer, false, NO_PLAYER, NO_TEAM, PUF_isVisible, ePlayer) != NULL;
}
int CvPlot::getNumVisibleUnits(PlayerTypes ePlayer) const {
	return plotCount(PUF_isVisibleDebug, ePlayer);
}


bool CvPlot::isVisibleEnemyUnit(const CvUnit* pUnit) const {
	return (plotCheck(PUF_isEnemy, pUnit->getOwnerINLINE(), pUnit->isAlwaysHostile(this), NO_PLAYER, NO_TEAM, PUF_isVisible, pUnit->getOwnerINLINE()) != NULL);
}

bool CvPlot::isVisibleOtherUnit(PlayerTypes ePlayer) const {
	return (plotCheck(PUF_isOtherTeam, ePlayer, -1, NO_PLAYER, NO_TEAM, PUF_isVisible, ePlayer) != NULL);
}


bool CvPlot::isFighting() const {
	return (plotCheck(PUF_isFighting) != NULL);
}


bool CvPlot::canHaveFeature(FeatureTypes eFeature, bool bIgnoreLatitude) const {
	FAssertMsg(getTerrainType() != NO_TERRAIN, "TerrainType is not assigned a valid value");

	if (eFeature == NO_FEATURE) {
		return true;
	}

	if (getFeatureType() != NO_FEATURE) {
		return false;
	}

	if (isPeak()) {
		return false;
	}

	if (isCity()) {
		return false;
	}

	const CvFeatureInfo& kFeature = GC.getFeatureInfo(eFeature);
	if (!kFeature.isTerrain(getTerrainType())) {
		return false;
	}

	if (!bIgnoreLatitude) {
		if (getLatitude() > kFeature.getMaxLatitude())
			return false;

		if (getLatitude() < kFeature.getMinLatitude())
			return false;
	}

	if (kFeature.isNoCoast() && isCoastalLand()) {
		return false;
	}

	if ((kFeature.getMinAdjacentWaterSize() > 0 || kFeature.getMaxAdjacentWaterSize() < MAX_INT) && !isCoastalLand(kFeature.getMinAdjacentWaterSize(), kFeature.getMaxAdjacentWaterSize())) {
		return false;
	}

	if (kFeature.isNoRiver() && isRiver()) {
		return false;
	}

	if (kFeature.isRequiresFlatlands() && isHills()) {
		return false;
	}

	if (kFeature.isNoAdjacent()) {
		for (int iI = 0; iI < NUM_DIRECTION_TYPES; ++iI) {
			CvPlot* pAdjacentPlot = plotDirection(getX_INLINE(), getY_INLINE(), ((DirectionTypes)iI));

			if (pAdjacentPlot != NULL) {
				if (pAdjacentPlot->getFeatureType() == eFeature) {
					return false;
				}
			}
		}
	}

	if (kFeature.isRequiresRiver() && !isRiver()) {
		return false;
	}

	return true;
}


bool CvPlot::isRoute() const {
	return (getRouteType() != NO_ROUTE);
}


bool CvPlot::isValidRoute(const CvUnit* pUnit) const {
	if (isRoute()) {
		if (!pUnit->isEnemy(getTeam(), this) || pUnit->isEnemyRoute()) {
			return true;
		}
	}

	return false;
}


bool CvPlot::isTradeNetworkImpassable(TeamTypes eTeam) const {
	return (isImpassable(eTeam) && !isRiverNetwork(eTeam));
}

bool CvPlot::isRiverNetwork(TeamTypes eTeam) const {
	if (!isRiver()) {
		return false;
	}

	if (GET_TEAM(eTeam).isRiverTrade()) {
		return true;
	}

	if (getTeam() == eTeam) {
		return true;
	}

	return false;
}

bool CvPlot::isNetworkTerrain(TeamTypes eTeam) const {
	FAssertMsg(eTeam != NO_TEAM, "eTeam is not assigned a valid value");
	FAssertMsg(getTerrainType() != NO_TERRAIN, "TerrainType is not assigned a valid value");

	if (GET_TEAM(eTeam).isTerrainTrade(getTerrainType())) {
		return true;
	}

	if (isWater()) {
		if (getTeam() == eTeam) {
			return true;
		}
	}

	return false;
}


bool CvPlot::isBonusNetwork(TeamTypes eTeam) const {
	if (isRoute()) {
		return true;
	}

	if (isRiverNetwork(eTeam)) {
		return true;
	}

	if (isNetworkTerrain(eTeam)) {
		return true;
	}

	return false;
}


bool CvPlot::isTradeNetwork(TeamTypes eTeam) const {
	FAssertMsg(eTeam != NO_TEAM, "eTeam is not assigned a valid value");

	if (atWar(eTeam, getTeam())) {
		return false;
	}

	if (getBlockadedCount(eTeam) > 0) {
		return false;
	}

	if (isTradeNetworkImpassable(eTeam)) {
		return false;
	}

	if (!isOwned()) {
		if (!isRevealed(eTeam, false)) {
			return false;
		}
	}

	return isBonusNetwork(eTeam);
}


bool CvPlot::isTradeNetworkConnected(const CvPlot* pPlot, TeamTypes eTeam) const {
	FAssertMsg(eTeam != NO_TEAM, "eTeam is not assigned a valid value");

	if (atWar(eTeam, getTeam()) || atWar(eTeam, pPlot->getTeam())) {
		return false;
	}

	if (isTradeNetworkImpassable(eTeam) || pPlot->isTradeNetworkImpassable(eTeam)) {
		return false;
	}

	if (!isOwned()) {
		if (!isRevealed(eTeam, false) || !(pPlot->isRevealed(eTeam, false))) {
			return false;
		}
	}

	if (isRoute()) {
		if (pPlot->isRoute()) {
			return true;
		}
	}

	if (isCity(true, eTeam)) {
		if (pPlot->isNetworkTerrain(eTeam)) {
			return true;
		}
	}

	if (isNetworkTerrain(eTeam)) {
		if (pPlot->isCity(true, eTeam)) {
			return true;
		}

		if (pPlot->isNetworkTerrain(eTeam)) {
			return true;
		}

		if (pPlot->isRiverNetwork(eTeam)) {
			if (pPlot->isRiverConnection(directionXY(pPlot, this))) {
				return true;
			}
		}
	}

	if (isRiverNetwork(eTeam)) {
		if (pPlot->isNetworkTerrain(eTeam)) {
			if (isRiverConnection(directionXY(this, pPlot))) {
				return true;
			}
		}

		if (isRiverConnection(directionXY(this, pPlot)) || pPlot->isRiverConnection(directionXY(pPlot, this))) {
			if (pPlot->isRiverNetwork(eTeam)) {
				return true;
			}
		}
	}

	return false;
}


bool CvPlot::isValidDomainForLocation(const CvUnit& unit) const {
	if (isValidDomainForAction(unit)) {
		return true;
	}

	return isCity(true, unit.getTeam());
}


bool CvPlot::isValidDomainForAction(const CvUnit& unit) const {
	switch (unit.getDomainType()) {
	case DOMAIN_SEA:
		return (isWater() || unit.canMoveAllTerrain());
		break;

	case DOMAIN_AIR:
		return false;
		break;

	case DOMAIN_LAND:
		return (!isWater() || unit.canMoveAllTerrain() || isLandUnitWaterSafe());
		break;

	case DOMAIN_IMMOBILE:
		return (!isWater() || unit.canMoveAllTerrain());
		break;

	default:
		FAssert(false);
		break;
	}

	return false;
}


bool CvPlot::isImpassable(TeamTypes eTeam, bool bIgnoreUniqueFeature) const {
	if (isPeak()) {
		if (eTeam == NO_TEAM || !GET_TEAM(eTeam).isCanPassPeaks()) {
			return true;
		}
	}

	if (getTerrainType() == NO_TERRAIN) {
		return false;
	}

	FeatureTypes eFeature = getFeatureType();
	if (eFeature != NO_FEATURE) {
		const CvFeatureInfo& kFeature = GC.getFeatureInfo(getFeatureType());
		if (kFeature.isImpassable()) {
			return !(bIgnoreUniqueFeature && GC.getFeatureInfo(getFeatureType()).isUnique());
		}
	}
	return GC.getTerrainInfo(getTerrainType()).isImpassable();
}


int CvPlot::getX() const {
	return m_iX;
}


int CvPlot::getY() const {
	return m_iY;
}


bool CvPlot::at(int iX, int iY) const {
	return ((getX_INLINE() == iX) && (getY_INLINE() == iY));
}


int CvPlot::getLatitude() const {
	double fLatitude = 0.0f;
	if (GC.getMapINLINE().isWrapXINLINE() || !(GC.getMapINLINE().isWrapYINLINE())) {
		fLatitude = ((getY_INLINE() * 1.0) / (GC.getMapINLINE().getGridHeightINLINE() - 1));
	} else {
		fLatitude = ((getX_INLINE() * 1.0) / (GC.getMapINLINE().getGridWidthINLINE() - 1));
	}

	fLatitude = fLatitude * (GC.getMapINLINE().getTopLatitude() - GC.getMapINLINE().getBottomLatitude());

	int iLatitude = (int)(fLatitude + 0.5);
	return abs((iLatitude + GC.getMapINLINE().getBottomLatitude()));
}


int CvPlot::getFOWIndex() const {
	return ((((GC.getMapINLINE().getGridHeight() - 1) - getY_INLINE()) * GC.getMapINLINE().getGridWidth() * LANDSCAPE_FOW_RESOLUTION * LANDSCAPE_FOW_RESOLUTION) + (getX_INLINE() * LANDSCAPE_FOW_RESOLUTION));
}


CvArea* CvPlot::area() const {
	if (m_pPlotArea == NULL) {
		m_pPlotArea = GC.getMapINLINE().getArea(getArea());
	}

	return m_pPlotArea;
}

CvArea* CvPlot::waterArea(bool bNoImpassable) const {
	if (isWater()) {
		return area();
	}

	int iBestValue = 0;
	CvArea* pBestArea = NULL;

	for (int iI = 0; iI < NUM_DIRECTION_TYPES; ++iI) {
		CvPlot* pAdjacentPlot = plotDirection(getX_INLINE(), getY_INLINE(), ((DirectionTypes)iI));

		if (pAdjacentPlot != NULL) {
			if (pAdjacentPlot->isWater() && (!bNoImpassable || !(pAdjacentPlot->isImpassable()))) {
				int iValue = pAdjacentPlot->area()->getNumTiles();

				if (iValue > iBestValue) {
					iBestValue = iValue;
					pBestArea = pAdjacentPlot->area();
				}
			}
		}
	}

	return pBestArea;
}

CvArea* CvPlot::secondWaterArea() const {
	FAssert(!isWater());

	int iBestValue = 0;
	CvArea* pWaterArea = waterArea();
	CvArea* pBestArea = NULL;

	for (int iI = 0; iI < NUM_DIRECTION_TYPES; ++iI) {
		CvPlot* pAdjacentPlot = plotDirection(getX_INLINE(), getY_INLINE(), ((DirectionTypes)iI));

		if (pAdjacentPlot != NULL) {
			if (pAdjacentPlot->isWater() && (pAdjacentPlot->getArea() != pWaterArea->getID())) {
				int iValue = pAdjacentPlot->area()->getNumTiles();

				if (iValue > iBestValue) {
					iBestValue = iValue;
					pBestArea = pAdjacentPlot->area();
				}
			}
		}
	}

	return pBestArea;

}


int CvPlot::getArea() const {
	return m_iArea;
}


void CvPlot::setArea(int iNewValue) {
	if (getArea() != iNewValue) {
		if (area() != NULL) {
			processArea(area(), -1);
		}

		m_iArea = iNewValue;
		m_pPlotArea = NULL;

		if (area() != NULL) {
			processArea(area(), 1);

			updateIrrigated();
			updateYield();
		}
	}
}


int CvPlot::getFeatureVariety() const {
	FAssert((getFeatureType() == NO_FEATURE) || (m_iFeatureVariety < GC.getFeatureInfo(getFeatureType()).getArtInfo()->getNumVarieties()));
	FAssert(m_iFeatureVariety >= 0);
	return m_iFeatureVariety;
}


int CvPlot::getOwnershipDuration() const {
	return m_iOwnershipDuration;
}


bool CvPlot::isOwnershipScore() const {
	return (getOwnershipDuration() >= GC.getDefineINT("OWNERSHIP_SCORE_DURATION_THRESHOLD"));
}


void CvPlot::setOwnershipDuration(int iNewValue) {
	if (getOwnershipDuration() != iNewValue) {
		bool bOldOwnershipScore = isOwnershipScore();

		m_iOwnershipDuration = iNewValue;
		FAssert(getOwnershipDuration() >= 0);

		if (bOldOwnershipScore != isOwnershipScore()) {
			if (isOwned()) {
				if (!isWater()) {
					GET_PLAYER(getOwnerINLINE()).changeTotalLandScored((isOwnershipScore()) ? 1 : -1);
				}
			}
		}
	}
}


void CvPlot::changeOwnershipDuration(int iChange) {
	setOwnershipDuration(getOwnershipDuration() + iChange);
}


int CvPlot::getImprovementDuration() const {
	return m_iImprovementDuration;
}


void CvPlot::setImprovementDuration(int iNewValue) {
	m_iImprovementDuration = iNewValue;
	FAssert(getImprovementDuration() >= 0);
}


void CvPlot::changeImprovementDuration(int iChange) {
	setImprovementDuration(getImprovementDuration() + iChange);
}


int CvPlot::getUpgradeProgress() const {
	return m_iUpgradeProgress;
}


int CvPlot::getUpgradeTimeLeft(ImprovementTypes eImprovement, PlayerTypes ePlayer) const {
	int iUpgradeLeft = (GC.getGameINLINE().getImprovementUpgradeTime(eImprovement) - ((getImprovementType() == eImprovement) ? getUpgradeProgress() : 0));
	if (ePlayer == NO_PLAYER) {
		return iUpgradeLeft;
	}

	int iUpgradeRate = GET_PLAYER(ePlayer).getImprovementUpgradeRate();
	if (iUpgradeRate == 0) {
		return iUpgradeLeft;
	}

	int iTurnsLeft = (iUpgradeLeft / iUpgradeRate);
	if ((iTurnsLeft * iUpgradeRate) < iUpgradeLeft) {
		iTurnsLeft++;
	}

	return std::max(1, iTurnsLeft);
}


void CvPlot::setUpgradeProgress(int iNewValue) {
	m_iUpgradeProgress = iNewValue;
	FAssert(getUpgradeProgress() >= 0);
}


void CvPlot::changeUpgradeProgress(int iChange) {
	setUpgradeProgress(getUpgradeProgress() + iChange);
}


int CvPlot::getForceUnownedTimer() const {
	return m_iForceUnownedTimer;
}


bool CvPlot::isForceUnowned() const {
	return (getForceUnownedTimer() > 0);
}


void CvPlot::setForceUnownedTimer(int iNewValue) {
	m_iForceUnownedTimer = iNewValue;
	FAssert(getForceUnownedTimer() >= 0);
}


void CvPlot::changeForceUnownedTimer(int iChange) {
	setForceUnownedTimer(getForceUnownedTimer() + iChange);
}


int CvPlot::getCityRadiusCount() const {
	return m_iCityRadiusCount;
}


bool CvPlot::isCityRadius() const {
	return (getCityRadiusCount() > 0);
}


void CvPlot::changeCityRadiusCount(int iChange) {
	m_iCityRadiusCount = (m_iCityRadiusCount + iChange);
	FAssert(getCityRadiusCount() >= 0);
}


bool CvPlot::isStartingPlot() const {
	return m_bStartingPlot;
}


void CvPlot::setStartingPlot(bool bNewValue) {
	m_bStartingPlot = bNewValue;
}


bool CvPlot::isNOfRiver() const {
	return m_bNOfRiver;
}


void CvPlot::setNOfRiver(bool bNewValue, CardinalDirectionTypes eRiverDir) {
	if ((isNOfRiver() != bNewValue) || (eRiverDir != m_eRiverWEDirection)) {
		if (isNOfRiver() != bNewValue) {
			updatePlotGroupBonus(false);
			m_bNOfRiver = bNewValue;
			updatePlotGroupBonus(true);

			updateRiverCrossing();
			updateYield();

			for (int iI = 0; iI < NUM_DIRECTION_TYPES; ++iI) {
				CvPlot* pAdjacentPlot = plotDirection(getX_INLINE(), getY_INLINE(), ((DirectionTypes)iI));

				if (pAdjacentPlot != NULL) {
					pAdjacentPlot->updateRiverCrossing();
					pAdjacentPlot->updateYield();
				}
			}

			if (area() != NULL) {
				area()->changeNumRiverEdges((isNOfRiver()) ? 1 : -1);
			}
		}

		FAssertMsg(eRiverDir == CARDINALDIRECTION_WEST || eRiverDir == CARDINALDIRECTION_EAST || eRiverDir == NO_CARDINALDIRECTION, "invalid parameter");
		m_eRiverWEDirection = eRiverDir;

		updateRiverSymbol(true, true);
	}
}


bool CvPlot::isWOfRiver() const {
	return m_bWOfRiver;
}


void CvPlot::setWOfRiver(bool bNewValue, CardinalDirectionTypes eRiverDir) {
	if ((isWOfRiver() != bNewValue) || (eRiverDir != m_eRiverNSDirection)) {
		if (isWOfRiver() != bNewValue) {
			updatePlotGroupBonus(false);
			m_bWOfRiver = bNewValue;
			updatePlotGroupBonus(true);

			updateRiverCrossing();
			updateYield();

			for (int iI = 0; iI < NUM_DIRECTION_TYPES; ++iI) {
				CvPlot* pAdjacentPlot = plotDirection(getX_INLINE(), getY_INLINE(), ((DirectionTypes)iI));

				if (pAdjacentPlot != NULL) {
					pAdjacentPlot->updateRiverCrossing();
					pAdjacentPlot->updateYield();
				}
			}

			if (area()) {
				area()->changeNumRiverEdges((isWOfRiver()) ? 1 : -1);
			}
		}

		FAssertMsg(eRiverDir == CARDINALDIRECTION_NORTH || eRiverDir == CARDINALDIRECTION_SOUTH || eRiverDir == NO_CARDINALDIRECTION, "invalid parameter");
		m_eRiverNSDirection = eRiverDir;

		updateRiverSymbol(true, true);
	}
}


CardinalDirectionTypes CvPlot::getRiverNSDirection() const {
	return (CardinalDirectionTypes)m_eRiverNSDirection;
}


CardinalDirectionTypes CvPlot::getRiverWEDirection() const {
	return (CardinalDirectionTypes)m_eRiverWEDirection;
}


// This function finds an *inland* corner of this plot at which to place a river.
// It then returns the plot with that corner at its SE.

CvPlot* CvPlot::getInlandCorner() const {
	CvPlot* pRiverPlot = NULL; // will be a plot through whose SE corner we want the river to run
	int aiShuffle[4];

	shuffleArray(aiShuffle, 4, GC.getGameINLINE().getMapRand());

	for (int iI = 0; iI < 4; ++iI) {
		switch (aiShuffle[iI]) {
		case 0:
			pRiverPlot = GC.getMapINLINE().plotSorenINLINE(getX_INLINE(), getY_INLINE()); break;
		case 1:
			pRiverPlot = plotDirection(getX_INLINE(), getY_INLINE(), DIRECTION_NORTH); break;
		case 2:
			pRiverPlot = plotDirection(getX_INLINE(), getY_INLINE(), DIRECTION_NORTHWEST); break;
		case 3:
			pRiverPlot = plotDirection(getX_INLINE(), getY_INLINE(), DIRECTION_WEST); break;
		}
		if (pRiverPlot != NULL && !pRiverPlot->hasCoastAtSECorner()) {
			break;
		} else {
			pRiverPlot = NULL;
		}
	}

	return pRiverPlot;
}


bool CvPlot::hasCoastAtSECorner() const {
	if (isWater()) {
		return true;
	}

	CvPlot* pAdjacentPlot = plotDirection(getX_INLINE(), getY_INLINE(), DIRECTION_EAST);
	if (pAdjacentPlot != NULL && pAdjacentPlot->isWater()) {
		return true;
	}

	pAdjacentPlot = plotDirection(getX_INLINE(), getY_INLINE(), DIRECTION_SOUTHEAST);
	if (pAdjacentPlot != NULL && pAdjacentPlot->isWater()) {
		return true;
	}

	pAdjacentPlot = plotDirection(getX_INLINE(), getY_INLINE(), DIRECTION_SOUTH);
	if (pAdjacentPlot != NULL && pAdjacentPlot->isWater()) {
		return true;
	}

	return false;
}


bool CvPlot::isIrrigated() const {
	return m_bIrrigated;
}


void CvPlot::setIrrigated(bool bNewValue) {
	if (isIrrigated() != bNewValue) {
		m_bIrrigated = bNewValue;

		for (int iDX = -1; iDX <= 1; iDX++) {
			for (int iDY = -1; iDY <= 1; iDY++) {
				CvPlot* pLoopPlot = plotXY(getX_INLINE(), getY_INLINE(), iDX, iDY);

				if (pLoopPlot != NULL) {
					pLoopPlot->updateYield();
					pLoopPlot->setLayoutDirty(true);
				}
			}
		}
	}
}


void CvPlot::updateIrrigated() {
	PROFILE("CvPlot::updateIrrigated()");

	if (area() == NULL) {
		return;
	}

	if (!(GC.getGameINLINE().isFinalInitialized())) {
		return;
	}

	FAStar* pIrrigatedFinder = gDLL->getFAStarIFace()->create();

	if (isIrrigated()) {
		if (!isPotentialIrrigation()) {
			setIrrigated(false);

			for (int iI = 0; iI < NUM_DIRECTION_TYPES; ++iI) {
				CvPlot* pLoopPlot = plotDirection(getX_INLINE(), getY_INLINE(), ((DirectionTypes)iI));

				if (pLoopPlot != NULL) {
					bool bFoundFreshWater = false;
					gDLL->getFAStarIFace()->Initialize(pIrrigatedFinder, GC.getMapINLINE().getGridWidthINLINE(), GC.getMapINLINE().getGridHeightINLINE(), GC.getMapINLINE().isWrapXINLINE(), GC.getMapINLINE().isWrapYINLINE(), NULL, NULL, NULL, potentialIrrigation, NULL, checkFreshWater, &bFoundFreshWater);
					gDLL->getFAStarIFace()->GeneratePath(pIrrigatedFinder, pLoopPlot->getX_INLINE(), pLoopPlot->getY_INLINE(), -1, -1);

					if (!bFoundFreshWater) {
						bool bIrrigated = false;
						gDLL->getFAStarIFace()->Initialize(pIrrigatedFinder, GC.getMapINLINE().getGridWidthINLINE(), GC.getMapINLINE().getGridHeightINLINE(), GC.getMapINLINE().isWrapXINLINE(), GC.getMapINLINE().isWrapYINLINE(), NULL, NULL, NULL, potentialIrrigation, NULL, changeIrrigated, &bIrrigated);
						gDLL->getFAStarIFace()->GeneratePath(pIrrigatedFinder, pLoopPlot->getX_INLINE(), pLoopPlot->getY_INLINE(), -1, -1);
					}
				}
			}
		}
	} else {
		if (isPotentialIrrigation() && isIrrigationAvailable(true)) {
			bool bIrrigated = true;
			gDLL->getFAStarIFace()->Initialize(pIrrigatedFinder, GC.getMapINLINE().getGridWidthINLINE(), GC.getMapINLINE().getGridHeightINLINE(), GC.getMapINLINE().isWrapXINLINE(), GC.getMapINLINE().isWrapYINLINE(), NULL, NULL, NULL, potentialIrrigation, NULL, changeIrrigated, &bIrrigated);
			gDLL->getFAStarIFace()->GeneratePath(pIrrigatedFinder, getX_INLINE(), getY_INLINE(), -1, -1);
		}
	}

	gDLL->getFAStarIFace()->destroy(pIrrigatedFinder);
}


bool CvPlot::isPotentialCityWork() const {
	return m_bPotentialCityWork;
}


bool CvPlot::isPotentialCityWorkForArea(CvArea* pArea) const {
	PROFILE_FUNC();

	for (int iI = 0; iI < NUM_CITY_PLOTS; ++iI) {
		CvPlot* pLoopPlot = plotCity(getX_INLINE(), getY_INLINE(), iI);

		if (pLoopPlot != NULL) {
			if (!(pLoopPlot->isWater()) || GC.getDefineINT("WATER_POTENTIAL_CITY_WORK_FOR_AREA")) {
				if (pLoopPlot->area() == pArea) {
					return true;
				}
			}
		}
	}

	return false;
}


void CvPlot::updatePotentialCityWork() {
	PROFILE_FUNC();

	bool bValid = false;

	for (int iI = 0; iI < NUM_CITY_PLOTS; ++iI) {
		CvPlot* pLoopPlot = plotCity(getX_INLINE(), getY_INLINE(), iI);

		if (pLoopPlot != NULL) {
			if (!(pLoopPlot->isWater())) {
				bValid = true;
				break;
			}
		}
	}

	if (isPotentialCityWork() != bValid) {
		m_bPotentialCityWork = bValid;

		updateYield();
	}
}


bool CvPlot::isShowCitySymbols() const {
	return m_bShowCitySymbols;
}


void CvPlot::updateShowCitySymbols() {
	bool bNewShowCitySymbols = false;

	for (int iI = 0; iI < NUM_CITY_PLOTS; ++iI) {
		CvPlot* pLoopPlot = plotCity(getX_INLINE(), getY_INLINE(), iI);

		if (pLoopPlot != NULL) {
			CvCity* pLoopCity = pLoopPlot->getPlotCity();

			if (pLoopCity != NULL) {
				if (pLoopCity->isCitySelected() && gDLL->getInterfaceIFace()->isCityScreenUp()) {
					if (pLoopCity->canWork(this)) {
						bNewShowCitySymbols = true;
						break;
					}
				}
			}
		}
	}

	if (isShowCitySymbols() != bNewShowCitySymbols) {
		m_bShowCitySymbols = bNewShowCitySymbols;

		updateSymbolDisplay();
		updateSymbolVisibility();
	}
}


bool CvPlot::isFlagDirty() const {
	return m_bFlagDirty;
}


void CvPlot::setFlagDirty(bool bNewValue) {
	m_bFlagDirty = bNewValue;
}


PlayerTypes CvPlot::getOwner() const {
	return getOwnerINLINE();
}


void CvPlot::setOwner(PlayerTypes eNewValue, bool bCheckUnits, bool bUpdatePlotGroup) {
	PROFILE_FUNC();

	if (getOwnerINLINE() != eNewValue) {
		GC.getGameINLINE().addReplayMessage(REPLAY_MESSAGE_PLOT_OWNER_CHANGE, eNewValue, (char*)NULL, getX_INLINE(), getY_INLINE());

		CvCity* pOldCity = getPlotCity();

		if (pOldCity != NULL) {
			CvWString szBuffer = gDLL->getText("TXT_KEY_MISC_CITY_REVOLTED_JOINED", pOldCity->getNameKey(), GET_PLAYER(eNewValue).getCivilizationDescriptionKey());
			gDLL->getInterfaceIFace()->addHumanMessage(getOwnerINLINE(), false, GC.getEVENT_MESSAGE_TIME(), szBuffer, "AS2D_CULTUREFLIP", MESSAGE_TYPE_MAJOR_EVENT, ARTFILEMGR.getInterfaceArtInfo("WORLDBUILDER_CITY_EDIT")->getPath(), (ColorTypes)GC.getInfoTypeForString("COLOR_RED"), getX_INLINE(), getY_INLINE(), true, true);
			gDLL->getInterfaceIFace()->addHumanMessage(eNewValue, false, GC.getEVENT_MESSAGE_TIME(), szBuffer, "AS2D_CULTUREFLIP", MESSAGE_TYPE_MAJOR_EVENT, ARTFILEMGR.getInterfaceArtInfo("WORLDBUILDER_CITY_EDIT")->getPath(), (ColorTypes)GC.getInfoTypeForString("COLOR_GREEN"), getX_INLINE(), getY_INLINE(), true, true);

			szBuffer = gDLL->getText("TXT_KEY_MISC_CITY_REVOLTS_JOINS", pOldCity->getNameKey(), GET_PLAYER(eNewValue).getCivilizationDescriptionKey());
			GC.getGameINLINE().addReplayMessage(REPLAY_MESSAGE_MAJOR_EVENT, getOwnerINLINE(), szBuffer, getX_INLINE(), getY_INLINE(), (ColorTypes)GC.getInfoTypeForString("COLOR_ALT_HIGHLIGHT_TEXT"));

			FAssertMsg(pOldCity->getOwnerINLINE() != eNewValue, "pOldCity->getOwnerINLINE() is not expected to be equal with eNewValue");
			GET_PLAYER(eNewValue).acquireCity(pOldCity, false, false, bUpdatePlotGroup); // will delete the pointer
			pOldCity = NULL;
			CvCity* pNewCity = getPlotCity();
			FAssertMsg(pNewCity != NULL, "NewCity is not assigned a valid value");

			if (pNewCity != NULL) {
				CLinkList<IDInfo> oldUnits;

				CLLNode<IDInfo>* pUnitNode = headUnitNode();

				while (pUnitNode != NULL) {
					oldUnits.insertAtEnd(pUnitNode->m_data);
					pUnitNode = nextUnitNode(pUnitNode);
				}

				pUnitNode = oldUnits.head();

				while (pUnitNode != NULL) {
					CvUnit* pLoopUnit = ::getUnit(pUnitNode->m_data);
					pUnitNode = oldUnits.next(pUnitNode);

					if (pLoopUnit) {
						if (pLoopUnit->isEnemy(GET_PLAYER(eNewValue).getTeam(), this)) {
							FAssert(pLoopUnit->getTeam() != GET_PLAYER(eNewValue).getTeam());
							pLoopUnit->kill(false, eNewValue);
						}
					}
				}

				UnitTypes eBestUnit = pNewCity->AI_bestUnitAI(UNITAI_CITY_DEFENSE);

				if (eBestUnit == NO_UNIT) {
					eBestUnit = pNewCity->AI_bestUnitAI(UNITAI_ATTACK);
				}

				if (eBestUnit != NO_UNIT) {
					int iFreeUnits = (GC.getDefineINT("BASE_REVOLT_FREE_UNITS") + ((pNewCity->getHighestPopulation() * GC.getDefineINT("REVOLT_FREE_UNITS_PERCENT")) / 100));

					for (int iI = 0; iI < iFreeUnits; ++iI) {
						GET_PLAYER(eNewValue).initUnit(eBestUnit, getX_INLINE(), getY_INLINE(), UNITAI_CITY_DEFENSE);
					}
				}
			}
		} else {
			setOwnershipDuration(0);

			if (isOwned()) {
				changeAdjacentSight(getTeam(), GC.getDefineINT("PLOT_VISIBILITY_RANGE"), false, NULL, bUpdatePlotGroup);

				if (area()) {
					area()->changeNumOwnedTiles(-1);
				}
				GC.getMapINLINE().changeOwnedPlots(-1);

				if (!isWater()) {
					GET_PLAYER(getOwnerINLINE()).changeTotalLand(-1);
					GET_TEAM(getTeam()).changeTotalLand(-1);

					if (isOwnershipScore()) {
						GET_PLAYER(getOwnerINLINE()).changeTotalLandScored(-1);
					}
				}

				if (getImprovementType() != NO_IMPROVEMENT) {
					GET_PLAYER(getOwnerINLINE()).changeImprovementCount(getImprovementType(), -1);
				}

				updatePlotGroupBonus(false);
			}

			CLLNode<IDInfo>* pUnitNode = headUnitNode();

			while (pUnitNode != NULL) {
				CvUnit* pLoopUnit = ::getUnit(pUnitNode->m_data);
				pUnitNode = nextUnitNode(pUnitNode);

				if (pLoopUnit->getTeam() != getTeam() && (getTeam() == NO_TEAM || !GET_TEAM(getTeam()).isVassal(pLoopUnit->getTeam()))) {
					GET_PLAYER(pLoopUnit->getOwnerINLINE()).changeNumOutsideUnits(-1);
				}

				if (pLoopUnit->isBlockading()) {
					pLoopUnit->setBlockading(false);
					pLoopUnit->getGroup()->clearMissionQueue();
					pLoopUnit->getGroup()->setActivityType(ACTIVITY_AWAKE);
				}
			}

			m_eOwner = eNewValue;

			setWorkingCityOverride(NULL);
			updateWorkingCity();

			if (isOwned()) {
				changeAdjacentSight(getTeam(), GC.getDefineINT("PLOT_VISIBILITY_RANGE"), true, NULL, bUpdatePlotGroup);

				if (area()) {
					area()->changeNumOwnedTiles(1);
				}
				GC.getMapINLINE().changeOwnedPlots(1);

				if (!isWater()) {
					GET_PLAYER(getOwnerINLINE()).changeTotalLand(1);
					GET_TEAM(getTeam()).changeTotalLand(1);

					if (isOwnershipScore()) {
						GET_PLAYER(getOwnerINLINE()).changeTotalLandScored(1);
					}
				}

				if (getImprovementType() != NO_IMPROVEMENT) {
					GET_PLAYER(getOwnerINLINE()).changeImprovementCount(getImprovementType(), 1);
				}

				updatePlotGroupBonus(true);
			}

			pUnitNode = headUnitNode();

			while (pUnitNode != NULL) {
				CvUnit* pLoopUnit = ::getUnit(pUnitNode->m_data);
				pUnitNode = nextUnitNode(pUnitNode);

				if (pLoopUnit->getTeam() != getTeam() && (getTeam() == NO_TEAM || !GET_TEAM(getTeam()).isVassal(pLoopUnit->getTeam()))) {
					GET_PLAYER(pLoopUnit->getOwnerINLINE()).changeNumOutsideUnits(1);
				}
			}

			for (int iI = 0; iI < MAX_TEAMS; ++iI) {
				if (GET_TEAM((TeamTypes)iI).isAlive()) {
					updateRevealedOwner((TeamTypes)iI);
				}
			}

			updateIrrigated();
			updateYield();

			if (bUpdatePlotGroup) {
				updatePlotGroup();
			}

			if (bCheckUnits) {
				verifyUnitValidPlot();
			}

			if (isOwned()) {
				if (isGoody()) {
					GET_PLAYER(getOwnerINLINE()).doGoody(this, NULL);
				}

				for (int iI = 0; iI < MAX_CIV_TEAMS; ++iI) {
					if (GET_TEAM((TeamTypes)iI).isAlive()) {
						if (isVisible((TeamTypes)iI, false)) {
							GET_TEAM((TeamTypes)iI).meet(getTeam(), true);
						}
					}
				}
			}

			if (GC.getGameINLINE().isDebugMode()) {
				updateMinimapColor();

				gDLL->getInterfaceIFace()->setDirty(GlobeLayer_DIRTY_BIT, true);

				gDLL->getEngineIFace()->SetDirty(CultureBorders_DIRTY_BIT, true);
			}
		}

		invalidateBorderDangerCache(); // K-Mod. (based on BBAI)

		updateSymbols();
	}
}


PlotTypes CvPlot::getPlotType() const {
	return (PlotTypes)m_ePlotType;
}


bool CvPlot::isWater() const {
	return (getPlotType() == PLOT_OCEAN);
}


bool CvPlot::isFlatlands() const {
	return (getPlotType() == PLOT_LAND);
}


bool CvPlot::isHills() const {
	return (getPlotType() == PLOT_HILLS);
}


bool CvPlot::isPeak() const {
	return (getPlotType() == PLOT_PEAK);
}


void CvPlot::setPlotType(PlotTypes eNewValue, bool bRecalculate, bool bRebuildGraphics) {
	if (getPlotType() != eNewValue) {
		if ((getPlotType() == PLOT_OCEAN) || (eNewValue == PLOT_OCEAN)) {
			erase();
		}

		bool bWasWater = isWater();

		updateSeeFromSight(false, true);

		m_ePlotType = eNewValue;

		updateYield();
		updatePlotGroup();

		updateSeeFromSight(true, true);

		if ((getTerrainType() == NO_TERRAIN) || (GC.getTerrainInfo(getTerrainType()).isWater() != isWater())) {
			if (isWater()) {
				if (isAdjacentToLand()) {
					setTerrainType(((TerrainTypes)(GC.getDefineINT("SHALLOW_WATER_TERRAIN"))), bRecalculate, bRebuildGraphics);
				} else {
					setTerrainType(((TerrainTypes)(GC.getDefineINT("DEEP_WATER_TERRAIN"))), bRecalculate, bRebuildGraphics);
				}
			} else {
				setTerrainType(((TerrainTypes)(GC.getDefineINT("LAND_TERRAIN"))), bRecalculate, bRebuildGraphics);
			}
		}

		GC.getMapINLINE().resetPathDistance();

		if (bWasWater != isWater()) {
			if (bRecalculate) {
				for (int iI = 0; iI < NUM_DIRECTION_TYPES; ++iI) {
					CvPlot* pLoopPlot = plotDirection(getX_INLINE(), getY_INLINE(), ((DirectionTypes)iI));

					if (pLoopPlot != NULL) {
						if (pLoopPlot->isWater()) {
							if (pLoopPlot->isAdjacentToLand()) {
								pLoopPlot->setTerrainType(((TerrainTypes)(GC.getDefineINT("SHALLOW_WATER_TERRAIN"))), bRecalculate, bRebuildGraphics);
							} else {
								pLoopPlot->setTerrainType(((TerrainTypes)(GC.getDefineINT("DEEP_WATER_TERRAIN"))), bRecalculate, bRebuildGraphics);
							}
						}
					}
				}
			}

			for (int iI = 0; iI < NUM_DIRECTION_TYPES; ++iI) {
				CvPlot* pLoopPlot = plotDirection(getX_INLINE(), getY_INLINE(), ((DirectionTypes)iI));

				if (pLoopPlot != NULL) {
					pLoopPlot->updateYield();
					pLoopPlot->updatePlotGroup();
				}
			}

			for (int iI = 0; iI < NUM_CITY_PLOTS; ++iI) {
				CvPlot* pLoopPlot = plotCity(getX_INLINE(), getY_INLINE(), iI);

				if (pLoopPlot != NULL) {
					pLoopPlot->updatePotentialCityWork();
				}
			}

			GC.getMapINLINE().changeLandPlots((isWater()) ? -1 : 1);

			if (getBonusType() != NO_BONUS) {
				GC.getMapINLINE().changeNumBonusesOnLand(getBonusType(), ((isWater()) ? -1 : 1));
			}

			if (isOwned()) {
				GET_PLAYER(getOwnerINLINE()).changeTotalLand((isWater()) ? -1 : 1);
				GET_TEAM(getTeam()).changeTotalLand((isWater()) ? -1 : 1);
			}

			if (bRecalculate) {
				CvArea* pNewArea = NULL;
				bool bRecalculateAreas = false;

				// XXX might want to change this if we allow diagonal water movement...
				if (isWater()) {
					for (int iI = 0; iI < NUM_CARDINALDIRECTION_TYPES; ++iI) {
						CvPlot* pLoopPlot = plotCardinalDirection(getX_INLINE(), getY_INLINE(), ((CardinalDirectionTypes)iI));

						if (pLoopPlot != NULL) {
							if (pLoopPlot->area()->isWater()) {
								if (pNewArea == NULL) {
									pNewArea = pLoopPlot->area();
								} else if (pNewArea != pLoopPlot->area()) {
									bRecalculateAreas = true;
									break;
								}
							}
						}
					}
				} else {
					for (int iI = 0; iI < NUM_DIRECTION_TYPES; ++iI) {
						CvPlot* pLoopPlot = plotDirection(getX_INLINE(), getY_INLINE(), ((DirectionTypes)iI));

						if (pLoopPlot != NULL) {
							if (!(pLoopPlot->area()->isWater())) {
								if (pNewArea == NULL) {
									pNewArea = pLoopPlot->area();
								} else if (pNewArea != pLoopPlot->area()) {
									bRecalculateAreas = true;
									break;
								}
							}
						}
					}
				}

				if (!bRecalculateAreas) {
					CvPlot* pLoopPlot = plotDirection(getX_INLINE(), getY_INLINE(), ((DirectionTypes)(NUM_DIRECTION_TYPES - 1)));
					CvArea* pLastArea = NULL;
					if (pLoopPlot != NULL) {
						pLastArea = pLoopPlot->area();
					} else {
						pLastArea = NULL;
					}

					int iAreaCount = 0;

					for (int iI = 0; iI < NUM_DIRECTION_TYPES; ++iI) {
						pLoopPlot = plotDirection(getX_INLINE(), getY_INLINE(), ((DirectionTypes)iI));

						CvArea* pCurrArea = NULL;
						if (pLoopPlot != NULL) {
							pCurrArea = pLoopPlot->area();
						} else {
							pCurrArea = NULL;
						}

						if (pCurrArea != pLastArea) {
							iAreaCount++;
						}

						pLastArea = pCurrArea;
					}

					if (iAreaCount > 2) {
						bRecalculateAreas = true;
					}
				}

				if (bRecalculateAreas) {
					GC.getMapINLINE().recalculateAreas();
				} else {
					setArea(FFreeList::INVALID_INDEX);

					if ((area() != NULL) && (area()->getNumTiles() == 1)) {
						GC.getMapINLINE().deleteArea(getArea());
					}

					if (pNewArea == NULL) {
						pNewArea = GC.getMapINLINE().addArea();
						pNewArea->init(pNewArea->getID(), isWater());
					}

					setArea(pNewArea->getID());
				}
			}
		}

		if (bRebuildGraphics && GC.IsGraphicsInitialized()) {
			//Update terrain graphical 
			gDLL->getEngineIFace()->RebuildPlot(getX_INLINE(), getY_INLINE(), true, true);

			updateFeatureSymbol();
			setLayoutDirty(true);
			updateRouteSymbol(false, true);
			updateRiverSymbol(false, true);
		}
	}
}


TerrainTypes CvPlot::getTerrainType() const {
	return (TerrainTypes)m_eTerrainType;
}


void CvPlot::setTerrainType(TerrainTypes eNewValue, bool bRecalculate, bool bRebuildGraphics) {
	if (getTerrainType() != eNewValue) {
		bool bUpdateSight;
		if ((getTerrainType() != NO_TERRAIN) &&
			(eNewValue != NO_TERRAIN) &&
			((GC.getTerrainInfo(getTerrainType()).getSeeFromLevel() != GC.getTerrainInfo(eNewValue).getSeeFromLevel()) ||
				(GC.getTerrainInfo(getTerrainType()).getSeeThroughLevel() != GC.getTerrainInfo(eNewValue).getSeeThroughLevel()))) {
			bUpdateSight = true;
		} else {
			bUpdateSight = false;
		}

		if (bUpdateSight) {
			updateSeeFromSight(false, true);
		}

		m_eTerrainType = eNewValue;

		updateYield();
		updatePlotGroup();

		if (bUpdateSight) {
			updateSeeFromSight(true, true);
		}

		if (bRebuildGraphics && GC.IsGraphicsInitialized()) {
			//Update terrain graphics
			gDLL->getEngineIFace()->RebuildPlot(getX_INLINE(), getY_INLINE(), false, true);
		}

		if (GC.getTerrainInfo(getTerrainType()).isWater() != isWater()) {
			setPlotType(((GC.getTerrainInfo(getTerrainType()).isWater()) ? PLOT_OCEAN : PLOT_LAND), bRecalculate, bRebuildGraphics);
		}
	}
}


FeatureTypes CvPlot::getFeatureType() const {
	return (FeatureTypes)m_eFeatureType;
}


void CvPlot::setFeatureType(FeatureTypes eNewValue, int iVariety) {
	FeatureTypes eOldFeature = getFeatureType();

	if (eNewValue != NO_FEATURE) {
		if (iVariety == -1) {
			iVariety = ((GC.getFeatureInfo(eNewValue).getArtInfo()->getNumVarieties() * ((getLatitude() * 9) / 8)) / 90);
		}

		iVariety = range(iVariety, 0, (GC.getFeatureInfo(eNewValue).getArtInfo()->getNumVarieties() - 1));
	} else {
		iVariety = 0;
	}

	if ((eOldFeature != eNewValue) || (m_iFeatureVariety != iVariety)) {
		bool bUpdateSight = false;
		if ((eOldFeature == NO_FEATURE) || (eNewValue == NO_FEATURE) || (GC.getFeatureInfo(eOldFeature).getSeeThroughChange() != GC.getFeatureInfo(eNewValue).getSeeThroughChange())) {
			bUpdateSight = true;
		} else {
			bUpdateSight = false;
		}

		if (bUpdateSight) {
			updateSeeFromSight(false, true);
		}

		// We need to check for the scenario where one feature replaces another in which case there are
		//  two potential building prereq changes, the loss of the old and the gain of the new. This can lead 
		//  to a double message if the loss of the old feature causes the building to be disabled and then
		//  the new feature enables it. Given that these events can happen mid-turn it is difficult to work
		//  around this.
		if (NO_FEATURE != eOldFeature && getWorkingCity() != NULL) getWorkingCity()->checkBuildingPlotPrereqs(this, false);
		m_eFeatureType = eNewValue;
		m_iFeatureVariety = iVariety;
		if (NO_FEATURE != eNewValue && getWorkingCity() != NULL) getWorkingCity()->checkBuildingPlotPrereqs(this, true);

		updateYield();

		if (bUpdateSight) {
			updateSeeFromSight(true, true);
		}

		updateFeatureSymbol();

		if (((eOldFeature != NO_FEATURE) && (GC.getFeatureInfo(eOldFeature).getArtInfo()->isRiverArt())) ||
			((getFeatureType() != NO_FEATURE) && (GC.getFeatureInfo(getFeatureType()).getArtInfo()->isRiverArt()))) {
			updateRiverSymbolArt(true);
		}

		for (int iI = 0; iI < NUM_CITY_PLOTS; ++iI) {
			CvPlot* pLoopPlot = plotCity(getX_INLINE(), getY_INLINE(), iI);

			if (pLoopPlot != NULL) {
				CvCity* pLoopCity = pLoopPlot->getPlotCity();

				if (pLoopCity != NULL) {
					pLoopCity->updateFeatureHealth();
					pLoopCity->updateFeatureHappiness();
				}
			}
		}

		if (getFeatureType() == NO_FEATURE) {
			if (getImprovementType() != NO_IMPROVEMENT) {
				if (GC.getImprovementInfo(getImprovementType()).isRequiresFeature()) {
					setImprovementType(NO_IMPROVEMENT);
				}
			}
		}
	}
}

void CvPlot::setFeatureDummyVisibility(const char* dummyTag, bool show) {
	FAssertMsg(m_pFeatureSymbol != NULL, "[Jason] No feature symbol.");
	if (m_pFeatureSymbol != NULL) {
		gDLL->getFeatureIFace()->setDummyVisibility(m_pFeatureSymbol, dummyTag, show);
	}
}

void CvPlot::addFeatureDummyModel(const char* dummyTag, const char* modelTag) {
	FAssertMsg(m_pFeatureSymbol != NULL, "[Jason] No feature symbol.");
	if (m_pFeatureSymbol != NULL) {
		gDLL->getFeatureIFace()->addDummyModel(m_pFeatureSymbol, dummyTag, modelTag);
	}
}

void CvPlot::setFeatureDummyTexture(const char* dummyTag, const char* textureTag) {
	FAssertMsg(m_pFeatureSymbol != NULL, "[Jason] No feature symbol.");
	if (m_pFeatureSymbol != NULL) {
		gDLL->getFeatureIFace()->setDummyTexture(m_pFeatureSymbol, dummyTag, textureTag);
	}
}

CvString CvPlot::pickFeatureDummyTag(int mouseX, int mouseY) {
	FAssertMsg(m_pFeatureSymbol != NULL, "[Jason] No feature symbol.");
	if (m_pFeatureSymbol != NULL) {
		return gDLL->getFeatureIFace()->pickDummyTag(m_pFeatureSymbol, mouseX, mouseY);
	}

	return NULL;
}

void CvPlot::resetFeatureModel() {
	FAssertMsg(m_pFeatureSymbol != NULL, "[Jason] No feature symbol.");
	if (m_pFeatureSymbol != NULL) {
		gDLL->getFeatureIFace()->resetModel(m_pFeatureSymbol);
	}
}

BonusTypes CvPlot::getBonusType(TeamTypes eTeam) const {
	if (eTeam != NO_TEAM) {
		if (m_eBonusType != NO_BONUS) {
			//if (!GET_TEAM(eTeam).isHasTech((TechTypes)(GC.getBonusInfo((BonusTypes)m_eBonusType).getTechReveal())) && !GET_TEAM(eTeam).isForceRevealedBonus((BonusTypes)m_eBonusType))
			if (!GET_TEAM(eTeam).isBonusRevealed((BonusTypes)m_eBonusType)) // K-Mod
			{
				return NO_BONUS;
			}
		}
	}

	return (BonusTypes)m_eBonusType;
}


BonusTypes CvPlot::getNonObsoleteBonusType(TeamTypes eTeam, bool bCheckConnected) const // K-Mod added bCheckConnected
{
	FAssert(eTeam != NO_TEAM);
	FAssert(GET_TEAM(eTeam).isAlive()); // K-Mod

	BonusTypes eBonus = getBonusType(eTeam);
	if (eBonus != NO_BONUS) {
		if (GET_TEAM(eTeam).isBonusObsolete(eBonus)) {
			return NO_BONUS;
		}
		if (bCheckConnected) {
			// note: this checks whether the bonus is connected for the owner of the plot, from the point of view of eTeam.
			TeamTypes ePlotTeam = getTeam();
			if (ePlotTeam == NO_TEAM || !GET_TEAM(ePlotTeam).isHasTech((TechTypes)GC.getBonusInfo(eBonus).getTechCityTrade()))
				return NO_BONUS;

			// note: this function is used inside CvPlot::updatePlotGroupBonuses, which is called during CvPlot::setImprovementType
			// between when the improvement is changed and the revealed improvement type is updated...
			// therefore when eTeam == ePlotTeam, we use the real improvement, not the revealed one.
			ImprovementTypes eImprovement = eTeam == NO_TEAM || eTeam == ePlotTeam ? getImprovementType() : getRevealedImprovementType(eTeam, false);

			FAssert(ePlotTeam != eTeam || eImprovement == getImprovementType());

			if (!isCity() && !GET_TEAM(ePlotTeam).doesImprovementConnectBonus(eImprovement, eBonus))
				return NO_BONUS;
		}
	}

	return eBonus;
}


void CvPlot::setBonusType(BonusTypes eNewValue) {
	BonusTypes eOldBonus = getBonusType();
	if (getBonusType() != eNewValue) {
		if (getBonusType() != NO_BONUS) {
			if (area()) {
				area()->changeNumBonuses(getBonusType(), -1);
			}
			GC.getMapINLINE().changeNumBonuses(getBonusType(), -1);

			if (!isWater()) {
				GC.getMapINLINE().changeNumBonusesOnLand(getBonusType(), -1);
			}
		}

		updatePlotGroupBonus(false);
		// We need to check for the scenario where one bonus replaces another in which case there are
		//  two potential building prereq changes, the loss of the old and the gain of the new. This can lead 
		//  to a double message if the loss of the old bonus causes the building to be disabled and then
		//  the new feature enables it. Given that these events can happen mid-turn it is difficult to work
		//  around this.
		if (NO_BONUS != eOldBonus && getWorkingCity() != NULL) getWorkingCity()->checkBuildingPlotPrereqs(this, false);
		m_eBonusType = eNewValue;
		updatePlotGroupBonus(true);
		if (NO_BONUS != eNewValue && getWorkingCity() != NULL) getWorkingCity()->checkBuildingPlotPrereqs(this, true);

		if (getBonusType() != NO_BONUS) {
			if (area()) {
				area()->changeNumBonuses(getBonusType(), 1);
			}
			GC.getMapINLINE().changeNumBonuses(getBonusType(), 1);

			if (!isWater()) {
				GC.getMapINLINE().changeNumBonusesOnLand(getBonusType(), 1);
			}
		}

		updateYield();

		setLayoutDirty(true);

		gDLL->getInterfaceIFace()->setDirty(GlobeLayer_DIRTY_BIT, true);
	}
}


ImprovementTypes CvPlot::getImprovementType() const {
	return (ImprovementTypes)m_eImprovementType;
}


void CvPlot::setImprovementType(ImprovementTypes eNewValue) {
	ImprovementTypes eOldImprovement = getImprovementType();

	if (getImprovementType() != eNewValue) {
		if (getImprovementType() != NO_IMPROVEMENT) {
			if (area()) {
				area()->changeNumImprovements(getImprovementType(), -1);
			}
			if (isOwned()) {
				GET_PLAYER(getOwnerINLINE()).changeImprovementCount(getImprovementType(), -1);
			}
		}

		updatePlotGroupBonus(false);
		// We need to check for the scenario where one improvement replaces another in which case there are
		//  two potential building prereq changes, the loss of the old and the gain of the new. This can lead 
		//  to a double message if the loss of the old improvement causes the building to be disabled and then
		//  the new improvement enables it. Given that these events can happen mid-turn it is difficult to work
		//  around this.
		if (NO_IMPROVEMENT != eOldImprovement && getWorkingCity() != NULL) getWorkingCity()->checkBuildingPlotPrereqs(this, false);
		m_eImprovementType = eNewValue;
		updatePlotGroupBonus(true);
		if (NO_IMPROVEMENT != getImprovementType() && getWorkingCity() != NULL) getWorkingCity()->checkBuildingPlotPrereqs(this, true);

		if (getImprovementType() == NO_IMPROVEMENT) {
			setImprovementDuration(0);
		}

		setUpgradeProgress(0);

		for (TeamTypes eTeam = (TeamTypes)0; eTeam < MAX_TEAMS; eTeam = (TeamTypes)(eTeam + 1)) {
			if (GET_TEAM(eTeam).isAlive()) {
				if (isVisible(eTeam, false)) {
					setRevealedImprovementType(eTeam, getImprovementType());
				}
			}
		}

		if (getImprovementType() != NO_IMPROVEMENT) {
			if (area()) {
				area()->changeNumImprovements(getImprovementType(), 1);
			}
			if (isOwned()) {
				GET_PLAYER(getOwnerINLINE()).changeImprovementCount(getImprovementType(), 1);
			}
		}

		updateIrrigated();
		updateYield();

		for (int iI = 0; iI < NUM_CITY_PLOTS; ++iI) {
			CvPlot* pLoopPlot = plotCity(getX_INLINE(), getY_INLINE(), iI);

			if (pLoopPlot != NULL) {
				CvCity* pLoopCity = pLoopPlot->getPlotCity();

				if (pLoopCity != NULL) {
					pLoopCity->updateFeatureHappiness();
					pLoopCity->updateImprovementHealth();
				}
			}
		}

		// Building or removing a fort will now force a plotgroup update to verify resource connections.
		if ((NO_IMPROVEMENT != getImprovementType() && GC.getImprovementInfo(getImprovementType()).isActsAsCity()) !=
			(NO_IMPROVEMENT != eOldImprovement && GC.getImprovementInfo(eOldImprovement).isActsAsCity())) {
			updatePlotGroup();
		}

		if (NO_IMPROVEMENT != eOldImprovement && GC.getImprovementInfo(eOldImprovement).isActsAsCity()) {
			verifyUnitValidPlot();
		}

		if (GC.getGameINLINE().isDebugMode()) {
			setLayoutDirty(true);
		}

		if (getImprovementType() != NO_IMPROVEMENT) {
			CvEventReporter::getInstance().improvementBuilt(getImprovementType(), getX_INLINE(), getY_INLINE());
		}

		if (getImprovementType() == NO_IMPROVEMENT) {
			CvEventReporter::getInstance().improvementDestroyed(eOldImprovement, getOwnerINLINE(), getX_INLINE(), getY_INLINE());
		}

		CvCity* pWorkingCity = getWorkingCity();
		if (NULL != pWorkingCity) {
			if ((NO_IMPROVEMENT != eNewValue && pWorkingCity->getImprovementFreeSpecialists(eNewValue) > 0) ||
				(NO_IMPROVEMENT != eOldImprovement && pWorkingCity->getImprovementFreeSpecialists(eOldImprovement) > 0)) {

				pWorkingCity->AI_setAssignWorkDirty(true);

			}
		}

		gDLL->getInterfaceIFace()->setDirty(CitizenButtons_DIRTY_BIT, true);
	}
}


RouteTypes CvPlot::getRouteType() const {
	return (RouteTypes)m_eRouteType;
}


void CvPlot::setRouteType(RouteTypes eNewValue, bool bUpdatePlotGroups) {
	if (getRouteType() != eNewValue) {
		bool bOldRoute = isRoute(); // XXX is this right???

		updatePlotGroupBonus(false);
		// We need to check for the scenario where one route replaces another in which case there are
		//  two potential building prereq changes, the loss of the old and the gain of the new. This can lead 
		//  to a double message if the loss of the old route causes the building to be disabled and then
		//  the new route enables it. Given that these events can happen mid-turn it is difficult to work
		//  around this.
		if (NO_ROUTE != getRouteType() && getWorkingCity() != NULL) getWorkingCity()->checkBuildingPlotPrereqs(this, false);
		m_eRouteType = eNewValue;
		updatePlotGroupBonus(true);
		if (NO_ROUTE != getImprovementType() && getWorkingCity() != NULL) getWorkingCity()->checkBuildingPlotPrereqs(this, true);

		for (TeamTypes eTeam = (TeamTypes)0; eTeam < MAX_TEAMS; eTeam = (TeamTypes)(eTeam + 1)) {
			if (GET_TEAM(eTeam).isAlive()) {
				if (isVisible(eTeam, false)) {
					setRevealedRouteType(eTeam, getRouteType());
				}
			}
		}

		updateYield();

		if (bUpdatePlotGroups) {
			if (bOldRoute != isRoute()) {
				updatePlotGroup();
			}
		}

		if (GC.getGameINLINE().isDebugMode()) {
			updateRouteSymbol(true, true);
		}

		if (getRouteType() != NO_ROUTE) {
			CvEventReporter::getInstance().routeBuilt(getRouteType(), getX_INLINE(), getY_INLINE());
		}

		// If we are removing a route that allows land units to cross the water then drown any that are on the plot
		if (eNewValue == NO_ROUTE && isLandUnitWaterSafe()) {
			CLLNode<IDInfo>* pUnitNode = headUnitNode();

			while (pUnitNode != NULL) {
				CvUnit* pLoopUnit = ::getUnit(pUnitNode->m_data);
				pUnitNode = nextUnitNode(pUnitNode);

				if (pLoopUnit->getDomainType() == DOMAIN_LAND && !pLoopUnit->canMoveAllTerrain()) {
					pLoopUnit->kill(true);
				}
			}
		}

		// K-Mod. Fixing a bug in the border danger cache from BBAI.
		if (bOldRoute && !isRoute()) {
			invalidateBorderDangerCache();
		}
	}
}


void CvPlot::updateCityRoute(bool bUpdatePlotGroup) {
	if (isCity()) {
		FAssertMsg(isOwned(), "isOwned is expected to be true");

		RouteTypes eCityRoute = GET_PLAYER(getOwnerINLINE()).getBestRoute();

		if (eCityRoute == NO_ROUTE) {
			eCityRoute = ((RouteTypes)(GC.getDefineINT("INITIAL_CITY_ROUTE_TYPE")));
		}

		setRouteType(eCityRoute, bUpdatePlotGroup);
	}
}


CvCity* CvPlot::getPlotCity() const {
	return getCity(m_plotCity);
}


void CvPlot::setPlotCity(CvCity* pNewValue) {
	if (getPlotCity() != pNewValue) {
		if (isCity()) {
			for (int iI = 0; iI < NUM_CITY_PLOTS; ++iI) {
				CvPlot* pLoopPlot = plotCity(getX_INLINE(), getY_INLINE(), iI);

				if (pLoopPlot != NULL) {
					pLoopPlot->changeCityRadiusCount(-1);
					pLoopPlot->changePlayerCityRadiusCount(getPlotCity()->getOwnerINLINE(), -1);
				}
			}
		}

		updatePlotGroupBonus(false);
		if (isCity()) {
			CvPlotGroup* pPlotGroup = getPlotGroup(getOwnerINLINE());

			if (pPlotGroup != NULL) {
				FAssertMsg((0 < GC.getNumBonusInfos()), "GC.getNumBonusInfos() is not greater than zero but an array is being allocated in CvPlot::setPlotCity");
				for (int iI = 0; iI < GC.getNumBonusInfos(); ++iI) {
					getPlotCity()->changeNumBonuses(((BonusTypes)iI), -(pPlotGroup->getNumBonuses((BonusTypes)iI)));
				}
			}
		}
		if (pNewValue != NULL) {
			m_plotCity = pNewValue->getIDInfo();
		} else {
			m_plotCity.reset();
		}
		if (isCity()) {
			CvPlotGroup* pPlotGroup = getPlotGroup(getOwnerINLINE());

			if (pPlotGroup != NULL) {
				FAssertMsg((0 < GC.getNumBonusInfos()), "GC.getNumBonusInfos() is not greater than zero but an array is being allocated in CvPlot::setPlotCity");
				for (int iI = 0; iI < GC.getNumBonusInfos(); ++iI) {
					getPlotCity()->changeNumBonuses(((BonusTypes)iI), pPlotGroup->getNumBonuses((BonusTypes)iI));
				}
			}
		}
		updatePlotGroupBonus(true);

		if (isCity()) {
			for (int iI = 0; iI < NUM_CITY_PLOTS; ++iI) {
				CvPlot* pLoopPlot = plotCity(getX_INLINE(), getY_INLINE(), iI);

				if (pLoopPlot != NULL) {
					pLoopPlot->changeCityRadiusCount(1);
					pLoopPlot->changePlayerCityRadiusCount(getPlotCity()->getOwnerINLINE(), 1);
				}
			}
		}

		updateIrrigated();
		updateYield();

		updateMinimapColor();
	}
}


CvCity* CvPlot::getWorkingCity() const {
	return getCity(m_workingCity);
}


void CvPlot::updateWorkingCity() {
	CvCity* pBestCity = getPlotCity();

	if (pBestCity == NULL) {
		pBestCity = getWorkingCityOverride();
		FAssertMsg((pBestCity == NULL) || (pBestCity->getOwnerINLINE() == getOwnerINLINE()), "pBest city is expected to either be NULL or the current plot instance's");
	}

	if ((pBestCity == NULL) && isOwned()) {
		int iBestPlot = 0;

		// We loop through all the possible plots around this plot looking for a city.
		// The city we find may not actually have the current range to work this plot if its culture level is
		//  not high enough so we then need to do a reverse check to see if this plot is in the cities range
		for (int iI = 0; iI < NUM_CITY_PLOTS; ++iI) {
			CvPlot* pLoopPlot = plotCity(getX_INLINE(), getY_INLINE(), iI);

			if (pLoopPlot != NULL) {
				CvCity* pLoopCity = pLoopPlot->getPlotCity();

				if (pLoopCity != NULL) {
					if (pLoopCity->getOwnerINLINE() == getOwnerINLINE()) {
						// Do the reverse check to see if the found city has enough culture to work this tile
						bool bFound = false;
						int iNumLoopCityPlots = pLoopCity->getNumCityPlots();
						for (int iJ = 0; iJ < iNumLoopCityPlots; ++iJ) {
							CvPlot* pCheckPlot = plotCity(pLoopCity->getX_INLINE(), pLoopCity->getY_INLINE(), iJ);
							if (pCheckPlot != NULL && pCheckPlot == this) {
								bFound = true;
								break;
							}
						}

						if (bFound) {
							// XXX use getGameTurnAcquired() instead???
							if ((pBestCity == NULL) ||
								(GC.getCityPlotPriority()[iI] < GC.getCityPlotPriority()[iBestPlot]) ||
								((GC.getCityPlotPriority()[iI] == GC.getCityPlotPriority()[iBestPlot]) &&
									((pLoopCity->getGameTurnFounded() < pBestCity->getGameTurnFounded()) ||
										((pLoopCity->getGameTurnFounded() == pBestCity->getGameTurnFounded()) &&
											(pLoopCity->getID() < pBestCity->getID()))))) {
								iBestPlot = iI;
								pBestCity = pLoopCity;
							}
						}
					}
				}
			}
		}
	}

	CvCity* pOldWorkingCity = getWorkingCity();

	if (pOldWorkingCity != pBestCity) {
		if (pOldWorkingCity != NULL) {
			pOldWorkingCity->setWorkingPlot(this, false);
			pOldWorkingCity->checkBuildingPlotPrereqs(this, false);
		}

		if (pBestCity != NULL) {
			FAssertMsg(isOwned(), "isOwned is expected to be true");
			FAssertMsg(!isBeingWorked(), "isBeingWorked did not return false as expected");
			m_workingCity = pBestCity->getIDInfo();
		} else {
			m_workingCity.reset();
		}

		if (pOldWorkingCity != NULL) {
			pOldWorkingCity->AI_setAssignWorkDirty(true);
		}
		if (getWorkingCity() != NULL) {
			getWorkingCity()->AI_setAssignWorkDirty(true);
			getWorkingCity()->checkBuildingPlotPrereqs(this, true);
		}

		updateYield();

		updateFog();
		updateShowCitySymbols();

		if (getOwnerINLINE() == GC.getGameINLINE().getActivePlayer()) {
			if (gDLL->getGraphicOption(GRAPHICOPTION_CITY_RADIUS)) {
				if (gDLL->getInterfaceIFace()->canSelectionListFound()) {
					gDLL->getInterfaceIFace()->setDirty(ColoredPlots_DIRTY_BIT, true);
				}
			}
		}
	}
}


CvCity* CvPlot::getWorkingCityOverride() const {
	return getCity(m_workingCityOverride);
}


void CvPlot::setWorkingCityOverride(const CvCity* pNewValue) {
	if (getWorkingCityOverride() != pNewValue) {
		if (pNewValue != NULL) {
			FAssertMsg(pNewValue->getOwnerINLINE() == getOwnerINLINE(), "Argument city pNewValue's owner is expected to be the same as the current instance");
			m_workingCityOverride = pNewValue->getIDInfo();
		} else {
			m_workingCityOverride.reset();
		}

		updateWorkingCity();
	}
}


int CvPlot::getRiverID() const {
	return m_iRiverID;
}


void CvPlot::setRiverID(int iNewValue) {
	m_iRiverID = iNewValue;
}


int CvPlot::getMinOriginalStartDist() const {
	return m_iMinOriginalStartDist;
}


void CvPlot::setMinOriginalStartDist(int iNewValue) {
	m_iMinOriginalStartDist = iNewValue;
}


int CvPlot::getReconCount() const {
	return m_iReconCount;
}


void CvPlot::changeReconCount(int iChange) {
	m_iReconCount = (m_iReconCount + iChange);
	FAssert(getReconCount() >= 0);
}


int CvPlot::getRiverCrossingCount() const {
	return m_iRiverCrossingCount;
}


void CvPlot::changeRiverCrossingCount(int iChange) {
	m_iRiverCrossingCount = (m_iRiverCrossingCount + iChange);
	FAssert(getRiverCrossingCount() >= 0);
}


short* CvPlot::getYield() {
	return m_aiYield;
}


int CvPlot::getYield(YieldTypes eIndex) const {
	FAssertMsg(eIndex >= 0, "eIndex is expected to be non-negative (invalid Index)");
	FAssertMsg(eIndex < NUM_YIELD_TYPES, "eIndex is expected to be within maximum bounds (invalid Index)");
	return m_aiYield[eIndex];
}


int CvPlot::calculateNatureYield(YieldTypes eYield, TeamTypes eTeam, bool bIgnoreFeature) const {
	if (isImpassable(getTeam(), true)) {
		if (!isPeak()) {
			return 0;
		} else if (eTeam != NO_TEAM && !GET_TEAM(eTeam).isCanPassPeaks()) {	//	Koshling - prevent mountains being worked until workers can	move into peak tiles
			return 0;
		}
	}

	FAssertMsg(getTerrainType() != NO_TERRAIN, "TerrainType is not assigned a valid value");

	int iYield = GC.getTerrainInfo(getTerrainType()).getYield(eYield);

	const CvYieldInfo& kYield = GC.getYieldInfo(eYield);
	if (isHills()) {
		iYield += kYield.getHillsChange();
	}

	if (isPeak()) {
		iYield += kYield.getPeakChange();
	}

	if (isLake()) {
		iYield += kYield.getLakeChange();
	}

	if (eTeam != NO_TEAM) {
		BonusTypes eBonus = getBonusType(eTeam);

		if (eBonus != NO_BONUS) {
			iYield += GC.getBonusInfo(eBonus).getYieldChange(eYield);
		}
	}

	if (isRiver()) {
		iYield += ((bIgnoreFeature || (getFeatureType() == NO_FEATURE)) ? GC.getTerrainInfo(getTerrainType()).getRiverYieldChange(eYield) : GC.getFeatureInfo(getFeatureType()).getRiverYieldChange(eYield));
	}

	if (isHills()) {
		iYield += ((bIgnoreFeature || (getFeatureType() == NO_FEATURE)) ? GC.getTerrainInfo(getTerrainType()).getHillsYieldChange(eYield) : GC.getFeatureInfo(getFeatureType()).getHillsYieldChange(eYield));
	}

	if (!bIgnoreFeature) {
		if (getFeatureType() != NO_FEATURE) {
			iYield += GC.getFeatureInfo(getFeatureType()).getYieldChange(eYield);
		}
	}

	return std::max(0, iYield);
}


int CvPlot::calculateBestNatureYield(YieldTypes eIndex, TeamTypes eTeam) const {
	return std::max(calculateNatureYield(eIndex, eTeam, false), calculateNatureYield(eIndex, eTeam, true));
}


int CvPlot::calculateTotalBestNatureYield(TeamTypes eTeam) const {
	return (calculateBestNatureYield(YIELD_FOOD, eTeam) + calculateBestNatureYield(YIELD_PRODUCTION, eTeam) + calculateBestNatureYield(YIELD_COMMERCE, eTeam));
}

int CvPlot::calculateImprovementYieldChange(ImprovementTypes eImprovement, YieldTypes eYield, PlayerTypes ePlayer, bool bOptimal, bool bBestRoute) const {
	PROFILE_FUNC();

	int iYield = GC.getImprovementInfo(eImprovement).getYieldChange(eYield);

	if (isRiverSide()) {
		iYield += GC.getImprovementInfo(eImprovement).getRiverSideYieldChange(eYield);
	}

	if (isHills()) {
		iYield += GC.getImprovementInfo(eImprovement).getHillsYieldChange(eYield);
	}

	if ((bOptimal) ? true : isIrrigationAvailable()) {
		iYield += GC.getImprovementInfo(eImprovement).getIrrigatedYieldChange(eYield);
	}

	if (bOptimal) {
		int iBestYield = 0;

		for (int iI = 0; iI < GC.getNumRouteInfos(); ++iI) {
			iBestYield = std::max(iBestYield, GC.getImprovementInfo(eImprovement).getRouteYieldChanges(iI, eYield));
		}

		iYield += iBestYield;
	} else {
		RouteTypes eRoute = getRouteType();

		if (bBestRoute && ePlayer != NO_PLAYER) {
			eRoute = GET_PLAYER(ePlayer).getBestRoute(this); // K-Mod. (obvious?)
		}

		if (eRoute != NO_ROUTE) {
			iYield += GC.getImprovementInfo(eImprovement).getRouteYieldChanges(eRoute, eYield);
		}
	}

	if (bOptimal || ePlayer == NO_PLAYER) {
		for (int iI = 0; iI < GC.getNumTechInfos(); ++iI) {
			iYield += GC.getImprovementInfo(eImprovement).getTechYieldChanges(iI, eYield);
		}

		// K-Mod note: this doesn't calculate the 'optimal' yield, because it will count negative effects and it will count effects from competing civics.
		// Maybe I'll fix it later.
		for (int iI = 0; iI < GC.getNumCivicInfos(); ++iI) {
			iYield += GC.getCivicInfo((CivicTypes)iI).getImprovementYieldChanges(eImprovement, eYield);
		}
	} else {
		iYield += GET_PLAYER(ePlayer).getImprovementYieldChange(eImprovement, eYield);
		iYield += GET_TEAM(GET_PLAYER(ePlayer).getTeam()).getImprovementYieldChange(eImprovement, eYield);
	}

	if (ePlayer != NO_PLAYER) {
		BonusTypes eBonus = getBonusType(GET_PLAYER(ePlayer).getTeam());

		if (eBonus != NO_BONUS) {
			iYield += GC.getImprovementInfo(eImprovement).getImprovementBonusYield(eBonus, eYield);
		}
	}

	return iYield;
}

int CvPlot::calculateYield(YieldTypes eYield, bool bDisplay) const {
	if (bDisplay && GC.getGameINLINE().isDebugMode()) {
		return getYield(eYield);
	}

	if (getTerrainType() == NO_TERRAIN) {
		return 0;
	}

	if (!isPotentialCityWork()) {
		return 0;
	}

	bool bCity = false;

	PlayerTypes ePlayer = NO_PLAYER;
	TeamTypes eActiveTeam = GC.getGameINLINE().getActiveTeam();
	ImprovementTypes eImprovement = NO_IMPROVEMENT;
	RouteTypes eRoute = NO_ROUTE;
	if (bDisplay) {
		ePlayer = getRevealedOwner(eActiveTeam, false);
		eImprovement = getRevealedImprovementType(eActiveTeam, false);
		eRoute = getRevealedRouteType(eActiveTeam, false);

		if (ePlayer == NO_PLAYER) {
			ePlayer = GC.getGameINLINE().getActivePlayer();
		}
	} else {
		ePlayer = getOwnerINLINE();
		eImprovement = getImprovementType();
		eRoute = getRouteType();
	}

	int iYield = calculateNatureYield(eYield, ((ePlayer != NO_PLAYER) ? GET_PLAYER(ePlayer).getTeam() : NO_TEAM));

	if (eImprovement != NO_IMPROVEMENT) {
		iYield += calculateImprovementYieldChange(eImprovement, eYield, ePlayer);
	}

	if (eRoute != NO_ROUTE) {
		iYield += GC.getRouteInfo(eRoute).getYieldChange(eYield);
	}

	const CvYieldInfo& kYield = GC.getYieldInfo(eYield);
	if (ePlayer != NO_PLAYER) {
		const CvPlayer& kPlayer = GET_PLAYER(ePlayer);
		CvCity* pCity = getPlotCity();

		if (pCity != NULL) {
			if (!bDisplay || pCity->isRevealed(eActiveTeam, false)) {
				iYield += kYield.getCityChange();
				if (kYield.getPopulationChangeDivisor() != 0) {
					iYield += ((pCity->getPopulation() + kYield.getPopulationChangeOffset()) / kYield.getPopulationChangeDivisor());
				}
				bCity = true;
			}
		}

		CvCity* pWorkingCity = getWorkingCity();
		if (pWorkingCity != NULL && !isImpassable(GET_PLAYER(ePlayer).getTeam())) {
			iYield += kPlayer.getPlotYield(eYield);

			if (isWater()) {
				iYield += kPlayer.getSeaPlotYield(eYield);
				if (!bDisplay || pWorkingCity->isRevealed(eActiveTeam, false)) {
					iYield += pWorkingCity->getSeaPlotYield(eYield);
				}
			}

			if (isRiver()) {
				iYield += kPlayer.getRiverPlotYield(eYield);
				if (!bDisplay || pWorkingCity->isRevealed(eActiveTeam, false)) {
					iYield += pWorkingCity->getRiverPlotYield(eYield);
				}
			}

			if (getFeatureType() == (FeatureTypes)GC.getInfoTypeForString("FEATURE_FOREST")) {
				iYield += kPlayer.getForestPlotYield(eYield);
			}
		}
	}

	if (bCity) {
		iYield = std::max(iYield, kYield.getMinCity());
	}

	iYield += GC.getGameINLINE().getPlotExtraYield(m_iX, m_iY, eYield);

	if (ePlayer != NO_PLAYER) {
		const CvPlayer& kPlayer = GET_PLAYER(ePlayer);
		if (kPlayer.getExtraYieldThreshold(eYield) > 0) {
			if (iYield >= kPlayer.getExtraYieldThreshold(eYield)) {
				iYield += GC.getEXTRA_YIELD();
			}
		}

		if (kPlayer.isGoldenAge()) {
			if (iYield >= kYield.getGoldenAgeYieldThreshold()) {
				iYield += kYield.getGoldenAgeYield();
			}
		}
	}

	return std::max(0, iYield);
}


bool CvPlot::hasYield() const {
	for (int iI = 0; iI < NUM_YIELD_TYPES; ++iI) {
		if (getYield((YieldTypes)iI) > 0) {
			return true;
		}
	}

	return false;
}


void CvPlot::updateYield() {
	if (area() == NULL) {
		return;
	}

	bool bChange = false;

	for (int iI = 0; iI < NUM_YIELD_TYPES; ++iI) {
		int iNewYield = calculateYield((YieldTypes)iI);

		if (getYield((YieldTypes)iI) != iNewYield) {
			int iOldYield = getYield((YieldTypes)iI);

			m_aiYield[iI] = iNewYield;
			FAssert(getYield((YieldTypes)iI) >= 0);

			CvCity* pWorkingCity = getWorkingCity();

			if (pWorkingCity != NULL) {
				if (isBeingWorked()) {
					pWorkingCity->changeBaseYieldRate(((YieldTypes)iI), (getYield((YieldTypes)iI) - iOldYield));
				}

				pWorkingCity->AI_setAssignWorkDirty(true);
			}

			bChange = true;
		}
	}

	if (bChange) {
		updateSymbols();
	}
}


int CvPlot::getCulture(PlayerTypes eIndex) const {
	FAssertMsg(eIndex >= 0, "iIndex is expected to be non-negative (invalid Index)");
	FAssertMsg(eIndex < MAX_PLAYERS, "iIndex is expected to be within maximum bounds (invalid Index)");

	if (NULL == m_aiCulture) {
		return 0;
	}

	return m_aiCulture[eIndex];
}


int CvPlot::countTotalCulture() const {
	int iTotalCulture = 0;

	for (int iI = 0; iI < MAX_PLAYERS; ++iI) {
		if (GET_PLAYER((PlayerTypes)iI).isAlive()) {
			iTotalCulture += getCulture((PlayerTypes)iI);
		}
	}

	return iTotalCulture;
}


TeamTypes CvPlot::findHighestCultureTeam() const {
	PlayerTypes eBestPlayer = findHighestCulturePlayer();

	if (NO_PLAYER == eBestPlayer) {
		return NO_TEAM;
	}

	return GET_PLAYER(eBestPlayer).getTeam();
}


PlayerTypes CvPlot::findHighestCulturePlayer() const {
	PlayerTypes eBestPlayer = NO_PLAYER;
	int iBestValue = 0;

	for (int iI = 0; iI < MAX_PLAYERS; ++iI) {
		if (GET_PLAYER((PlayerTypes)iI).isAlive()) {
			int iValue = getCulture((PlayerTypes)iI);

			if (iValue > iBestValue) {
				iBestValue = iValue;
				eBestPlayer = (PlayerTypes)iI;
			}
		}
	}

	return eBestPlayer;
}


int CvPlot::calculateCulturePercent(PlayerTypes eIndex) const {
	int iTotalCulture = countTotalCulture();

	if (iTotalCulture > 0) {
		return ((getCulture(eIndex) * 100) / iTotalCulture);
	}

	return 0;
}


int CvPlot::calculateTeamCulturePercent(TeamTypes eIndex) const {
	int iTeamCulturePercent = 0;

	for (int iI = 0; iI < MAX_PLAYERS; ++iI) {
		if (GET_PLAYER((PlayerTypes)iI).isAlive()) {
			if (GET_PLAYER((PlayerTypes)iI).getTeam() == eIndex) {
				iTeamCulturePercent += calculateCulturePercent((PlayerTypes)iI);
			}
		}
	}

	return iTeamCulturePercent;
}


void CvPlot::setCulture(PlayerTypes eIndex, int iNewValue, bool bUpdate, bool bUpdatePlotGroups) {
	PROFILE_FUNC();

	FAssertMsg(eIndex >= 0, "iIndex is expected to be non-negative (invalid Index)");
	FAssertMsg(eIndex < MAX_PLAYERS, "iIndex is expected to be within maximum bounds (invalid Index)");

	if (getCulture(eIndex) != iNewValue) {
		if (NULL == m_aiCulture) {
			m_aiCulture = new int[MAX_PLAYERS];
			for (int iI = 0; iI < MAX_PLAYERS; ++iI) {
				m_aiCulture[iI] = 0;
			}
		}

		m_aiCulture[eIndex] = iNewValue;
		FAssert(getCulture(eIndex) >= 0);

		if (bUpdate) {
			updateCulture(true, bUpdatePlotGroups);
		}

		CvCity* pCity = getPlotCity();
		if (pCity != NULL) {
			pCity->AI_setAssignWorkDirty(true);
		}
	}
}


void CvPlot::changeCulture(PlayerTypes eIndex, int iChange, bool bUpdate) {
	if (0 != iChange) {
		setCulture(eIndex, (getCulture(eIndex) + iChange), bUpdate, true);
	}
}


int CvPlot::getFoundValue(PlayerTypes eIndex) {
	FAssertMsg(eIndex >= 0, "eIndex is expected to be non-negative (invalid Index)");
	FAssertMsg(eIndex < MAX_PLAYERS, "eIndex is expected to be within maximum bounds (invalid Index)");

	if (NULL == m_aiFoundValue) {
		return 0;
	}

	if (m_aiFoundValue[eIndex] == -1) {
		long lResult = -1;
		if (GC.getUSE_GET_CITY_FOUND_VALUE_CALLBACK()) {
			CyArgsList argsList;
			argsList.add((int)eIndex);
			argsList.add(getX());
			argsList.add(getY());
			gDLL->getPythonIFace()->callFunction(PYGameModule, "getCityFoundValue", argsList.makeFunctionArgs(), &lResult);
		}

		if (lResult == -1) {
			m_aiFoundValue[eIndex] = GET_PLAYER(eIndex).AI_foundValue(getX_INLINE(), getY_INLINE(), -1, true);
		}

		if (m_aiFoundValue[eIndex] > area()->getBestFoundValue(eIndex)) {
			area()->setBestFoundValue(eIndex, m_aiFoundValue[eIndex]);
		}
	}

	return m_aiFoundValue[eIndex];
}


bool CvPlot::isBestAdjacentFound(PlayerTypes eIndex) {
	CvPlayerAI::CvFoundSettings kFoundSet(GET_PLAYER(eIndex), false); // K-Mod

	int iPlotValue = GET_PLAYER(eIndex).AI_foundValue_bulk(getX_INLINE(), getY_INLINE(), kFoundSet);

	if (iPlotValue == 0) {
		return false;
	}

	for (int iI = 0; iI < NUM_DIRECTION_TYPES; ++iI) {
		CvPlot* pAdjacentPlot = plotDirection(getX_INLINE(), getY_INLINE(), ((DirectionTypes)iI));

		if ((pAdjacentPlot != NULL) && pAdjacentPlot->isRevealed(GET_PLAYER(eIndex).getTeam(), false)) {
			if (GET_PLAYER(eIndex).AI_foundValue_bulk(pAdjacentPlot->getX_INLINE(), pAdjacentPlot->getY_INLINE(), kFoundSet) > iPlotValue) {
				return false;
			}
		}
	}

	return true;
}


void CvPlot::setFoundValue(PlayerTypes eIndex, short iNewValue) {
	FAssertMsg(eIndex >= 0, "eIndex is expected to be non-negative (invalid Index)");
	FAssertMsg(eIndex < MAX_PLAYERS, "eIndex is expected to be within maximum bounds (invalid Index)");
	FAssert(iNewValue >= -1);

	if (NULL == m_aiFoundValue && 0 != iNewValue) {
		m_aiFoundValue = new short[MAX_PLAYERS];
		for (int iI = 0; iI < MAX_PLAYERS; ++iI) {
			m_aiFoundValue[iI] = 0;
		}
	}

	if (NULL != m_aiFoundValue) {
		m_aiFoundValue[eIndex] = iNewValue;
	}
}


int CvPlot::getPlayerCityRadiusCount(PlayerTypes eIndex) const {
	FAssertMsg(eIndex >= 0, "eIndex is expected to be non-negative (invalid Index)");
	FAssertMsg(eIndex < MAX_PLAYERS, "eIndex is expected to be within maximum bounds (invalid Index)");

	if (NULL == m_aiPlayerCityRadiusCount) {
		return 0;
	}

	return m_aiPlayerCityRadiusCount[eIndex];
}


bool CvPlot::isPlayerCityRadius(PlayerTypes eIndex) const {
	return (getPlayerCityRadiusCount(eIndex) > 0);
}


void CvPlot::changePlayerCityRadiusCount(PlayerTypes eIndex, int iChange) {
	FAssertMsg(eIndex >= 0, "eIndex is expected to be non-negative (invalid Index)");
	FAssertMsg(eIndex < MAX_PLAYERS, "eIndex is expected to be within maximum bounds (invalid Index)");

	if (0 != iChange) {
		if (NULL == m_aiPlayerCityRadiusCount) {
			m_aiPlayerCityRadiusCount = new char[MAX_PLAYERS];
			for (int iI = 0; iI < MAX_PLAYERS; ++iI) {
				m_aiPlayerCityRadiusCount[iI] = 0;
			}
		}

		m_aiPlayerCityRadiusCount[eIndex] += iChange;
		FAssert(getPlayerCityRadiusCount(eIndex) >= 0);
	}
}


CvPlotGroup* CvPlot::getPlotGroup(PlayerTypes ePlayer) const {
	FAssertMsg(ePlayer >= 0, "eIndex is expected to be non-negative (invalid Index)");
	FAssertMsg(ePlayer < MAX_PLAYERS, "ePlayer is expected to be within maximum bounds (invalid Index)");

	if (NULL == m_aiPlotGroup) {
		return GET_PLAYER(ePlayer).getPlotGroup(FFreeList::INVALID_INDEX);
	}

	return GET_PLAYER(ePlayer).getPlotGroup(m_aiPlotGroup[ePlayer]);
}


CvPlotGroup* CvPlot::getOwnerPlotGroup() const {
	if (getOwnerINLINE() == NO_PLAYER) {
		return NULL;
	}

	return getPlotGroup(getOwnerINLINE());
}


void CvPlot::setPlotGroup(PlayerTypes ePlayer, CvPlotGroup* pNewValue) {
	CvPlotGroup* pOldPlotGroup = getPlotGroup(ePlayer);

	if (pOldPlotGroup != pNewValue) {
		if (NULL == m_aiPlotGroup) {
			m_aiPlotGroup = new int[MAX_PLAYERS];
			for (int iI = 0; iI < MAX_PLAYERS; ++iI) {
				m_aiPlotGroup[iI] = FFreeList::INVALID_INDEX;
			}
		}

		CvCity* pCity = getPlotCity();

		if (ePlayer == getOwnerINLINE()) {
			updatePlotGroupBonus(false);
		}

		if (pOldPlotGroup != NULL) {
			if (pCity != NULL) {
				if (pCity->getOwnerINLINE() == ePlayer) {
					FAssertMsg((0 < GC.getNumBonusInfos()), "GC.getNumBonusInfos() is not greater than zero but an array is being allocated in CvPlot::setPlotGroup");
					for (int iI = 0; iI < GC.getNumBonusInfos(); ++iI) {
						pCity->changeNumBonuses(((BonusTypes)iI), -(pOldPlotGroup->getNumBonuses((BonusTypes)iI)));
					}
				}
			}
		}

		if (pNewValue == NULL) {
			m_aiPlotGroup[ePlayer] = FFreeList::INVALID_INDEX;
		} else {
			m_aiPlotGroup[ePlayer] = pNewValue->getID();
		}

		if (getPlotGroup(ePlayer) != NULL) {
			if (pCity != NULL) {
				if (pCity->getOwnerINLINE() == ePlayer) {
					FAssertMsg((0 < GC.getNumBonusInfos()), "GC.getNumBonusInfos() is not greater than zero but an array is being allocated in CvPlot::setPlotGroup");
					for (int iI = 0; iI < GC.getNumBonusInfos(); ++iI) {
						pCity->changeNumBonuses(((BonusTypes)iI), getPlotGroup(ePlayer)->getNumBonuses((BonusTypes)iI));
					}
				}
			}
		}
		if (ePlayer == getOwnerINLINE()) {
			updatePlotGroupBonus(true);
		}
	}
}


void CvPlot::updatePlotGroup() {
	PROFILE_FUNC();

	for (int iI = 0; iI < MAX_PLAYERS; ++iI) {
		if (GET_PLAYER((PlayerTypes)iI).isAlive()) {
			updatePlotGroup((PlayerTypes)iI);
		}
	}
}


void CvPlot::updatePlotGroup(PlayerTypes ePlayer, bool bRecalculate) {
	if (!(GC.getGameINLINE().isFinalInitialized())) {
		return;
	}

	CvPlotGroup* pPlotGroup = getPlotGroup(ePlayer);

	if (pPlotGroup != NULL) {
		if (bRecalculate) {
			bool bConnected = false;

			if (isTradeNetwork(GET_PLAYER(ePlayer).getTeam())) {
				bConnected = true;

				for (int iI = 0; iI < NUM_DIRECTION_TYPES; ++iI) {
					CvPlot* pAdjacentPlot = plotDirection(getX_INLINE(), getY_INLINE(), ((DirectionTypes)iI));

					if (pAdjacentPlot != NULL) {
						if (pAdjacentPlot->getPlotGroup(ePlayer) == pPlotGroup) {
							if (!isTradeNetworkConnected(pAdjacentPlot, GET_PLAYER(ePlayer).getTeam())) {
								bConnected = false;
								break;
							}
						}
					}
				}
			}

			if (!bConnected) {
				bool bEmpty = (pPlotGroup->getLengthPlots() == 1);
				FAssertMsg(pPlotGroup->getLengthPlots() > 0, "pPlotGroup should have more than 0 plots");

				pPlotGroup->removePlot(this);

				if (!bEmpty) {
					pPlotGroup->recalculatePlots();
				}
			}
		}

		pPlotGroup = getPlotGroup(ePlayer);
	}

	if (isTradeNetwork(GET_PLAYER(ePlayer).getTeam())) {
		for (int iI = 0; iI < NUM_DIRECTION_TYPES; ++iI) {
			CvPlot* pAdjacentPlot = plotDirection(getX_INLINE(), getY_INLINE(), ((DirectionTypes)iI));

			if (pAdjacentPlot != NULL) {
				CvPlotGroup* pAdjacentPlotGroup = pAdjacentPlot->getPlotGroup(ePlayer);

				if ((pAdjacentPlotGroup != NULL) && (pAdjacentPlotGroup != pPlotGroup)) {
					if (isTradeNetworkConnected(pAdjacentPlot, GET_PLAYER(ePlayer).getTeam())) {
						if (pPlotGroup == NULL) {
							pAdjacentPlotGroup->addPlot(this);
							pPlotGroup = pAdjacentPlotGroup;
							FAssertMsg(getPlotGroup(ePlayer) == pPlotGroup, "ePlayer's plot group is expected to equal pPlotGroup");
						} else {
							FAssertMsg(getPlotGroup(ePlayer) == pPlotGroup, "ePlayer's plot group is expected to equal pPlotGroup");
							GC.getMapINLINE().combinePlotGroups(ePlayer, pPlotGroup, pAdjacentPlotGroup);
							pPlotGroup = getPlotGroup(ePlayer);
							FAssertMsg(pPlotGroup != NULL, "PlotGroup is not assigned a valid value");
						}
					}
				}
			}
		}

		if (pPlotGroup == NULL) {
			GET_PLAYER(ePlayer).initPlotGroup(this);
		}
	}
}


int CvPlot::getVisibilityCount(TeamTypes eTeam) const {
	FAssertMsg(eTeam >= 0, "eTeam is expected to be non-negative (invalid Index)");
	FAssertMsg(eTeam < MAX_TEAMS, "eTeam is expected to be within maximum bounds (invalid Index)");

	if (NULL == m_aiVisibilityCount) {
		return 0;
	}

	return m_aiVisibilityCount[eTeam];
}


void CvPlot::changeVisibilityCount(TeamTypes eTeam, int iChange, InvisibleTypes eSeeInvisible, bool bUpdatePlotGroups) {
	FAssertMsg(eTeam >= 0, "eTeam is expected to be non-negative (invalid Index)");
	FAssertMsg(eTeam < MAX_TEAMS, "eTeam is expected to be within maximum bounds (invalid Index)");

	if (iChange != 0) {
		if (NULL == m_aiVisibilityCount) {
			m_aiVisibilityCount = new short[MAX_TEAMS];
			for (int iI = 0; iI < MAX_TEAMS; ++iI) {
				m_aiVisibilityCount[iI] = 0;
			}
		}

		bool bOldVisible = isVisible(eTeam, false);

		m_aiVisibilityCount[eTeam] += iChange;
		FAssert(getVisibilityCount(eTeam) >= 0);

		if (eSeeInvisible != NO_INVISIBLE) {
			changeInvisibleVisibilityCount(eTeam, eSeeInvisible, iChange);
		}

		if (bOldVisible != isVisible(eTeam, false)) {
			if (isVisible(eTeam, false)) {
				setRevealed(eTeam, true, false, NO_TEAM, bUpdatePlotGroups);

				for (int iI = 0; iI < NUM_DIRECTION_TYPES; ++iI) {
					CvPlot* pAdjacentPlot = plotDirection(getX_INLINE(), getY_INLINE(), ((DirectionTypes)iI));
					if (pAdjacentPlot != NULL) {
						// K-Mod. updateRevealedOwner simply checks to see if there is a visible adjacent plot. But we've already checked that, so lets go right to the punch.
						pAdjacentPlot->setRevealedOwner(eTeam, pAdjacentPlot->getOwnerINLINE());
					}
				}

				if (getTeam() != NO_TEAM) {
					GET_TEAM(getTeam()).meet(eTeam, true);
				}
				// K-Mod. Meet the owner of any units you can see.
				{
					PROFILE("CvPlot::changeVisibility -- meet units"); // (this is new, so I want to time it.)
					CvTeam& kTeam = GET_TEAM(eTeam);

					CLLNode<IDInfo>* pUnitNode = headUnitNode();
					while (pUnitNode) {
						CvUnit* pLoopUnit = ::getUnit(pUnitNode->m_data);

						kTeam.meet(GET_PLAYER(pLoopUnit->getVisualOwner(eTeam)).getTeam(), true);

						pUnitNode = nextUnitNode(pUnitNode);
					}
				}
			}

			CvCity* pCity = getPlotCity();
			if (pCity != NULL) {
				pCity->setInfoDirty(true);
			}

			for (int iI = 0; iI < MAX_TEAMS; ++iI) {
				if (GET_TEAM((TeamTypes)iI).isAlive()) {
					if (GET_TEAM((TeamTypes)iI).isStolenVisibility(eTeam)) {
						changeStolenVisibilityCount(((TeamTypes)iI), ((isVisible(eTeam, false)) ? 1 : -1));
					}
				}
			}

			if (eTeam == GC.getGameINLINE().getActiveTeam()) {
				updateFog();
				updateMinimapColor();
				updateCenterUnit();
			}
		}
	}
}


int CvPlot::getStolenVisibilityCount(TeamTypes eTeam) const {
	FAssertMsg(eTeam >= 0, "eTeam is expected to be non-negative (invalid Index)");
	FAssertMsg(eTeam < MAX_TEAMS, "eTeam is expected to be within maximum bounds (invalid Index)");

	if (NULL == m_aiStolenVisibilityCount) {
		return 0;
	}

	return m_aiStolenVisibilityCount[eTeam];
}


void CvPlot::changeStolenVisibilityCount(TeamTypes eTeam, int iChange) {
	FAssertMsg(eTeam >= 0, "eTeam is expected to be non-negative (invalid Index)");
	FAssertMsg(eTeam < MAX_TEAMS, "eTeam is expected to be within maximum bounds (invalid Index)");

	if (iChange != 0) {
		if (NULL == m_aiStolenVisibilityCount) {
			m_aiStolenVisibilityCount = new short[MAX_TEAMS];
			for (int iI = 0; iI < MAX_TEAMS; ++iI) {
				m_aiStolenVisibilityCount[iI] = 0;
			}
		}

		bool bOldVisible = isVisible(eTeam, false);

		m_aiStolenVisibilityCount[eTeam] += iChange;
		FAssert(getStolenVisibilityCount(eTeam) >= 0);

		if (bOldVisible != isVisible(eTeam, false)) {
			if (isVisible(eTeam, false)) {
				setRevealed(eTeam, true, false, NO_TEAM, true);
			}

			CvCity* pCity = getPlotCity();

			if (pCity != NULL) {
				pCity->setInfoDirty(true);
			}

			if (eTeam == GC.getGameINLINE().getActiveTeam()) {
				updateFog();
				updateMinimapColor();
				updateCenterUnit();
			}
		}
	}
}


int CvPlot::getBlockadedCount(TeamTypes eTeam) const {
	FAssertMsg(eTeam >= 0, "eTeam is expected to be non-negative (invalid Index)");
	FAssertMsg(eTeam < MAX_TEAMS, "eTeam is expected to be within maximum bounds (invalid Index)");

	if (NULL == m_aiBlockadedCount) {
		return 0;
	}

	return m_aiBlockadedCount[eTeam];
}

void CvPlot::changeBlockadedCount(TeamTypes eTeam, int iChange) {
	FAssertMsg(eTeam >= 0, "eTeam is expected to be non-negative (invalid Index)");
	FAssertMsg(eTeam < MAX_TEAMS, "eTeam is expected to be within maximum bounds (invalid Index)");

	if (iChange != 0) {
		if (NULL == m_aiBlockadedCount) {
			m_aiBlockadedCount = new short[MAX_TEAMS];
			for (int iI = 0; iI < MAX_TEAMS; ++iI) {
				m_aiBlockadedCount[iI] = 0;
			}
		}

		m_aiBlockadedCount[eTeam] += iChange;

		FAssert(getBlockadedCount(eTeam) == 0 || isWater());

		// Hack so that never get negative blockade counts as a result of fixing issue causing rare permanent blockades.
		if (getBlockadedCount(eTeam) < 0) {
			m_aiBlockadedCount[eTeam] = 0;
		}

		CvCity* pWorkingCity = getWorkingCity();
		if (NULL != pWorkingCity) {
			pWorkingCity->AI_setAssignWorkDirty(true);
		}
	}
}

PlayerTypes CvPlot::getRevealedOwner(TeamTypes eTeam, bool bDebug) const {
	if (bDebug && GC.getGameINLINE().isDebugMode()) {
		return getOwnerINLINE();
	} else {
		FAssertMsg(eTeam >= 0, "eTeam is expected to be non-negative (invalid Index)");
		FAssertMsg(eTeam < MAX_TEAMS, "eTeam is expected to be within maximum bounds (invalid Index)");

		if (NULL == m_aiRevealedOwner) {
			return NO_PLAYER;
		}

		return (PlayerTypes)m_aiRevealedOwner[eTeam];
	}
}


TeamTypes CvPlot::getRevealedTeam(TeamTypes eTeam, bool bDebug) const {
	FAssertMsg(eTeam >= 0, "eTeam is expected to be non-negative (invalid Index)");
	FAssertMsg(eTeam < MAX_TEAMS, "eTeam is expected to be within maximum bounds (invalid Index)");

	PlayerTypes eRevealedOwner = getRevealedOwner(eTeam, bDebug);

	if (eRevealedOwner != NO_PLAYER) {
		return GET_PLAYER(eRevealedOwner).getTeam();
	} else {
		return NO_TEAM;
	}
}


void CvPlot::setRevealedOwner(TeamTypes eTeam, PlayerTypes eNewValue) {
	FAssertMsg(eTeam >= 0, "eTeam is expected to be non-negative (invalid Index)");
	FAssertMsg(eTeam < MAX_TEAMS, "eTeam is expected to be within maximum bounds (invalid Index)");

	if (getRevealedOwner(eTeam, false) != eNewValue) {
		if (NULL == m_aiRevealedOwner) {
			m_aiRevealedOwner = new char[MAX_TEAMS];
			for (int iI = 0; iI < MAX_TEAMS; ++iI) {
				m_aiRevealedOwner[iI] = -1;
			}
		}

		m_aiRevealedOwner[eTeam] = eNewValue;
		if (eNewValue != NO_PLAYER)
			GET_TEAM(eTeam).makeHasSeen(GET_PLAYER(eNewValue).getTeam());

		if (eTeam == GC.getGameINLINE().getActiveTeam()) {
			updateMinimapColor();

			if (GC.IsGraphicsInitialized()) {
				gDLL->getInterfaceIFace()->setDirty(GlobeLayer_DIRTY_BIT, true);

				gDLL->getEngineIFace()->SetDirty(CultureBorders_DIRTY_BIT, true);
			}
		}
	}

	FAssert((NULL == m_aiRevealedOwner) || (m_aiRevealedOwner[eTeam] == eNewValue));
}


void CvPlot::updateRevealedOwner(TeamTypes eTeam) {
	FAssertMsg(eTeam >= 0, "eTeam is expected to be non-negative (invalid Index)");
	FAssertMsg(eTeam < MAX_TEAMS, "eTeam is expected to be within maximum bounds (invalid Index)");

	bool bRevealed = false;

	if (!bRevealed) {
		if (isVisible(eTeam, false)) {
			bRevealed = true;
		}
	}

	if (!bRevealed) {
		for (int iI = 0; iI < NUM_DIRECTION_TYPES; ++iI) {
			CvPlot* pAdjacentPlot = plotDirection(getX_INLINE(), getY_INLINE(), ((DirectionTypes)iI));

			if (pAdjacentPlot != NULL) {
				if (pAdjacentPlot->isVisible(eTeam, false)) {
					bRevealed = true;
					break;
				}
			}
		}
	}

	if (bRevealed) {
		setRevealedOwner(eTeam, getOwnerINLINE());
	}
}


bool CvPlot::isRiverCrossing(DirectionTypes eIndex) const {
	FAssertMsg(eIndex < NUM_DIRECTION_TYPES, "eTeam is expected to be within maximum bounds (invalid Index)");

	if (eIndex == NO_DIRECTION) {
		return false;
	}

	if (NULL == m_abRiverCrossing) {
		return false;
	}

	return m_abRiverCrossing[eIndex];
}


void CvPlot::updateRiverCrossing(DirectionTypes eIndex) {
	FAssertMsg(eIndex >= 0, "eTeam is expected to be non-negative (invalid Index)");
	FAssertMsg(eIndex < NUM_DIRECTION_TYPES, "eTeam is expected to be within maximum bounds (invalid Index)");

	CvPlot* pCornerPlot = NULL;
	bool bValid = false;
	CvPlot* pPlot = plotDirection(getX_INLINE(), getY_INLINE(), eIndex);

	if ((NULL == pPlot || !pPlot->isWater()) && !isWater()) {
		switch (eIndex) {
		case DIRECTION_NORTH:
			if (pPlot != NULL) {
				bValid = pPlot->isNOfRiver();
			}
			break;

		case DIRECTION_NORTHEAST:
			pCornerPlot = plotDirection(getX_INLINE(), getY_INLINE(), DIRECTION_NORTH);
			break;

		case DIRECTION_EAST:
			bValid = isWOfRiver();
			break;

		case DIRECTION_SOUTHEAST:
			pCornerPlot = this;
			break;

		case DIRECTION_SOUTH:
			bValid = isNOfRiver();
			break;

		case DIRECTION_SOUTHWEST:
			pCornerPlot = plotDirection(getX_INLINE(), getY_INLINE(), DIRECTION_WEST);
			break;

		case DIRECTION_WEST:
			if (pPlot != NULL) {
				bValid = pPlot->isWOfRiver();
			}
			break;

		case DIRECTION_NORTHWEST:
			pCornerPlot = plotDirection(getX_INLINE(), getY_INLINE(), DIRECTION_NORTHWEST);
			break;

		default:
			FAssert(false);
			break;
		}

		if (pCornerPlot != NULL) {
			CvPlot* pNorthEastPlot = plotDirection(pCornerPlot->getX_INLINE(), pCornerPlot->getY_INLINE(), DIRECTION_EAST);
			CvPlot* pSouthEastPlot = plotDirection(pCornerPlot->getX_INLINE(), pCornerPlot->getY_INLINE(), DIRECTION_SOUTHEAST);
			CvPlot* pSouthWestPlot = plotDirection(pCornerPlot->getX_INLINE(), pCornerPlot->getY_INLINE(), DIRECTION_SOUTH);
			CvPlot* pNorthWestPlot = pCornerPlot;

			if (pSouthWestPlot && pNorthWestPlot && pSouthEastPlot && pNorthEastPlot) {
				if (pSouthWestPlot->isWOfRiver() && pNorthWestPlot->isWOfRiver()) {
					bValid = true;
				} else if (pNorthEastPlot->isNOfRiver() && pNorthWestPlot->isNOfRiver()) {
					bValid = true;
				} else if ((eIndex == DIRECTION_NORTHEAST) || (eIndex == DIRECTION_SOUTHWEST)) {
					if (pNorthEastPlot->isNOfRiver() && (pNorthWestPlot->isWOfRiver() || pNorthWestPlot->isWater())) {
						bValid = true;
					} else if ((pNorthEastPlot->isNOfRiver() || pSouthEastPlot->isWater()) && pNorthWestPlot->isWOfRiver()) {
						bValid = true;
					} else if (pSouthWestPlot->isWOfRiver() && (pNorthWestPlot->isNOfRiver() || pNorthWestPlot->isWater())) {
						bValid = true;
					} else if ((pSouthWestPlot->isWOfRiver() || pSouthEastPlot->isWater()) && pNorthWestPlot->isNOfRiver()) {
						bValid = true;
					}
				} else {
					FAssert((eIndex == DIRECTION_SOUTHEAST) || (eIndex == DIRECTION_NORTHWEST));

					if (pNorthWestPlot->isNOfRiver() && (pNorthWestPlot->isWOfRiver() || pNorthEastPlot->isWater())) {
						bValid = true;
					} else if ((pNorthWestPlot->isNOfRiver() || pSouthWestPlot->isWater()) && pNorthWestPlot->isWOfRiver()) {
						bValid = true;
					} else if (pNorthEastPlot->isNOfRiver() && (pSouthWestPlot->isWOfRiver() || pSouthWestPlot->isWater())) {
						bValid = true;
					} else if ((pNorthEastPlot->isNOfRiver() || pNorthEastPlot->isWater()) && pSouthWestPlot->isWOfRiver()) {
						bValid = true;
					}
				}
			}

		}
	}

	if (isRiverCrossing(eIndex) != bValid) {
		if (NULL == m_abRiverCrossing) {
			m_abRiverCrossing = new bool[NUM_DIRECTION_TYPES];
			for (int iI = 0; iI < NUM_DIRECTION_TYPES; ++iI) {
				m_abRiverCrossing[iI] = false;
			}
		}

		m_abRiverCrossing[eIndex] = bValid;

		changeRiverCrossingCount((isRiverCrossing(eIndex)) ? 1 : -1);
	}
}


void CvPlot::updateRiverCrossing() {
	for (int iI = 0; iI < NUM_DIRECTION_TYPES; ++iI) {
		updateRiverCrossing((DirectionTypes)iI);
	}
}


bool CvPlot::isRevealed(TeamTypes eTeam, bool bDebug) const {
	FAssertMsg(eTeam >= 0, "eTeam is expected to be non-negative (invalid Index)");
	FAssertMsg(eTeam < MAX_TEAMS, "eTeam is expected to be within maximum bounds (invalid Index)");

	if (bDebug && GC.getGameINLINE().isDebugMode()) {
		return true;
	}

	if (NULL == m_abRevealed) {
		return false;
	}

	return m_abRevealed[eTeam];
}


void CvPlot::setRevealed(TeamTypes eTeam, bool bNewValue, bool bTerrainOnly, TeamTypes eFromTeam, bool bUpdatePlotGroup) {
	FAssertMsg(eTeam >= 0, "eTeam is expected to be non-negative (invalid Index)");
	FAssertMsg(eTeam < MAX_TEAMS, "eTeam is expected to be within maximum bounds (invalid Index)");

	CvCity* pCity = getPlotCity();

	bool bFirstReveal = (NULL == m_abRevealed);

	if (isRevealed(eTeam, false) != bNewValue) {
		if (bFirstReveal) {
			m_abRevealed = new bool[MAX_TEAMS];
			for (int iI = 0; iI < MAX_TEAMS; ++iI) {
				m_abRevealed[iI] = false;
			}
		}

		m_abRevealed[eTeam] = bNewValue;

		if (area()) {
			area()->changeNumRevealedTiles(eTeam, ((isRevealed(eTeam, false)) ? 1 : -1));
		}

		if (bUpdatePlotGroup) {
			for (int iI = 0; iI < MAX_PLAYERS; ++iI) {
				if (GET_PLAYER((PlayerTypes)iI).isAlive()) {
					if (GET_PLAYER((PlayerTypes)iI).getTeam() == eTeam) {
						updatePlotGroup((PlayerTypes)iI);
					}
				}
			}
		}

		if (eTeam == GC.getGameINLINE().getActiveTeam()) {
			updateSymbols();
			updateFog();
			updateVisibility();

			gDLL->getInterfaceIFace()->setDirty(MinimapSection_DIRTY_BIT, true);
			gDLL->getInterfaceIFace()->setDirty(GlobeLayer_DIRTY_BIT, true);
		}

		if (isRevealed(eTeam, false)) {
			// ONEVENT - PlotRevealed
			CvEventReporter::getInstance().plotRevealed(this, eTeam);
		}
	}

	if (getFeatureType() != NO_FEATURE) {
		FeatureTypes eFeatureType = getFeatureType();
		const CvFeatureInfo& kFeature = GC.getFeatureInfo(eFeatureType);
		if (bFirstReveal && kFeature.isUnique()) {
			for (PlayerTypes ePlayer = (PlayerTypes)0; ePlayer < MAX_CIV_PLAYERS; ePlayer = (PlayerTypes)(ePlayer + 1)) {
				CvPlayer& kPlayer = GET_PLAYER(ePlayer);
				if (kPlayer.getTeam() == eTeam && kPlayer.isHuman()) {

					// Check if this is a multi-plot wonder as the player may have had the movie/notification before
					bool bPreviousFound = false;
					int iWonderSize = kFeature.getUniqueSize();
					if (iWonderSize > 1) {
						// Loop through the possible other plots and if we have revealed one that also has the feature skip the notification
						for (int iX = getX_INLINE() - iWonderSize + 1; iX <= getX_INLINE() + iWonderSize - 1 && !bPreviousFound; iX++) {
							for (int iY = getY_INLINE() - iWonderSize + 1; iY <= getY_INLINE() + iWonderSize - 1 && !bPreviousFound; iY++) {
								CvPlot* pLoopPlot = GC.getMapINLINE().plot(iX, iY);
								if (pLoopPlot == this)
									continue;

								if (pLoopPlot->isRevealed(eTeam, false) && pLoopPlot->getFeatureType() == eFeatureType) {
									bPreviousFound = true;
								}
							}
						}
					}

					if (!bPreviousFound) {
						CvWString szBuffer = gDLL->getText("TXT_KEY_WONDERDISCOVERED_YOU", kFeature.getDescription());
						gDLL->getInterfaceIFace()->addHumanMessage(ePlayer, false, GC.getEVENT_MESSAGE_TIME(), szBuffer, "AS2D_WONDER_BUILDING_BUILD", MESSAGE_TYPE_INFO, kFeature.getButton(), (ColorTypes)GC.getInfoTypeForString("COLOR_WHITE"), getX_INLINE(), getY_INLINE(), true, true);

						if (!CvString(kFeature.getMovieArtDef()).empty()) {
							// show movie
							CvPopupInfo* pInfo = new CvPopupInfo(BUTTONPOPUP_PYTHON_SCREEN);
							if (NULL != pInfo) {
								pInfo->setText(L"showWonderMovie");
								pInfo->setData1((int)getFeatureType());
								pInfo->setData3(6);
								kPlayer.addPopup(pInfo);
							}
						}
					}
				}
			}
		}
	}

	if (!bTerrainOnly) {
		if (isRevealed(eTeam, false)) {
			if (eFromTeam == NO_TEAM) {
				setRevealedOwner(eTeam, getOwnerINLINE());
				setRevealedImprovementType(eTeam, getImprovementType());
				setRevealedRouteType(eTeam, getRouteType());

				if (pCity != NULL) {
					pCity->setRevealed(eTeam, true);
				}
			} else {
				if (getRevealedOwner(eFromTeam, false) == getOwnerINLINE()) {
					setRevealedOwner(eTeam, getRevealedOwner(eFromTeam, false));
				}

				if (getRevealedImprovementType(eFromTeam, false) == getImprovementType()) {
					setRevealedImprovementType(eTeam, getRevealedImprovementType(eFromTeam, false));
				}

				if (getRevealedRouteType(eFromTeam, false) == getRouteType()) {
					setRevealedRouteType(eTeam, getRevealedRouteType(eFromTeam, false));
				}

				if (pCity != NULL) {
					if (pCity->isRevealed(eFromTeam, false)) {
						pCity->setRevealed(eTeam, true);
					}
				}
			}
		} else {
			setRevealedOwner(eTeam, NO_PLAYER);
			setRevealedImprovementType(eTeam, NO_IMPROVEMENT);
			setRevealedRouteType(eTeam, NO_ROUTE);

			if (pCity != NULL) {
				pCity->setRevealed(eTeam, false);
			}
		}
	}
}

bool CvPlot::isAdjacentRevealed(TeamTypes eTeam) const {
	for (int iI = 0; iI < NUM_DIRECTION_TYPES; ++iI) {
		CvPlot* pAdjacentPlot = plotDirection(getX_INLINE(), getY_INLINE(), ((DirectionTypes)iI));
		if (pAdjacentPlot != NULL) {
			if (pAdjacentPlot->isRevealed(eTeam, false)) {
				return true;
			}
		}
	}

	return false;
}

bool CvPlot::isAdjacentNonrevealed(TeamTypes eTeam) const {
	for (int iI = 0; iI < NUM_DIRECTION_TYPES; ++iI) {
		CvPlot* pAdjacentPlot = plotDirection(getX_INLINE(), getY_INLINE(), ((DirectionTypes)iI));
		if (pAdjacentPlot != NULL) {
			if (!pAdjacentPlot->isRevealed(eTeam, false)) {
				return true;
			}
		}
	}

	return false;
}


ImprovementTypes CvPlot::getRevealedImprovementType(TeamTypes eTeam, bool bDebug) const {
	if (bDebug && GC.getGameINLINE().isDebugMode()) {
		return getImprovementType();
	} else {
		FAssertMsg(eTeam >= 0, "eTeam is expected to be non-negative (invalid Index)");
		FAssertMsg(eTeam < MAX_TEAMS, "eTeam is expected to be within maximum bounds (invalid Index)");

		if (NULL == m_aeRevealedImprovementType) {
			return NO_IMPROVEMENT;
		}

		return (ImprovementTypes)m_aeRevealedImprovementType[eTeam];
	}
}


void CvPlot::setRevealedImprovementType(TeamTypes eTeam, ImprovementTypes eNewValue) {
	FAssertMsg(eTeam >= 0, "eTeam is expected to be non-negative (invalid Index)");
	FAssertMsg(eTeam < MAX_TEAMS, "eTeam is expected to be within maximum bounds (invalid Index)");

	if (getRevealedImprovementType(eTeam, false) != eNewValue) {
		if (NULL == m_aeRevealedImprovementType) {
			m_aeRevealedImprovementType = new short[MAX_TEAMS];
			for (int iI = 0; iI < MAX_TEAMS; ++iI) {
				m_aeRevealedImprovementType[iI] = NO_IMPROVEMENT;
			}
		}

		m_aeRevealedImprovementType[eTeam] = eNewValue;

		if (eTeam == GC.getGameINLINE().getActiveTeam()) {
			updateSymbols();
			setLayoutDirty(true);
			//gDLL->getEngineIFace()->SetDirty(GlobeTexture_DIRTY_BIT, true);
		}
	}
}


RouteTypes CvPlot::getRevealedRouteType(TeamTypes eTeam, bool bDebug) const {
	if (bDebug && GC.getGameINLINE().isDebugMode()) {
		return getRouteType();
	} else {
		FAssertMsg(eTeam >= 0, "eTeam is expected to be non-negative (invalid Index)");
		FAssertMsg(eTeam < MAX_TEAMS, "eTeam is expected to be within maximum bounds (invalid Index)");

		if (NULL == m_aeRevealedRouteType) {
			return NO_ROUTE;
		}

		return (RouteTypes)m_aeRevealedRouteType[eTeam];
	}
}


void CvPlot::setRevealedRouteType(TeamTypes eTeam, RouteTypes eNewValue) {
	FAssertMsg(eTeam >= 0, "eTeam is expected to be non-negative (invalid Index)");
	FAssertMsg(eTeam < MAX_TEAMS, "eTeam is expected to be within maximum bounds (invalid Index)");

	if (getRevealedRouteType(eTeam, false) != eNewValue) {
		if (NULL == m_aeRevealedRouteType) {
			m_aeRevealedRouteType = new short[MAX_TEAMS];
			for (int iI = 0; iI < MAX_TEAMS; ++iI) {
				m_aeRevealedRouteType[iI] = NO_ROUTE;
			}
		}

		m_aeRevealedRouteType[eTeam] = eNewValue;

		if (eTeam == GC.getGameINLINE().getActiveTeam()) {
			updateSymbols();
			updateRouteSymbol(true, true);
		}
	}
}


int CvPlot::getBuildProgress(BuildTypes eBuild) const {
	if (NULL == m_paiBuildProgress) {
		return 0;
	}

	return m_paiBuildProgress[eBuild];
}


// Returns true if build finished...
// The signature of this method has changed, but as it was not referenced in Python it was safe to do so
bool CvPlot::changeBuildProgress(BuildTypes eBuild, int iChange, CvUnit* pUnit) {

	FAssertMsg(pUnit != NULL, "pUnit should be valid");

	CvWString szBuffer;
	bool bFinished = false;

	if (iChange != 0) {
		if (NULL == m_paiBuildProgress) {
			m_paiBuildProgress = new short[GC.getNumBuildInfos()];
			for (int iI = 0; iI < GC.getNumBuildInfos(); ++iI) {
				m_paiBuildProgress[iI] = 0;
			}
		}

		m_paiBuildProgress[eBuild] += iChange;
		FAssert(getBuildProgress(eBuild) >= 0);

		if (getBuildProgress(eBuild) >= getBuildTime(eBuild)) {
			m_paiBuildProgress[eBuild] = 0;

			if (GC.getBuildInfo(eBuild).getImprovement() != NO_IMPROVEMENT) {
				setImprovementType((ImprovementTypes)GC.getBuildInfo(eBuild).getImprovement());
			}

			if (GC.getBuildInfo(eBuild).getRoute() != NO_ROUTE) {
				setRouteType((RouteTypes)GC.getBuildInfo(eBuild).getRoute(), true);
			}

			if (getFeatureType() != NO_FEATURE) {
				if (pUnit && !pUnit->isBuildLeaveFeature(eBuild, getFeatureType())) {
					if (GC.getBuildInfo(eBuild).isFeatureRemove(getFeatureType())) {
						CvCity* pCity;
						int iProduction = getFeatureProduction(eBuild, pUnit->getTeam(), &pCity);
						if (iProduction > 0) {
							pCity->changeFeatureProduction(iProduction);

							szBuffer = gDLL->getText("TXT_KEY_MISC_CLEARING_FEATURE_BONUS", GC.getFeatureInfo(getFeatureType()).getTextKeyWide(), iProduction, pCity->getNameKey());
							gDLL->getInterfaceIFace()->addHumanMessage(pCity->getOwnerINLINE(), false, GC.getEVENT_MESSAGE_TIME(), szBuffer, ARTFILEMGR.getInterfaceArtInfo("WORLDBUILDER_CITY_EDIT")->getPath(), MESSAGE_TYPE_INFO, GC.getFeatureInfo(getFeatureType()).getButton(), (ColorTypes)GC.getInfoTypeForString("COLOR_WHITE"), getX_INLINE(), getY_INLINE(), true, true);
						}

						// Python Event
						CvEventReporter::getInstance().plotFeatureRemoved(this, getFeatureType(), pCity);

						setFeatureType(NO_FEATURE);
					}
				}
			}

			bFinished = true;
		}
	}

	return bFinished;
}


void CvPlot::updateFeatureSymbolVisibility() {
	//PROFILE_FUNC();

	if (!GC.IsGraphicsInitialized()) {
		return;
	}

	if (m_pFeatureSymbol != NULL) {
		bool bVisible = isRevealed(GC.getGameINLINE().getActiveTeam(), true);
		if (getFeatureType() != NO_FEATURE) {
			if (GC.getFeatureInfo(getFeatureType()).isVisibleAlways())
				bVisible = true;
		}

		bool wasVisible = !gDLL->getFeatureIFace()->IsHidden(m_pFeatureSymbol);
		if (wasVisible != bVisible) {
			gDLL->getFeatureIFace()->Hide(m_pFeatureSymbol, !bVisible);
			gDLL->getEngineIFace()->MarkPlotTextureAsDirty(m_iX, m_iY);
		}
	}
}


void CvPlot::updateFeatureSymbol(bool bForce) {
	//PROFILE_FUNC();

	if (!GC.IsGraphicsInitialized()) {
		return;
	}

	FeatureTypes eFeature = getFeatureType();

	gDLL->getEngineIFace()->RebuildTileArt(m_iX, m_iY);

	if ((eFeature == NO_FEATURE) ||
		(GC.getFeatureInfo(eFeature).getArtInfo()->isRiverArt()) ||
		(GC.getFeatureInfo(eFeature).getArtInfo()->getTileArtType() != TILE_ART_TYPE_NONE)) {
		gDLL->getFeatureIFace()->destroy(m_pFeatureSymbol);
		return;
	}

	if (bForce || (m_pFeatureSymbol == NULL) || (gDLL->getFeatureIFace()->getFeature(m_pFeatureSymbol) != eFeature)) {
		gDLL->getFeatureIFace()->destroy(m_pFeatureSymbol);
		m_pFeatureSymbol = gDLL->getFeatureIFace()->createFeature();

		FAssertMsg(m_pFeatureSymbol != NULL, "m_pFeatureSymbol is not expected to be equal with NULL");

		gDLL->getFeatureIFace()->init(m_pFeatureSymbol, 0, 0, eFeature, this);

		updateFeatureSymbolVisibility();
	} else {
		gDLL->getEntityIFace()->updatePosition((CvEntity*)m_pFeatureSymbol); //update position and contours
	}
}


CvRoute* CvPlot::getRouteSymbol() const {
	return m_pRouteSymbol;
}


// XXX route symbols don't really exist anymore...
void CvPlot::updateRouteSymbol(bool bForce, bool bAdjacent) {
	PROFILE_FUNC();

	if (!GC.IsGraphicsInitialized()) {
		return;
	}

	if (bAdjacent) {
		for (int iI = 0; iI < NUM_DIRECTION_TYPES; ++iI) {
			CvPlot* pAdjacentPlot = plotDirection(getX_INLINE(), getY_INLINE(), ((DirectionTypes)iI));

			if (pAdjacentPlot != NULL) {
				pAdjacentPlot->updateRouteSymbol(bForce, false);
			}
		}
	}

	RouteTypes eRoute = getRevealedRouteType(GC.getGameINLINE().getActiveTeam(), true);
	if (eRoute == NO_ROUTE) {
		gDLL->getRouteIFace()->destroy(m_pRouteSymbol);
		return;
	}

	if (bForce || (m_pRouteSymbol == NULL) || (gDLL->getRouteIFace()->getRoute(m_pRouteSymbol) != eRoute)) {
		gDLL->getRouteIFace()->destroy(m_pRouteSymbol);
		m_pRouteSymbol = gDLL->getRouteIFace()->createRoute();
		FAssertMsg(m_pRouteSymbol != NULL, "m_pRouteSymbol is not expected to be equal with NULL");

		gDLL->getRouteIFace()->init(m_pRouteSymbol, 0, 0, eRoute, this);
		setLayoutDirty(true);
	} else {
		gDLL->getEntityIFace()->updatePosition((CvEntity*)m_pRouteSymbol); //update position and contours
	}
}


CvRiver* CvPlot::getRiverSymbol() const {
	return m_pRiverSymbol;
}


CvFeature* CvPlot::getFeatureSymbol() const {
	return m_pFeatureSymbol;
}


void CvPlot::updateRiverSymbol(bool bForce, bool bAdjacent) {
	//PROFILE_FUNC();

	if (!GC.IsGraphicsInitialized()) {
		return;
	}

	if (bAdjacent) {
		for (int i = 0; i < NUM_DIRECTION_TYPES; i++) {
			CvPlot* pAdjacentPlot = plotDirection(getX_INLINE(), getY_INLINE(), ((DirectionTypes)i));
			if (pAdjacentPlot != NULL) {
				pAdjacentPlot->updateRiverSymbol(bForce, false);
			}
		}
	}

	if (!isRiverMask()) {
		gDLL->getRiverIFace()->destroy(m_pRiverSymbol);
		return;
	}

	if (bForce || (m_pRiverSymbol == NULL)) {
		//create river
		gDLL->getRiverIFace()->destroy(m_pRiverSymbol);
		m_pRiverSymbol = gDLL->getRiverIFace()->createRiver();
		FAssertMsg(m_pRiverSymbol != NULL, "m_pRiverSymbol is not expected to be equal with NULL");
		gDLL->getRiverIFace()->init(m_pRiverSymbol, 0, 0, 0, this);

		//force tree cuts for adjacent plots
		DirectionTypes affectedDirections[] = { NO_DIRECTION, DIRECTION_EAST, DIRECTION_SOUTHEAST, DIRECTION_SOUTH };
		for (int i = 0; i < 4; i++) {
			CvPlot* pAdjacentPlot = plotDirection(getX_INLINE(), getY_INLINE(), affectedDirections[i]);
			if (pAdjacentPlot != NULL) {
				gDLL->getEngineIFace()->ForceTreeOffsets(pAdjacentPlot->getX(), pAdjacentPlot->getY());
			}
		}

		//cut out canyons
		gDLL->getEngineIFace()->RebuildRiverPlotTile(getX_INLINE(), getY_INLINE(), true, false);

		//recontour adjacent rivers
		for (int i = 0; i < NUM_DIRECTION_TYPES; i++) {
			CvPlot* pAdjacentPlot = plotDirection(getX_INLINE(), getY_INLINE(), ((DirectionTypes)i));
			if ((pAdjacentPlot != NULL) && (pAdjacentPlot->m_pRiverSymbol != NULL)) {
				gDLL->getEntityIFace()->updatePosition((CvEntity*)pAdjacentPlot->m_pRiverSymbol); //update position and contours
			}
		}

		// update the symbol
		setLayoutDirty(true);
	}

	//recontour rivers
	gDLL->getEntityIFace()->updatePosition((CvEntity*)m_pRiverSymbol); //update position and contours
}


void CvPlot::updateRiverSymbolArt(bool bAdjacent) {
	//this is used to update floodplain features
	gDLL->getEntityIFace()->setupFloodPlains(m_pRiverSymbol);
	if (bAdjacent) {
		for (int i = 0; i < NUM_DIRECTION_TYPES; i++) {
			CvPlot* pAdjacentPlot = plotDirection(getX_INLINE(), getY_INLINE(), (DirectionTypes)i);
			if ((pAdjacentPlot != NULL) && (pAdjacentPlot->m_pRiverSymbol != NULL)) {
				gDLL->getEntityIFace()->setupFloodPlains(pAdjacentPlot->m_pRiverSymbol);
			}
		}
	}
}


CvFlagEntity* CvPlot::getFlagSymbol() const {
	return m_pFlagSymbol;
}

CvFlagEntity* CvPlot::getFlagSymbolOffset() const {
	return m_pFlagSymbolOffset;
}

void CvPlot::updateFlagSymbol() {
	//PROFILE_FUNC();

	if (!GC.IsGraphicsInitialized()) {
		return;
	}

	PlayerTypes ePlayer = NO_PLAYER;
	PlayerTypes ePlayerOffset = NO_PLAYER;

	CvUnit* pCenterUnit = getDebugCenterUnit(); // K-Mod

	//get the plot's unit's flag
	if (pCenterUnit != NULL) {
		ePlayer = pCenterUnit->getVisualOwner();
	}

	//get moving unit's flag
	if (gDLL->getInterfaceIFace()->getSingleMoveGotoPlot() == this) {
		if (ePlayer == NO_PLAYER) {
			ePlayer = GC.getGameINLINE().getActivePlayer();
		} else {
			ePlayerOffset = GC.getGameINLINE().getActivePlayer();
		}
	}

	//don't put two of the same flags
	if (ePlayerOffset == ePlayer) {
		ePlayerOffset = NO_PLAYER;
	}

	//destroy old flags
	if (ePlayer == NO_PLAYER) {
		gDLL->getFlagEntityIFace()->destroy(m_pFlagSymbol);
	}
	if (ePlayerOffset == NO_PLAYER) {
		gDLL->getFlagEntityIFace()->destroy(m_pFlagSymbolOffset);
	}

	//create and/or update unit's flag
	if (ePlayer != NO_PLAYER) {
		if ((m_pFlagSymbol == NULL) || (gDLL->getFlagEntityIFace()->getPlayer(m_pFlagSymbol) != ePlayer)) {
			if (m_pFlagSymbol != NULL) {
				gDLL->getFlagEntityIFace()->destroy(m_pFlagSymbol);
			}
			m_pFlagSymbol = gDLL->getFlagEntityIFace()->create(ePlayer);
			if (m_pFlagSymbol != NULL) {
				gDLL->getFlagEntityIFace()->setPlot(m_pFlagSymbol, this, false);
			}
		}

		if (m_pFlagSymbol != NULL) {
			gDLL->getFlagEntityIFace()->updateUnitInfo(m_pFlagSymbol, this, false);
		}
	}

	//create and/or update offset flag
	if (ePlayerOffset != NO_PLAYER) {
		if ((m_pFlagSymbolOffset == NULL) || (gDLL->getFlagEntityIFace()->getPlayer(m_pFlagSymbolOffset) != ePlayerOffset)) {
			if (m_pFlagSymbolOffset != NULL) {
				gDLL->getFlagEntityIFace()->destroy(m_pFlagSymbolOffset);
			}
			m_pFlagSymbolOffset = gDLL->getFlagEntityIFace()->create(ePlayerOffset);
			if (m_pFlagSymbolOffset != NULL) {
				gDLL->getFlagEntityIFace()->setPlot(m_pFlagSymbolOffset, this, true);
			}
		}

		if (m_pFlagSymbolOffset != NULL) {
			gDLL->getFlagEntityIFace()->updateUnitInfo(m_pFlagSymbolOffset, this, true);
		}
	}
}


CvUnit* CvPlot::getCenterUnit() const {
	return m_pCenterUnit;
}


CvUnit* CvPlot::getDebugCenterUnit() const {
	CvUnit* pCenterUnit = getCenterUnit();

	if (pCenterUnit == NULL) {
		if (GC.getGameINLINE().isDebugMode()) {
			CLLNode<IDInfo>* pUnitNode = headUnitNode();
			if (pUnitNode == NULL)
				pCenterUnit = NULL;
			else
				pCenterUnit = ::getUnit(pUnitNode->m_data);
		}
	}

	return pCenterUnit;
}


void CvPlot::setCenterUnit(CvUnit* pNewValue) {
	CvUnit* pOldValue = getCenterUnit();

	if (pOldValue != pNewValue) {
		m_pCenterUnit = pNewValue;
		updateMinimapColor();

		setFlagDirty(true);

		if (getCenterUnit() != NULL) {
			getCenterUnit()->setInfoBarDirty(true);
		}
	}
}


int CvPlot::getCultureRangeCities(PlayerTypes eOwnerIndex, int iRangeIndex) const {
	FAssert(eOwnerIndex >= 0);
	FAssert(eOwnerIndex < MAX_PLAYERS);
	FAssert(iRangeIndex >= 0);
	FAssert(iRangeIndex < GC.getNumCultureLevelInfos());

	if (NULL == m_apaiCultureRangeCities) {
		return 0;
	} else if (NULL == m_apaiCultureRangeCities[eOwnerIndex]) {
		return 0;
	}

	return m_apaiCultureRangeCities[eOwnerIndex][iRangeIndex];
}


bool CvPlot::isCultureRangeCity(PlayerTypes eOwnerIndex, int iRangeIndex) const {
	return (getCultureRangeCities(eOwnerIndex, iRangeIndex) > 0);
}


void CvPlot::changeCultureRangeCities(PlayerTypes eOwnerIndex, int iRangeIndex, int iChange, bool bUpdatePlotGroups) {
	bool bOldCultureRangeCities;

	FAssert(eOwnerIndex >= 0);
	FAssert(eOwnerIndex < MAX_PLAYERS);
	FAssert(iRangeIndex >= 0);
	FAssert(iRangeIndex < GC.getNumCultureLevelInfos());

	if (0 != iChange) {
		bOldCultureRangeCities = isCultureRangeCity(eOwnerIndex, iRangeIndex);

		if (NULL == m_apaiCultureRangeCities) {
			m_apaiCultureRangeCities = new char* [MAX_PLAYERS];
			for (int iI = 0; iI < MAX_PLAYERS; ++iI) {
				m_apaiCultureRangeCities[iI] = NULL;
			}
		}

		if (NULL == m_apaiCultureRangeCities[eOwnerIndex]) {
			m_apaiCultureRangeCities[eOwnerIndex] = new char[GC.getNumCultureLevelInfos()];
			for (int iI = 0; iI < GC.getNumCultureLevelInfos(); ++iI) {
				m_apaiCultureRangeCities[eOwnerIndex][iI] = 0;
			}
		}

		m_apaiCultureRangeCities[eOwnerIndex][iRangeIndex] += iChange;

		if (bOldCultureRangeCities != isCultureRangeCity(eOwnerIndex, iRangeIndex)) {
			updateCulture(true, bUpdatePlotGroups);
		}
	}
}


int CvPlot::getInvisibleVisibilityCount(TeamTypes eTeam, InvisibleTypes eInvisible) const {
	FAssertMsg(eTeam >= 0, "eTeam is expected to be non-negative (invalid Index)");
	FAssertMsg(eTeam < MAX_TEAMS, "eTeam is expected to be within maximum bounds (invalid Index)");
	FAssertMsg(eInvisible >= 0, "eInvisible is expected to be non-negative (invalid Index)");
	FAssertMsg(eInvisible < GC.getNumInvisibleInfos(), "eInvisible is expected to be within maximum bounds (invalid Index)");

	if (NULL == m_apaiInvisibleVisibilityCount) {
		return 0;
	} else if (NULL == m_apaiInvisibleVisibilityCount[eTeam]) {
		return 0;
	}

	return m_apaiInvisibleVisibilityCount[eTeam][eInvisible];
}


bool CvPlot::isInvisibleVisible(TeamTypes eTeam, InvisibleTypes eInvisible)	const {
	return (getInvisibleVisibilityCount(eTeam, eInvisible) > 0);
}


void CvPlot::changeInvisibleVisibilityCount(TeamTypes eTeam, InvisibleTypes eInvisible, int iChange) {
	FAssertMsg(eTeam >= 0, "eTeam is expected to be non-negative (invalid Index)");
	FAssertMsg(eTeam < MAX_TEAMS, "eTeam is expected to be within maximum bounds (invalid Index)");
	FAssertMsg(eInvisible >= 0, "eInvisible is expected to be non-negative (invalid Index)");
	FAssertMsg(eInvisible < GC.getNumInvisibleInfos(), "eInvisible is expected to be within maximum bounds (invalid Index)");

	if (iChange != 0) {
		bool bOldInvisibleVisible = isInvisibleVisible(eTeam, eInvisible);

		if (NULL == m_apaiInvisibleVisibilityCount) {
			m_apaiInvisibleVisibilityCount = new short* [MAX_TEAMS];
			for (int iI = 0; iI < MAX_TEAMS; ++iI) {
				m_apaiInvisibleVisibilityCount[iI] = NULL;
			}
		}

		if (NULL == m_apaiInvisibleVisibilityCount[eTeam]) {
			m_apaiInvisibleVisibilityCount[eTeam] = new short[GC.getNumInvisibleInfos()];
			for (int iI = 0; iI < GC.getNumInvisibleInfos(); ++iI) {
				m_apaiInvisibleVisibilityCount[eTeam][iI] = 0;
			}
		}

		m_apaiInvisibleVisibilityCount[eTeam][eInvisible] += iChange;

		if (bOldInvisibleVisible != isInvisibleVisible(eTeam, eInvisible)) {
			if (eTeam == GC.getGameINLINE().getActiveTeam()) {
				updateCenterUnit();
			}
		}
	}
}


int CvPlot::getNumUnits() const {
	return m_units.getLength();
}


CvUnit* CvPlot::getUnitByIndex(int iIndex) const {
	CLLNode<IDInfo>* pUnitNode = m_units.nodeNum(iIndex);
	if (pUnitNode != NULL) {
		return ::getUnit(pUnitNode->m_data);
	} else {
		return NULL;
	}
}


void CvPlot::addUnit(CvUnit* pUnit, bool bUpdate) {
	FAssertMsg(pUnit->at(getX_INLINE(), getY_INLINE()), "pUnit is expected to be at getX_INLINE and getY_INLINE");

	CLLNode<IDInfo>* pUnitNode = headUnitNode();
	while (pUnitNode != NULL) {
		CvUnit* pLoopUnit = ::getUnit(pUnitNode->m_data);

		if (!isBeforeUnitCycle(pLoopUnit, pUnit)) {
			break;
		}

		pUnitNode = nextUnitNode(pUnitNode);
	}

	if (pUnitNode != NULL) {
		m_units.insertBefore(pUnit->getIDInfo(), pUnitNode);
	} else {
		m_units.insertAtEnd(pUnit->getIDInfo());
	}

	if (bUpdate) {
		updateCenterUnit();

		setFlagDirty(true);
	}
}


void CvPlot::removeUnit(CvUnit* pUnit, bool bUpdate) {
	CLLNode<IDInfo>* pUnitNode = headUnitNode();
	while (pUnitNode != NULL) {
		if (::getUnit(pUnitNode->m_data) == pUnit) {
			FAssertMsg(::getUnit(pUnitNode->m_data)->at(getX_INLINE(), getY_INLINE()), "The current unit instance is expected to be at getX_INLINE and getY_INLINE");
			m_units.deleteNode(pUnitNode);
			break;
		} else {
			pUnitNode = nextUnitNode(pUnitNode);
		}
	}

	if (bUpdate) {
		updateCenterUnit();

		setFlagDirty(true);
	}
}


CLLNode<IDInfo>* CvPlot::nextUnitNode(CLLNode<IDInfo>* pNode) const {
	return m_units.next(pNode);
}


CLLNode<IDInfo>* CvPlot::prevUnitNode(CLLNode<IDInfo>* pNode) const {
	return m_units.prev(pNode);
}


CLLNode<IDInfo>* CvPlot::headUnitNode() const {
	return m_units.head();
}


CLLNode<IDInfo>* CvPlot::tailUnitNode() const {
	return m_units.tail();
}


int CvPlot::getNumSymbols() const {
	return m_symbols.size();
}


CvSymbol* CvPlot::getSymbol(int iID) const {
	return m_symbols[iID];
}


CvSymbol* CvPlot::addSymbol() {
	CvSymbol* pSym = gDLL->getSymbolIFace()->createSymbol();
	m_symbols.push_back(pSym);
	return pSym;
}


void CvPlot::deleteSymbol(int iID) {
	m_symbols.erase(m_symbols.begin() + iID);
}


void CvPlot::deleteAllSymbols() {
	for (int i = 0; i < getNumSymbols(); i++) {
		gDLL->getSymbolIFace()->destroy(m_symbols[i]);
	}
	m_symbols.clear();
}

CvString CvPlot::getScriptData() const {
	return m_szScriptData;
}

void CvPlot::setScriptData(const char* szNewValue) {
	SAFE_DELETE_ARRAY(m_szScriptData);
	m_szScriptData = _strdup(szNewValue);
}

// Protected Functions...

void CvPlot::doFeature() {
	PROFILE("CvPlot::doFeature()")

		if (getFeatureType() != NO_FEATURE) {
			int iProbability = GC.getFeatureInfo(getFeatureType()).getDisappearanceProbability();

			if (iProbability > 0) {
				int iOdds = (10000 * GC.getGameSpeedInfo(GC.getGameINLINE().getGameSpeedType()).getVictoryDelayPercent()) / 100;
				if (GC.getGameINLINE().getSorenRandNum(iOdds, "Feature Disappearance") < iProbability) {
					setFeatureType(NO_FEATURE);
				}
			}
		} else {
			if (!isUnit()) {
				if (getImprovementType() == NO_IMPROVEMENT) {
					for (int iI = 0; iI < GC.getNumFeatureInfos(); ++iI) {
						if (canHaveFeature((FeatureTypes)iI)) {
							if ((getBonusType() == NO_BONUS) || (GC.getBonusInfo(getBonusType()).isFeature(iI))) {
								int iProbability = 0;

								for (int iJ = 0; iJ < NUM_CARDINALDIRECTION_TYPES; iJ++) {
									CvPlot* pLoopPlot = plotCardinalDirection(getX_INLINE(), getY_INLINE(), ((CardinalDirectionTypes)iJ));

									if (pLoopPlot != NULL) {
										if (pLoopPlot->getFeatureType() == ((FeatureTypes)iI)) {
											if (pLoopPlot->getImprovementType() == NO_IMPROVEMENT) {
												iProbability += GC.getFeatureInfo((FeatureTypes)iI).getGrowthProbability();
											} else {
												iProbability += GC.getImprovementInfo(pLoopPlot->getImprovementType()).getFeatureGrowthProbability();
											}
										}
									}
								}

								iProbability *= std::max(0, (GC.getFEATURE_GROWTH_MODIFIER() + 100));
								iProbability /= 100;

								if (isRoute()) {
									iProbability *= std::max(0, (GC.getROUTE_FEATURE_GROWTH_MODIFIER() + 100));
									iProbability /= 100;
								}

								if (iProbability > 0) {
									int iOdds = (10000 * GC.getGameSpeedInfo(GC.getGameINLINE().getGameSpeedType()).getVictoryDelayPercent()) / 100;
									if (GC.getGameINLINE().getSorenRandNum(iOdds, "Feature Growth") < iProbability) {
										setFeatureType((FeatureTypes)iI);

										CvCity* pCity = GC.getMapINLINE().findCity(getX_INLINE(), getY_INLINE(), getOwnerINLINE(), NO_TEAM, false);

										if (pCity != NULL && isVisible(GET_PLAYER(pCity->getOwnerINLINE()).getTeam(), false)) // K-Mod
										{
											// Tell the owner of this city.
											CvWString szBuffer = gDLL->getText("TXT_KEY_MISC_FEATURE_GROWN_NEAR_CITY", GC.getFeatureInfo((FeatureTypes)iI).getTextKeyWide(), pCity->getNameKey());
											gDLL->getInterfaceIFace()->addHumanMessage(pCity->getOwnerINLINE(), false, GC.getEVENT_MESSAGE_TIME(), szBuffer, "AS2D_FEATUREGROWTH", MESSAGE_TYPE_INFO, GC.getFeatureInfo((FeatureTypes)iI).getButton(), (ColorTypes)GC.getInfoTypeForString("COLOR_WHITE"), getX_INLINE(), getY_INLINE(), true, true);
										}
										break;
									}
								}
							}
						}
					}
				}
			}
		}
}


void CvPlot::doCulture() {
	PROFILE("CvPlot::doCulture()")

		CvCity* pCity = getPlotCity();
	if (pCity != NULL) {
		PlayerTypes eCulturalOwner = calculateCulturalOwner();

		if (eCulturalOwner != NO_PLAYER) {
			if (GET_PLAYER(eCulturalOwner).getTeam() != getTeam()) {
				if (!(pCity->isOccupation())) {
					// K-Mod Introduced gamespeed scaling to the first revolt test changed the second revolt test so that small garrisons have a relatively bigger effect
					//  (but I've also changed cultureStrength() so that it can be asymptotically large)
					int iLeftOdds = GC.getGameSpeedInfo(GC.getGameINLINE().getGameSpeedType()).getVictoryDelayPercent();
					if (GC.getGameINLINE().getSorenRandNum(iLeftOdds, "Revolt #1") < pCity->getRevoltTestProbability()) {
						int iCityStrength = pCity->cultureStrength(eCulturalOwner);
						int iGarrison = pCity->cultureGarrison(eCulturalOwner);

						// Note: Pr( [0, S+2G] > 3G) = (S-G)/(S+2*G).   c.f. (S-G)/S
						if ((GC.getGameINLINE().getSorenRandNum(iCityStrength + 2 * iGarrison, "Revolt #2") > 3 * iGarrison) || pCity->isBarbarian()) {
							CLinkList<IDInfo> oldUnits;

							CLLNode<IDInfo>* pUnitNode = headUnitNode();

							while (pUnitNode != NULL) {
								oldUnits.insertAtEnd(pUnitNode->m_data);
								pUnitNode = nextUnitNode(pUnitNode);
							}

							pUnitNode = oldUnits.head();

							while (pUnitNode != NULL) {
								CvUnit* pLoopUnit = ::getUnit(pUnitNode->m_data);
								pUnitNode = nextUnitNode(pUnitNode);

								if (pLoopUnit) {
									if (pLoopUnit->isBarbarian()) {
										pLoopUnit->kill(false, eCulturalOwner);
									} else if (pLoopUnit->canDefend()) {
										pLoopUnit->changeDamage((pLoopUnit->currHitPoints() / 2), eCulturalOwner);
									}
								}

							}

							if (pCity->canCultureFlip(eCulturalOwner)) // K-Mod
							{
								if (GC.getGameINLINE().isOption(GAMEOPTION_ONE_CITY_CHALLENGE) && GET_PLAYER(eCulturalOwner).isHuman()) {
									pCity->kill(true);
								} else {
									setOwner(eCulturalOwner, true, true); // will delete pCity
								}
								pCity = NULL;
							} else {
								pCity->changeNumRevolts(eCulturalOwner, 1);
								// K-Mod - Changed number of revolt turns to not depend on iCityStrength, because iCityStrength can be huge.
								pCity->changeOccupationTimer(GC.getDefineINT("BASE_REVOLT_OCCUPATION_TURNS") + std::min(3, pCity->getNumRevolts(eCulturalOwner) - 1));
								// XXX announce for all seen cities?
								CvWString szBuffer = gDLL->getText("TXT_KEY_MISC_REVOLT_IN_CITY", GET_PLAYER(eCulturalOwner).getCivilizationAdjective(), pCity->getNameKey());
								gDLL->getInterfaceIFace()->addHumanMessage(getOwnerINLINE(), false, GC.getEVENT_MESSAGE_TIME(), szBuffer, "AS2D_CITY_REVOLT", MESSAGE_TYPE_MINOR_EVENT, ARTFILEMGR.getInterfaceArtInfo("INTERFACE_RESISTANCE")->getPath(), (ColorTypes)GC.getInfoTypeForString("COLOR_RED"), getX_INLINE(), getY_INLINE(), true, true);
								gDLL->getInterfaceIFace()->addHumanMessage(eCulturalOwner, false, GC.getEVENT_MESSAGE_TIME(), szBuffer, "AS2D_CITY_REVOLT", MESSAGE_TYPE_MINOR_EVENT, ARTFILEMGR.getInterfaceArtInfo("INTERFACE_RESISTANCE")->getPath(), (ColorTypes)GC.getInfoTypeForString("COLOR_GREEN"), getX_INLINE(), getY_INLINE(), true, true);
							}
						}
					}
				}
			}
		}
	} else {
		// No point in trying to change the culture if there isn't any
		PlayerTypes eCulturalOwner = calculateCulturalOwner();
		if (eCulturalOwner != NO_PLAYER && GC.getIDW_STATIONED_INFLUENCE_ENABLED()) {
			std::map<PlayerTypes, std::vector<CvUnit*> > mAllPlayerUnits;
			int iTotalEnemyUnits = 0;

			// Iterate through the units on the plot storing for each player
			CLLNode<IDInfo>* pUnitNode = headUnitNode();
			while (pUnitNode != NULL) {
				CvUnit* pLoopUnit = ::getUnit(pUnitNode->m_data);
				if (!pLoopUnit->isAnimal()) {
					// Append the unit to the list of units for that player
					mAllPlayerUnits[pLoopUnit->getOwner()].push_back(pLoopUnit);

					// If this is an enemy increment the total count
					if (pLoopUnit->isEnemy(GET_PLAYER(eCulturalOwner).getTeam(), this))
						iTotalEnemyUnits++;
				}
				pUnitNode = nextUnitNode(pUnitNode);
			}

			// Now we have the list of all the players and their units on the plot
			// iterate through the list and do any appropriate culture exchange
			if (!mAllPlayerUnits.empty()) {
				for (std::map<PlayerTypes, std::vector<CvUnit*> >::const_iterator it = mAllPlayerUnits.begin(); it != mAllPlayerUnits.end(); ++it) {
					// Get the info from the map
					PlayerTypes ePlayer = it->first;
					std::vector<CvUnit*> vPlayerUnits = it->second;

					// Check we have some units, otherwise skip to the next player
					if (vPlayerUnits.empty())
						continue;

					// Get the first unit from the vector of units so we can use it to test for enemy status
					CvUnit* pUnit = vPlayerUnits[0];
					if (pUnit == NULL)
						continue;

					// Check if this player is the plot owner
					if (ePlayer == eCulturalOwner) {
						std::map<PlayerTypes, int> mEnemyCultures;
						int iTotalCulture = 0;

						// Loop through the players finding the culture owners
						for (PlayerTypes ePlayer = (PlayerTypes)0; ePlayer < MAX_PLAYERS; ePlayer = (PlayerTypes)(ePlayer + 1)) // Include barbarians
						{
							int iPlayerCulture = 0;
							if (GET_PLAYER(ePlayer).isAlive()) {
								iPlayerCulture = getCulture(ePlayer);
								iTotalCulture += iPlayerCulture;
								if (iPlayerCulture > 0 && pUnit->isEnemy(GET_PLAYER(ePlayer).getTeam(), this)) {
									mEnemyCultures[ePlayer] = iPlayerCulture;
								}
							}
						}

						// Loop through the enemies with culture on the plot and do the exchange
						for (std::map<PlayerTypes, int>::const_iterator it = mEnemyCultures.begin(); it != mEnemyCultures.end(); ++it) {
							float fInfluence = GC.getIDW_BASE_STATIONED_OWNER_INFLUENCE() * it->second / iTotalCulture;
							pUnit->doInfluenceCulture(fInfluence, it->first);
						}
					}
					// Now deal with enemies on the plot
					else if (pUnit->isEnemy(GET_PLAYER(eCulturalOwner).getTeam(), this)) {
						float fInfluence = GC.getIDW_BASE_STATIONED_ENEMY_INFLUENCE() * vPlayerUnits.size() / iTotalEnemyUnits;
						pUnit->doInfluenceCulture(fInfluence, eCulturalOwner);
					}
				}
			}
		}
	}

	updateCulture(true, true);
}


void CvPlot::processArea(CvArea* pArea, int iChange) {
	pArea->changeNumTiles(iChange);

	if (isOwned()) {
		pArea->changeNumOwnedTiles(iChange);
	}

	if (isNOfRiver()) {
		pArea->changeNumRiverEdges(iChange);
	}
	if (isWOfRiver()) {
		pArea->changeNumRiverEdges(iChange);
	}

	if (getBonusType() != NO_BONUS) {
		pArea->changeNumBonuses(getBonusType(), iChange);
	}

	if (getImprovementType() != NO_IMPROVEMENT) {
		pArea->changeNumImprovements(getImprovementType(), iChange);
	}

	for (int iI = 0; iI < MAX_PLAYERS; ++iI) {
		if (GET_PLAYER((PlayerTypes)iI).getStartingPlot() == this) {
			pArea->changeNumStartingPlots(iChange);
		}

		pArea->changePower(((PlayerTypes)iI), (getUnitPower((PlayerTypes)iI) * iChange));

		pArea->changeUnitsPerPlayer(((PlayerTypes)iI), (plotCount(PUF_isPlayer, iI) * iChange));
		pArea->changeAnimalsPerPlayer(((PlayerTypes)iI), (plotCount(PUF_isAnimal, -1, -1, ((PlayerTypes)iI)) * iChange));

		for (int iJ = 0; iJ < NUM_UNITAI_TYPES; iJ++) {
			pArea->changeNumAIUnits(((PlayerTypes)iI), ((UnitAITypes)iJ), (plotCount(PUF_isUnitAIType, iJ, -1, ((PlayerTypes)iI)) * iChange));
		}
	}

	for (int iI = 0; iI < MAX_TEAMS; ++iI) {
		if (isRevealed(((TeamTypes)iI), false)) {
			pArea->changeNumRevealedTiles(((TeamTypes)iI), iChange);
		}
	}

	CvCity* pCity = getPlotCity();

	if (pCity != NULL) {
		// XXX make sure all of this (esp. the changePower()) syncs up...
		pArea->changePower(pCity->getOwnerINLINE(), (getPopulationPower(pCity->getPopulation()) * iChange));

		pArea->changeCitiesPerPlayer(pCity->getOwnerINLINE(), iChange);
		pArea->changePopulationPerPlayer(pCity->getOwnerINLINE(), (pCity->getPopulation() * iChange));

		for (int iI = 0; iI < GC.getNumBuildingInfos(); ++iI) {
			if (pCity->getNumActiveBuilding((BuildingTypes)iI) > 0) {
				pArea->changePower(pCity->getOwnerINLINE(), (GC.getBuildingInfo((BuildingTypes)iI).getPowerValue() * iChange * pCity->getNumActiveBuilding((BuildingTypes)iI)));

				if (GC.getBuildingInfo((BuildingTypes)iI).getAreaHealth() > 0) {
					pArea->changeBuildingGoodHealth(pCity->getOwnerINLINE(), (GC.getBuildingInfo((BuildingTypes)iI).getAreaHealth() * iChange * pCity->getNumActiveBuilding((BuildingTypes)iI)));
				} else {
					pArea->changeBuildingBadHealth(pCity->getOwnerINLINE(), (GC.getBuildingInfo((BuildingTypes)iI).getAreaHealth() * iChange * pCity->getNumActiveBuilding((BuildingTypes)iI)));
				}
				pArea->changeBuildingHappiness(pCity->getOwnerINLINE(), (GC.getBuildingInfo((BuildingTypes)iI).getAreaHappiness() * iChange * pCity->getNumActiveBuilding((BuildingTypes)iI)));
				pArea->changeFreeSpecialist(pCity->getOwnerINLINE(), (GC.getBuildingInfo((BuildingTypes)iI).getAreaFreeSpecialist() * iChange * pCity->getNumActiveBuilding((BuildingTypes)iI)));

				pArea->changeCleanPowerCount(pCity->getTeam(), ((GC.getBuildingInfo((BuildingTypes)iI).isAreaCleanPower()) ? iChange * pCity->getNumActiveBuilding((BuildingTypes)iI) : 0));

				pArea->changeBorderObstacleCount(pCity->getTeam(), ((GC.getBuildingInfo((BuildingTypes)iI).isAreaBorderObstacle()) ? iChange * pCity->getNumActiveBuilding((BuildingTypes)iI) : 0));

				for (int iJ = 0; iJ < NUM_YIELD_TYPES; iJ++) {
					pArea->changeYieldRateModifier(pCity->getOwnerINLINE(), ((YieldTypes)iJ), (GC.getBuildingInfo((BuildingTypes)iI).getAreaYieldModifier(iJ) * iChange * pCity->getNumActiveBuilding((BuildingTypes)iI)));
				}
			}
		}

		for (int iI = 0; iI < NUM_UNITAI_TYPES; ++iI) {
			pArea->changeNumTrainAIUnits(pCity->getOwnerINLINE(), ((UnitAITypes)iI), (pCity->getNumTrainUnitAI((UnitAITypes)iI) * iChange));
		}

		for (int iI = 0; iI < MAX_PLAYERS; ++iI) {
			if (pArea->getTargetCity((PlayerTypes)iI) == pCity) {
				pArea->setTargetCity(((PlayerTypes)iI), NULL);
			}
		}
	}
}


ColorTypes CvPlot::plotMinimapColor() {
	if (GC.getGameINLINE().getActivePlayer() != NO_PLAYER) {
		CvCity* pCity = getPlotCity();

		if ((pCity != NULL) && pCity->isRevealed(GC.getGameINLINE().getActiveTeam(), true)) {
			return (ColorTypes)GC.getInfoTypeForString("COLOR_WHITE");
		}

		if (isActiveVisible(true)) {
			CvUnit* pCenterUnit = getDebugCenterUnit();

			if (pCenterUnit != NULL) {
				return ((ColorTypes)(GC.getPlayerColorInfo(GET_PLAYER(pCenterUnit->getVisualOwner()).getPlayerColor()).getColorTypePrimary()));
			}
		}

		if ((getRevealedOwner(GC.getGameINLINE().getActiveTeam(), true) != NO_PLAYER) && !isRevealedBarbarian()) {
			return ((ColorTypes)(GC.getPlayerColorInfo(GET_PLAYER(getRevealedOwner(GC.getGameINLINE().getActiveTeam(), true)).getPlayerColor()).getColorTypePrimary()));
		}
	}

	return (ColorTypes)GC.getInfoTypeForString("COLOR_CLEAR");
}

//
// read object from a stream
// used during load
//
void CvPlot::read(FDataStreamBase* pStream) {
	// Init saved data
	reset();

	uint uiFlag = 0;
	pStream->Read(&uiFlag);	// flags for expansion

	pStream->Read(&m_iX);
	pStream->Read(&m_iY);
	pStream->Read(&m_iArea);
	// m_pPlotArea not saved
	pStream->Read(&m_iFeatureVariety);
	pStream->Read(&m_iOwnershipDuration);
	pStream->Read(&m_iImprovementDuration);
	pStream->Read(&m_iUpgradeProgress);
	pStream->Read(&m_iForceUnownedTimer);
	pStream->Read(&m_iCityRadiusCount);
	pStream->Read(&m_iRiverID);
	pStream->Read(&m_iMinOriginalStartDist);
	pStream->Read(&m_iReconCount);
	pStream->Read(&m_iRiverCrossingCount);

	bool bVal;
	pStream->Read(&bVal);
	m_bStartingPlot = bVal;
	pStream->Read(&bVal);
	m_bHills = bVal;
	pStream->Read(&bVal);
	m_bPeaks = bVal;
	pStream->Read(&bVal);
	m_bNOfRiver = bVal;
	pStream->Read(&bVal);
	m_bWOfRiver = bVal;
	pStream->Read(&bVal);
	m_bIrrigated = bVal;
	pStream->Read(&bVal);
	m_bPotentialCityWork = bVal;
	// m_bShowCitySymbols not saved
	// m_bFlagDirty not saved
	// m_bPlotLayoutDirty not saved
	// m_bLayoutStateWorked not saved

	pStream->Read(&m_eOwner);
	pStream->Read(&m_ePlotType);
	pStream->Read(&m_eTerrainType);
	pStream->Read(&m_eFeatureType);
	pStream->Read(&m_eBonusType);
	pStream->Read(&m_eImprovementType);
	pStream->Read(&m_eRouteType);
	pStream->Read(&m_eRiverNSDirection);
	pStream->Read(&m_eRiverWEDirection);

	pStream->Read((int*)&m_plotCity.eOwner);
	pStream->Read(&m_plotCity.iID);
	pStream->Read((int*)&m_workingCity.eOwner);
	pStream->Read(&m_workingCity.iID);
	pStream->Read((int*)&m_workingCityOverride.eOwner);
	pStream->Read(&m_workingCityOverride.iID);

	pStream->Read(NUM_YIELD_TYPES, m_aiYield);

	// K-Mod. I've changed the purpose of invalidateBorderDangerCache. It is no longer appropriate for this.
	m_iActivePlayerSafeRangeCache = -1;
	for (int i = 0; i < MAX_TEAMS; i++) {
		m_abBorderDangerCache[i] = false;
	}

	char cCount;
	SAFE_DELETE_ARRAY(m_aiCulture);
	pStream->Read(&cCount);
	if (cCount > 0) {
		m_aiCulture = new int[cCount];
		pStream->Read(cCount, m_aiCulture);
	}

	SAFE_DELETE_ARRAY(m_aiFoundValue);
	pStream->Read(&cCount);
	if (cCount > 0) {
		m_aiFoundValue = new short[cCount];
		pStream->Read(cCount, m_aiFoundValue);
	}

	SAFE_DELETE_ARRAY(m_aiPlayerCityRadiusCount);
	pStream->Read(&cCount);
	if (cCount > 0) {
		m_aiPlayerCityRadiusCount = new char[cCount];
		pStream->Read(cCount, m_aiPlayerCityRadiusCount);
	}

	SAFE_DELETE_ARRAY(m_aiPlotGroup);
	pStream->Read(&cCount);
	if (cCount > 0) {
		m_aiPlotGroup = new int[cCount];
		pStream->Read(cCount, m_aiPlotGroup);
	}

	SAFE_DELETE_ARRAY(m_aiVisibilityCount);
	pStream->Read(&cCount);
	if (cCount > 0) {
		m_aiVisibilityCount = new short[cCount];
		pStream->Read(cCount, m_aiVisibilityCount);
	}

	SAFE_DELETE_ARRAY(m_aiStolenVisibilityCount);
	pStream->Read(&cCount);
	if (cCount > 0) {
		m_aiStolenVisibilityCount = new short[cCount];
		pStream->Read(cCount, m_aiStolenVisibilityCount);
	}

	SAFE_DELETE_ARRAY(m_aiBlockadedCount);
	pStream->Read(&cCount);
	if (cCount > 0) {
		m_aiBlockadedCount = new short[cCount];
		pStream->Read(cCount, m_aiBlockadedCount);
	}

	SAFE_DELETE_ARRAY(m_aiRevealedOwner);
	pStream->Read(&cCount);
	if (cCount > 0) {
		m_aiRevealedOwner = new char[cCount];
		pStream->Read(cCount, m_aiRevealedOwner);
	}

	SAFE_DELETE_ARRAY(m_abRiverCrossing);
	pStream->Read(&cCount);
	if (cCount > 0) {
		m_abRiverCrossing = new bool[cCount];
		pStream->Read(cCount, m_abRiverCrossing);
	}

	SAFE_DELETE_ARRAY(m_abRevealed);
	pStream->Read(&cCount);
	if (cCount > 0) {
		m_abRevealed = new bool[cCount];
		pStream->Read(cCount, m_abRevealed);
	}

	SAFE_DELETE_ARRAY(m_aeRevealedImprovementType);
	pStream->Read(&cCount);
	if (cCount > 0) {
		m_aeRevealedImprovementType = new short[cCount];
		pStream->Read(cCount, m_aeRevealedImprovementType);
	}

	SAFE_DELETE_ARRAY(m_aeRevealedRouteType);
	pStream->Read(&cCount);
	if (cCount > 0) {
		m_aeRevealedRouteType = new short[cCount];
		pStream->Read(cCount, m_aeRevealedRouteType);
	}

	m_szScriptData = pStream->ReadString();

	int iCount;
	SAFE_DELETE_ARRAY(m_paiBuildProgress);
	pStream->Read(&iCount);
	if (iCount > 0) {
		m_paiBuildProgress = new short[iCount];
		pStream->Read(iCount, m_paiBuildProgress);
	}

	if (NULL != m_apaiCultureRangeCities) {
		for (int iI = 0; iI < MAX_PLAYERS; ++iI) {
			SAFE_DELETE_ARRAY(m_apaiCultureRangeCities[iI]);
		}
		SAFE_DELETE_ARRAY(m_apaiCultureRangeCities);
	}
	pStream->Read(&cCount);
	if (cCount > 0) {
		m_apaiCultureRangeCities = new char* [cCount];
		for (int iI = 0; iI < cCount; ++iI) {
			pStream->Read(&iCount);
			if (iCount > 0) {
				m_apaiCultureRangeCities[iI] = new char[iCount];
				pStream->Read(iCount, m_apaiCultureRangeCities[iI]);
			} else {
				m_apaiCultureRangeCities[iI] = NULL;
			}
		}
	}

	if (NULL != m_apaiInvisibleVisibilityCount) {
		for (int iI = 0; iI < MAX_TEAMS; ++iI) {
			SAFE_DELETE_ARRAY(m_apaiInvisibleVisibilityCount[iI]);
		}
		SAFE_DELETE_ARRAY(m_apaiInvisibleVisibilityCount);
	}
	pStream->Read(&cCount);
	if (cCount > 0) {
		m_apaiInvisibleVisibilityCount = new short* [cCount];
		for (int iI = 0; iI < cCount; ++iI) {
			pStream->Read(&iCount);
			if (iCount > 0) {
				m_apaiInvisibleVisibilityCount[iI] = new short[iCount];
				pStream->Read(iCount, m_apaiInvisibleVisibilityCount[iI]);
			} else {
				m_apaiInvisibleVisibilityCount[iI] = NULL;
			}
		}
	}

	m_units.Read(pStream);
}

//
// write object to a stream
// used during save
//
void CvPlot::write(FDataStreamBase* pStream) {
	uint uiFlag = 0;
	pStream->Write(uiFlag);		// flag for expansion

	pStream->Write(m_iX);
	pStream->Write(m_iY);
	pStream->Write(m_iArea);
	// m_pPlotArea not saved
	pStream->Write(m_iFeatureVariety);
	pStream->Write(m_iOwnershipDuration);
	pStream->Write(m_iImprovementDuration);
	pStream->Write(m_iUpgradeProgress);
	pStream->Write(m_iForceUnownedTimer);
	pStream->Write(m_iCityRadiusCount);
	pStream->Write(m_iRiverID);
	pStream->Write(m_iMinOriginalStartDist);
	pStream->Write(m_iReconCount);
	pStream->Write(m_iRiverCrossingCount);

	pStream->Write(m_bStartingPlot);
	pStream->Write(m_bHills);
	pStream->Write(m_bPeaks);
	pStream->Write(m_bNOfRiver);
	pStream->Write(m_bWOfRiver);
	pStream->Write(m_bIrrigated);
	pStream->Write(m_bPotentialCityWork);
	// m_bShowCitySymbols not saved
	// m_bFlagDirty not saved
	// m_bPlotLayoutDirty not saved
	// m_bLayoutStateWorked not saved

	pStream->Write(m_eOwner);
	pStream->Write(m_ePlotType);
	pStream->Write(m_eTerrainType);
	pStream->Write(m_eFeatureType);
	pStream->Write(m_eBonusType);
	pStream->Write(m_eImprovementType);
	pStream->Write(m_eRouteType);
	pStream->Write(m_eRiverNSDirection);
	pStream->Write(m_eRiverWEDirection);

	pStream->Write(m_plotCity.eOwner);
	pStream->Write(m_plotCity.iID);
	pStream->Write(m_workingCity.eOwner);
	pStream->Write(m_workingCity.iID);
	pStream->Write(m_workingCityOverride.eOwner);
	pStream->Write(m_workingCityOverride.iID);

	pStream->Write(NUM_YIELD_TYPES, m_aiYield);

	if (NULL == m_aiCulture) {
		pStream->Write((char)0);
	} else {
		pStream->Write((char)MAX_PLAYERS);
		pStream->Write(MAX_PLAYERS, m_aiCulture);
	}

	if (NULL == m_aiFoundValue) {
		pStream->Write((char)0);
	} else {
		pStream->Write((char)MAX_PLAYERS);
		pStream->Write(MAX_PLAYERS, m_aiFoundValue);
	}

	if (NULL == m_aiPlayerCityRadiusCount) {
		pStream->Write((char)0);
	} else {
		pStream->Write((char)MAX_PLAYERS);
		pStream->Write(MAX_PLAYERS, m_aiPlayerCityRadiusCount);
	}

	if (NULL == m_aiPlotGroup) {
		pStream->Write((char)0);
	} else {
		pStream->Write((char)MAX_PLAYERS);
		pStream->Write(MAX_PLAYERS, m_aiPlotGroup);
	}

	if (NULL == m_aiVisibilityCount) {
		pStream->Write((char)0);
	} else {
		pStream->Write((char)MAX_TEAMS);
		pStream->Write(MAX_TEAMS, m_aiVisibilityCount);
	}

	if (NULL == m_aiStolenVisibilityCount) {
		pStream->Write((char)0);
	} else {
		pStream->Write((char)MAX_TEAMS);
		pStream->Write(MAX_TEAMS, m_aiStolenVisibilityCount);
	}

	if (NULL == m_aiBlockadedCount) {
		pStream->Write((char)0);
	} else {
		pStream->Write((char)MAX_TEAMS);
		pStream->Write(MAX_TEAMS, m_aiBlockadedCount);
	}

	if (NULL == m_aiRevealedOwner) {
		pStream->Write((char)0);
	} else {
		pStream->Write((char)MAX_TEAMS);
		pStream->Write(MAX_TEAMS, m_aiRevealedOwner);
	}

	if (NULL == m_abRiverCrossing) {
		pStream->Write((char)0);
	} else {
		pStream->Write((char)NUM_DIRECTION_TYPES);
		pStream->Write(NUM_DIRECTION_TYPES, m_abRiverCrossing);
	}

	if (NULL == m_abRevealed) {
		pStream->Write((char)0);
	} else {
		pStream->Write((char)MAX_TEAMS);
		pStream->Write(MAX_TEAMS, m_abRevealed);
	}

	if (NULL == m_aeRevealedImprovementType) {
		pStream->Write((char)0);
	} else {
		pStream->Write((char)MAX_TEAMS);
		pStream->Write(MAX_TEAMS, m_aeRevealedImprovementType);
	}

	if (NULL == m_aeRevealedRouteType) {
		pStream->Write((char)0);
	} else {
		pStream->Write((char)MAX_TEAMS);
		pStream->Write(MAX_TEAMS, m_aeRevealedRouteType);
	}

	pStream->WriteString(m_szScriptData);

	if (NULL == m_paiBuildProgress) {
		pStream->Write((int)0);
	} else {
		pStream->Write((int)GC.getNumBuildInfos());
		pStream->Write(GC.getNumBuildInfos(), m_paiBuildProgress);
	}

	if (NULL == m_apaiCultureRangeCities) {
		pStream->Write((char)0);
	} else {
		pStream->Write((char)MAX_PLAYERS);
		for (uint iI = 0; iI < MAX_PLAYERS; ++iI) {
			if (NULL == m_apaiCultureRangeCities[iI]) {
				pStream->Write((int)0);
			} else {
				pStream->Write((int)GC.getNumCultureLevelInfos());
				pStream->Write(GC.getNumCultureLevelInfos(), m_apaiCultureRangeCities[iI]);
			}
		}
	}

	if (NULL == m_apaiInvisibleVisibilityCount) {
		pStream->Write((char)0);
	} else {
		pStream->Write((char)MAX_TEAMS);
		for (uint iI = 0; iI < MAX_TEAMS; ++iI) {
			if (NULL == m_apaiInvisibleVisibilityCount[iI]) {
				pStream->Write((int)0);
			} else {
				pStream->Write((int)GC.getNumInvisibleInfos());
				pStream->Write(GC.getNumInvisibleInfos(), m_apaiInvisibleVisibilityCount[iI]);
			}
		}
	}

	m_units.Write(pStream);
}

void CvPlot::setLayoutDirty(bool bDirty) {
	if (!GC.IsGraphicsInitialized()) {
		return;
	}

	if (isLayoutDirty() != bDirty) {
		m_bPlotLayoutDirty = bDirty;

		if (isLayoutDirty() && (m_pPlotBuilder == NULL)) {
			if (!updatePlotBuilder()) {
				m_bPlotLayoutDirty = false;
			}
		}
	}
}

bool CvPlot::updatePlotBuilder() {
	if (GC.IsGraphicsInitialized() && shouldUsePlotBuilder()) {
		if (m_pPlotBuilder == NULL) // we need a plot builder... but it doesn't exist
		{
			m_pPlotBuilder = gDLL->getPlotBuilderIFace()->create();
			gDLL->getPlotBuilderIFace()->init(m_pPlotBuilder, this);
		}

		return true;
	}

	return false;
}

bool CvPlot::isLayoutDirty() const {
	return m_bPlotLayoutDirty;
}

bool CvPlot::isLayoutStateDifferent() const {
	bool bSame = (m_bLayoutStateWorked == isBeingWorked());

	// done
	return !bSame;
}

void CvPlot::setLayoutStateToCurrent() {
	m_bLayoutStateWorked = isBeingWorked();
}

//------------------------------------------------------------------------------------------------

void CvPlot::getVisibleImprovementState(ImprovementTypes& eType, bool& bWorked) {
	eType = NO_IMPROVEMENT;
	bWorked = false;

	if (GC.getGameINLINE().getActiveTeam() == NO_TEAM) {
		return;
	}

	eType = getRevealedImprovementType(GC.getGameINLINE().getActiveTeam(), true);

	if (eType == NO_IMPROVEMENT) {
		if (isActiveVisible(true)) {
			if (isBeingWorked() && !isCity()) {
				if (isWater()) {
					eType = ((ImprovementTypes)(GC.getDefineINT("WATER_IMPROVEMENT")));
				} else {
					eType = ((ImprovementTypes)(GC.getDefineINT("LAND_IMPROVEMENT")));
				}
			}
		}
	}

	// worked state
	if (isActiveVisible(false) && isBeingWorked()) {
		bWorked = true;
	}
}

void CvPlot::getVisibleBonusState(BonusTypes& eType, bool& bImproved, bool& bWorked) {
	eType = NO_BONUS;
	bImproved = false;
	bWorked = false;

	if (GC.getGameINLINE().getActiveTeam() == NO_TEAM) {
		return;
	}

	if (GC.getGameINLINE().isDebugMode()) {
		eType = getBonusType();
	} else if (isRevealed(GC.getGameINLINE().getActiveTeam(), false)) {
		eType = getBonusType(GC.getGameINLINE().getActiveTeam());
	}

	// improved and worked states ...
	if (eType != NO_BONUS) {
		ImprovementTypes eRevealedImprovement = getRevealedImprovementType(GC.getGameINLINE().getActiveTeam(), true);

		if ((eRevealedImprovement != NO_IMPROVEMENT) && GC.getImprovementInfo(eRevealedImprovement).isImprovementBonusTrade(eType)) {
			bImproved = true;
			bWorked = isBeingWorked();
		}
	}
}

bool CvPlot::shouldUsePlotBuilder() {
	bool bBonusImproved; bool bBonusWorked; bool bImprovementWorked;
	BonusTypes eBonusType;
	ImprovementTypes eImprovementType;
	getVisibleBonusState(eBonusType, bBonusImproved, bBonusWorked);
	getVisibleImprovementState(eImprovementType, bImprovementWorked);
	if (eBonusType != NO_BONUS || eImprovementType != NO_IMPROVEMENT) {
		return true;
	}
	return false;
}

int CvPlot::getYieldWithBuild(BuildTypes eBuild, YieldTypes eYield, bool bWithUpgrade) const {
	int iYield = 0;

	bool bIgnoreFeature = false;
	if (getFeatureType() != NO_FEATURE) {
		if (GC.getBuildInfo(eBuild).isFeatureRemove(getFeatureType())) {
			bIgnoreFeature = true;
		}
	}

	iYield += calculateNatureYield(eYield, getTeam(), bIgnoreFeature);

	ImprovementTypes eImprovement = (ImprovementTypes)GC.getBuildInfo(eBuild).getImprovement();
	// K-Mod. if the build doesn't have its own improvement - use the existing one!
	if (eImprovement == NO_IMPROVEMENT) {
		eImprovement = getImprovementType();
		// note: unfortunately this won't account for the fact that some builds will destroy the existing improvement without creating a new one.
		// eg. chopping a forest which has a lumbermill. I'm sorry about that. I may correct it later.
	}

	if (eImprovement != NO_IMPROVEMENT) {
		if (bWithUpgrade) {
			// K-Mod. Stuff that. Just use the final improvement.
			ImprovementTypes eFinalImprovement = finalImprovementUpgrade(eImprovement);
			if (eFinalImprovement != NO_IMPROVEMENT)
				eImprovement = eFinalImprovement;
		}

		iYield += calculateImprovementYieldChange(eImprovement, eYield, getOwnerINLINE(), false);
	}

	RouteTypes eRoute = (RouteTypes)GC.getBuildInfo(eBuild).getRoute();
	if (eRoute != NO_ROUTE) {
		if (eImprovement != NO_IMPROVEMENT) {
			for (int iI = 0; iI < NUM_YIELD_TYPES; iI++) {
				iYield += GC.getImprovementInfo(eImprovement).getRouteYieldChanges(eRoute, iI);
				if (getRouteType() != NO_ROUTE) {
					iYield -= GC.getImprovementInfo(eImprovement).getRouteYieldChanges(getRouteType(), iI);
				}
			}
		}
	}

	// K-Mod. Count the 'extra yield' for financial civs. (Don't bother with golden-age bonuses.)
	int iThreshold = GET_PLAYER(getOwnerINLINE()).getExtraYieldThreshold(eYield);
	if (iThreshold > 0 && iYield >= iThreshold)
		iYield += GC.getEXTRA_YIELD();

	return std::max(0, iYield); // K-Mod - so that it matches calculateYield()
}

bool CvPlot::canTrigger(EventTriggerTypes eTrigger, PlayerTypes ePlayer) const {
	FAssert(::isPlotEventTrigger(eTrigger));

	const CvEventTriggerInfo& kTrigger = GC.getEventTriggerInfo(eTrigger);

	if (kTrigger.isOwnPlot() && getOwnerINLINE() != ePlayer) {
		return false;
	}

	if (kTrigger.getPlotType() != NO_PLOT) {
		if (getPlotType() != kTrigger.getPlotType()) {
			return false;
		}
	}

	if (kTrigger.getNumFeaturesRequired() > 0) {
		bool bFoundValid = false;

		for (int i = 0; i < kTrigger.getNumFeaturesRequired(); ++i) {
			if (kTrigger.getFeatureRequired(i) == getFeatureType()) {
				bFoundValid = true;
				break;
			}
		}

		if (!bFoundValid) {
			return false;
		}
	}

	if (kTrigger.getNumTerrainsRequired() > 0) {
		bool bFoundValid = false;

		for (int i = 0; i < kTrigger.getNumTerrainsRequired(); ++i) {
			if (kTrigger.getTerrainRequired(i) == getTerrainType()) {
				bFoundValid = true;
				break;
			}
		}

		if (!bFoundValid) {
			return false;
		}
	}

	if (kTrigger.getNumImprovementsRequired() > 0) {
		bool bFoundValid = false;

		for (int i = 0; i < kTrigger.getNumImprovementsRequired(); ++i) {
			if (kTrigger.getImprovementRequired(i) == getImprovementType()) {
				bFoundValid = true;
				break;
			}
		}

		if (!bFoundValid) {
			return false;
		}
	}

	if (kTrigger.getNumBonusesRequired() > 0) {
		bool bFoundValid = false;

		for (int i = 0; i < kTrigger.getNumBonusesRequired(); ++i) {
			if (kTrigger.getBonusRequired(i) == getBonusType(kTrigger.isOwnPlot() ? GET_PLAYER(ePlayer).getTeam() : NO_TEAM)) {
				bFoundValid = true;
				break;
			}
		}

		if (!bFoundValid) {
			return false;
		}
	}

	if (kTrigger.getNumRoutesRequired() > 0) {
		bool bFoundValid = false;

		if (NULL == getPlotCity()) {
			for (int i = 0; i < kTrigger.getNumRoutesRequired(); ++i) {
				if (kTrigger.getRouteRequired(i) == getRouteType()) {
					bFoundValid = true;
					break;
				}
			}

		}

		if (!bFoundValid) {
			return false;
		}
	}

	if (kTrigger.isUnitsOnPlot()) {
		bool bFoundValid = false;

		CLLNode<IDInfo>* pUnitNode = headUnitNode();

		while (NULL != pUnitNode) {
			CvUnit* pLoopUnit = ::getUnit(pUnitNode->m_data);
			pUnitNode = nextUnitNode(pUnitNode);

			if (pLoopUnit->getOwnerINLINE() == ePlayer) {
				if (-1 != pLoopUnit->getTriggerValue(eTrigger, this, false)) {
					bFoundValid = true;
					break;
				}
			}
		}

		if (!bFoundValid) {
			return false;
		}
	}


	if (kTrigger.isPrereqEventCity() && kTrigger.getNumPrereqEvents() > 0) {
		bool bFoundValid = true;

		for (int iI = 0; iI < kTrigger.getNumPrereqEvents(); ++iI) {
			const EventTriggeredData* pTriggeredData = GET_PLAYER(ePlayer).getEventOccured((EventTypes)kTrigger.getPrereqEvent(iI));
			if (NULL == pTriggeredData || pTriggeredData->m_iPlotX != getX_INLINE() || pTriggeredData->m_iPlotY != getY_INLINE()) {
				bFoundValid = false;
				break;
			}
		}

		if (!bFoundValid) {
			return false;
		}
	}


	return true;
}

bool CvPlot::canApplyEvent(EventTypes eEvent) const {
	const CvEventInfo& kEvent = GC.getEventInfo(eEvent);

	if (kEvent.getFeatureChange() > 0) {
		if (NO_FEATURE != kEvent.getFeature()) {
			if (NO_IMPROVEMENT != getImprovementType() || !canHaveFeature((FeatureTypes)kEvent.getFeature())) {
				return false;
			}
		}
	} else if (kEvent.getFeatureChange() < 0) {
		if (NO_FEATURE == getFeatureType()) {
			return false;
		}
	}

	if (kEvent.getImprovementChange() > 0) {
		if (NO_IMPROVEMENT != kEvent.getImprovement()) {
			if (!canHaveImprovement((ImprovementTypes)kEvent.getImprovement(), getTeam())) {
				return false;
			}
		}
	} else if (kEvent.getImprovementChange() < 0) {
		if (NO_IMPROVEMENT == getImprovementType()) {
			return false;
		}
	}

	if (kEvent.getBonusChange() > 0) {
		if (NO_BONUS != kEvent.getBonus()) {
			if (!canHaveBonus((BonusTypes)kEvent.getBonus(), false)) {
				return false;
			}
		}
	} else if (kEvent.getBonusChange() < 0) {
		if (NO_BONUS == getBonusType()) {
			return false;
		}
	}

	if (kEvent.getRouteChange() < 0) {
		if (NO_ROUTE == getRouteType()) {
			return false;
		}

		if (isCity()) {
			return false;
		}
	}

	return true;
}

void CvPlot::applyEvent(EventTypes eEvent) {
	const CvEventInfo& kEvent = GC.getEventInfo(eEvent);

	if (kEvent.getFeatureChange() > 0) {
		if (NO_FEATURE != kEvent.getFeature()) {
			setFeatureType((FeatureTypes)kEvent.getFeature());
		}
	} else if (kEvent.getFeatureChange() < 0) {
		setFeatureType(NO_FEATURE);
	}

	if (kEvent.getImprovementChange() > 0) {
		if (NO_IMPROVEMENT != kEvent.getImprovement()) {
			setImprovementType((ImprovementTypes)kEvent.getImprovement());
		}
	} else if (kEvent.getImprovementChange() < 0) {
		setImprovementType(NO_IMPROVEMENT);
	}

	if (kEvent.getBonusChange() > 0) {
		if (NO_BONUS != kEvent.getBonus()) {
			setBonusType((BonusTypes)kEvent.getBonus());
		}
	} else if (kEvent.getBonusChange() < 0) {
		setBonusType(NO_BONUS);
	}

	if (kEvent.getRouteChange() > 0) {
		if (NO_ROUTE != kEvent.getRoute()) {
			setRouteType((RouteTypes)kEvent.getRoute(), true);
		}
	} else if (kEvent.getRouteChange() < 0) {
		setRouteType(NO_ROUTE, true);
	}

	for (int i = 0; i < NUM_YIELD_TYPES; ++i) {
		int iChange = kEvent.getPlotExtraYield(i);
		if (0 != iChange) {
			GC.getGameINLINE().setPlotExtraYield(m_iX, m_iY, (YieldTypes)i, iChange);
		}
	}
}

bool CvPlot::canTrain(UnitTypes eUnit, bool bContinue, bool bTestVisible) const {
	CvCity* pCity = getPlotCity();
	const CvUnitInfo& kUnit = GC.getUnitInfo(eUnit);

	if (kUnit.getMinCultureLevel() != NO_CULTURELEVEL) {
		if (NULL == pCity || pCity->getCultureLevel() < kUnit.getMinCultureLevel())	return false;
	}

	if (kUnit.getMinPopulation() > 0) {
		if (NULL == pCity || pCity->getPopulation() < kUnit.getMinPopulation()) return false;
	}

	if (kUnit.isPrereqPower()) {
		if (NULL == pCity || !pCity->isPower()) return false;
	}

	// AND Bonus
	for (int iI = 0; iI < kUnit.getNumPrereqVicinityAndBonus(); iI++) {
		if (NULL == pCity || !pCity->hasVicinityBonus((BonusTypes)kUnit.getPrereqVicinityAndBonus(iI))) return false;
	}

	// OR Bonus
	bool bValid = false;
	int iNumPrereq = kUnit.getNumPrereqVicinityOrBonus();
	if (NULL == pCity && iNumPrereq > 0) return false;
	for (int iI = 0; iI < iNumPrereq && !bValid; iI++) {
		bValid = pCity->hasVicinityBonus((BonusTypes)kUnit.getPrereqVicinityOrBonus(iI));
	}
	if (!bValid && iNumPrereq > 0) {
		return false;
	}

	// OR Building Classes
	bValid = false;
	iNumPrereq = kUnit.getNumPrereqOrBuildingClasses();
	if (NULL == pCity && iNumPrereq > 0) return false;
	for (int iI = 0; iI < iNumPrereq && !bValid; iI++) {
		bValid = pCity->getNumActiveBuildingClass((BuildingClassTypes)kUnit.getPrereqOrBuildingClass(iI)) > 0;
	}
	if (iNumPrereq > 0 && !bValid) {
		return false;
	}

	// Not Building Classes
	bValid = false;
	iNumPrereq = kUnit.getNumPrereqNotBuildingClasses();
	for (int iI = 0; iI < iNumPrereq; iI++) {
		if (NULL == pCity || !(pCity->getNumActiveBuildingClass((BuildingClassTypes)kUnit.getPrereqNotBuildingClass(iI)) > 0)) return false;
	}

	// Check for required improvements
	bValid = false;
	iNumPrereq = kUnit.getNumPrereqVicinityImprovements();
	if (NULL == pCity && iNumPrereq > 0) return false;
	for (int iI = 0; iI < iNumPrereq && !bValid; iI++) {
		bValid = pCity->hasVicinityImprovement((ImprovementTypes)kUnit.getPrereqVicinityImprovement(iI));
	}
	if (!bValid && iNumPrereq > 0) {
		return false;
	}

	// Check for required terrains
	for (int iI = 0; iI < kUnit.getNumPrereqAndTerrains(); iI++) {
		if (NULL == pCity || !pCity->hasVicinityBonus((BonusTypes)kUnit.getPrereqAndTerrain(iI))) return false;
	}

	// Check OR Terrains
	// If any succeed then we don't need to perform this check on any more terrains
	bValid = false;
	iNumPrereq = kUnit.getNumPrereqOrTerrains();
	if (NULL == pCity && iNumPrereq > 0) return false;
	for (int iI = 0; iI < iNumPrereq && !bValid; iI++) {
		bValid = pCity->hasVicinityTerrain((TerrainTypes)kUnit.getPrereqOrTerrain(iI));
	}
	if (!bValid && iNumPrereq > 0) {
		return false;
	}

	// Check for required features
	bValid = false;
	iNumPrereq = kUnit.getNumPrereqVicinityFeatures();
	if (NULL == pCity && iNumPrereq > 0) return false;
	for (int iI = 0; iI < iNumPrereq && !bValid; iI++) {
		bValid = pCity->hasVicinityFeature((FeatureTypes)kUnit.getPrereqVicinityFeature(iI));
	}
	if (!bValid && iNumPrereq > 0) {
		return false;
	}

	if (kUnit.isPrereqReligion()) {
		if (NULL == pCity || pCity->getReligionCount() == 0) // K-Mod
		{
			return false;
		}
	}

	if (kUnit.getPrereqReligion() != NO_RELIGION) {
		if (NULL == pCity || !pCity->isHasReligion((ReligionTypes)(kUnit.getPrereqReligion()))) {
			return false;
		}
	}

	if (kUnit.getPrereqCorporation() != NO_CORPORATION) {
		if (NULL == pCity || !pCity->isActiveCorporation((CorporationTypes)(kUnit.getPrereqCorporation()))) {
			return false;
		}
	}

	if (kUnit.isPrereqBonuses()) {
		if (kUnit.getDomainType() == DOMAIN_SEA) {
			bool bValid = false;

			for (DirectionTypes eDirection = (DirectionTypes)0; eDirection < NUM_DIRECTION_TYPES; eDirection = (DirectionTypes)(eDirection + 1)) {
				CvPlot* pLoopPlot = plotDirection(getX_INLINE(), getY_INLINE(), eDirection);

				if (pLoopPlot != NULL) {
					if (pLoopPlot->isWater()) {
						if (pLoopPlot->area()->getNumTotalBonuses() > 0) {
							bValid = true;
							break;
						}
					}
				}
			}

			if (!bValid) {
				return false;
			}
		} else {
			if (area()->getNumTotalBonuses() > 0) {
				return false;
			}
		}
	}

	if (isCity()) {
		if (kUnit.getDomainType() == DOMAIN_SEA) {
			if (!isWater() && !isCoastalLand(kUnit.getMinAreaSize())) {
				return false;
			}
		} else {
			if (area()->getNumTiles() < kUnit.getMinAreaSize()) {
				return false;
			}
		}
	} else {
		if (area()->getNumTiles() < kUnit.getMinAreaSize()) {
			return false;
		}

		if (kUnit.getDomainType() == DOMAIN_SEA) {
			if (!isWater()) {
				return false;
			}
		} else if (kUnit.getDomainType() == DOMAIN_LAND) {
			if (isWater()) {
				return false;
			}
		} else {
			return false;
		}
	}

	if (!bTestVisible) {
		if (kUnit.getHolyCity() != NO_RELIGION) {
			if (NULL == pCity || !pCity->isHolyCity((ReligionTypes)kUnit.getHolyCity())) {
				return false;
			}
		}

		if (kUnit.getPrereqBuilding() != NO_BUILDING) {
			if (NULL == pCity) {
				return false;
			}

			if (pCity->getNumBuilding((BuildingTypes)(kUnit.getPrereqBuilding())) == 0) {
				SpecialBuildingTypes eSpecialBuilding = ((SpecialBuildingTypes)(GC.getBuildingInfo((BuildingTypes)(kUnit.getPrereqBuilding())).getSpecialBuildingType()));

				if (eSpecialBuilding == NO_SPECIALBUILDING || !GET_PLAYER(getOwnerINLINE()).isSpecialBuildingNotRequired(eSpecialBuilding)) {
					return false;
				}
			}
		}

		if (kUnit.getPrereqAndBonus() != NO_BONUS) {
			if (NULL == pCity) {
				if (!isPlotGroupConnectedBonus(getOwnerINLINE(), (BonusTypes)kUnit.getPrereqAndBonus())) {
					return false;
				}
			} else {
				if (!pCity->hasBonus((BonusTypes)kUnit.getPrereqAndBonus())) {
					return false;
				}
			}
		}

		bool bRequiresBonus = false;
		bool bNeedsBonus = true;

		for (int iI = 0; iI < kUnit.getNumPrereqOrBonuses(); ++iI) {
			BonusTypes ePrereqBonus = (BonusTypes)kUnit.getPrereqOrBonus(iI);
			if (ePrereqBonus != NO_BONUS) {
				bRequiresBonus = true;

				if (NULL == pCity) {
					if (isPlotGroupConnectedBonus(getOwnerINLINE(), ePrereqBonus)) {
						bNeedsBonus = false;
						break;
					}
				} else {
					if (pCity->hasBonus(ePrereqBonus)) {
						bNeedsBonus = false;
						break;
					}
				}
			}
		}

		if (bRequiresBonus && bNeedsBonus) {
			return false;
		}
	}

	return true;
}

int CvPlot::countFriendlyCulture(TeamTypes eTeam) const {
	int iTotalCulture = 0;

	for (int iPlayer = 0; iPlayer < MAX_PLAYERS; ++iPlayer) {
		CvPlayer& kLoopPlayer = GET_PLAYER((PlayerTypes)iPlayer);
		if (kLoopPlayer.isAlive()) {
			CvTeam& kLoopTeam = GET_TEAM(kLoopPlayer.getTeam());
			if (kLoopPlayer.getTeam() == eTeam || kLoopTeam.isVassal(eTeam) || kLoopTeam.isOpenBorders(eTeam)) {
				iTotalCulture += getCulture((PlayerTypes)iPlayer);
			}
		}
	}

	return iTotalCulture;
}

int CvPlot::countNumAirUnits(TeamTypes eTeam) const {
	int iCount = 0;

	CLLNode<IDInfo>* pUnitNode = headUnitNode();
	while (pUnitNode != NULL) {
		CvUnit* pLoopUnit = ::getUnit(pUnitNode->m_data);
		pUnitNode = nextUnitNode(pUnitNode);

		if (DOMAIN_AIR == pLoopUnit->getDomainType() && !pLoopUnit->isCargo() && pLoopUnit->getTeam() == eTeam) {
			iCount += GC.getUnitInfo(pLoopUnit->getUnitType()).getAirUnitCap();
		}
	}

	return iCount;
}

int CvPlot::airUnitSpaceAvailable(TeamTypes eTeam) const {
	int iMaxUnits = 0;

	CvCity* pCity = getPlotCity();
	if (NULL != pCity) {
		iMaxUnits = pCity->getAirUnitCapacity(getTeam());
	} else {
		iMaxUnits = GC.getDefineINT("CITY_AIR_UNIT_CAPACITY");
	}

	return (iMaxUnits - countNumAirUnits(eTeam));
}

bool CvPlot::isEspionageCounterSpy(TeamTypes eTeam) const {
	CvCity* pCity = getPlotCity();

	if (NULL != pCity && pCity->getTeam() == eTeam) {
		if (pCity->getEspionageDefenseModifier() > 0) {
			return true;
		}
	}

	if (plotCount(PUF_isCounterSpy, -1, -1, NO_PLAYER, eTeam) > 0) {
		return true;
	}

	return false;
}

int CvPlot::getAreaIdForGreatWall() const {
	return getArea();
}

int CvPlot::getSoundScriptId() const {
	int iScriptId = -1;
	if (isActiveVisible(true)) {
		if (getImprovementType() != NO_IMPROVEMENT) {
			iScriptId = GC.getImprovementInfo(getImprovementType()).getWorldSoundscapeScriptId();
		} else if (getFeatureType() != NO_FEATURE) {
			iScriptId = GC.getFeatureInfo(getFeatureType()).getWorldSoundscapeScriptId();
		} else if (getTerrainType() != NO_TERRAIN) {
			iScriptId = GC.getTerrainInfo(getTerrainType()).getWorldSoundscapeScriptId();
		}
	}
	return iScriptId;
}

int CvPlot::get3DAudioScriptFootstepIndex(int iFootstepTag) const {
	if (getFeatureType() != NO_FEATURE) {
		return GC.getFeatureInfo(getFeatureType()).get3DAudioScriptFootstepIndex(iFootstepTag);
	}

	if (getTerrainType() != NO_TERRAIN) {
		return GC.getTerrainInfo(getTerrainType()).get3DAudioScriptFootstepIndex(iFootstepTag);
	}

	return -1;
}

float CvPlot::getAqueductSourceWeight() const {
	float fWeight = 0.0f;
	if (isLake() || isPeak() || (getFeatureType() != NO_FEATURE && GC.getFeatureInfo(getFeatureType()).isAddsFreshWater())) {
		fWeight = 1.0f;
	} else if (isHills()) {
		fWeight = 0.67f;
	}

	return fWeight;
}

bool CvPlot::shouldDisplayBridge(CvPlot* pToPlot, PlayerTypes ePlayer) const {
	TeamTypes eObservingTeam = GET_PLAYER(ePlayer).getTeam();
	TeamTypes eOurTeam = getRevealedTeam(eObservingTeam, true);
	TeamTypes eOtherTeam = NO_TEAM;
	if (pToPlot != NULL) {
		eOtherTeam = pToPlot->getRevealedTeam(eObservingTeam, true);
	}

	if (eOurTeam == eObservingTeam || eOtherTeam == eObservingTeam || (eOurTeam == NO_TEAM && eOtherTeam == NO_TEAM)) {
		return GET_TEAM(eObservingTeam).isBridgeBuilding();
	}

	if (eOurTeam == NO_TEAM) {
		return GET_TEAM(eOtherTeam).isBridgeBuilding();
	}

	if (eOtherTeam == NO_TEAM) {
		return GET_TEAM(eOurTeam).isBridgeBuilding();
	}

	return (GET_TEAM(eOurTeam).isBridgeBuilding() && GET_TEAM(eOtherTeam).isBridgeBuilding());
}

bool CvPlot::checkLateEra() const {
	PlayerTypes ePlayer = getOwnerINLINE();
	if (ePlayer == NO_PLAYER) {
		//find largest culture in this plot
		ePlayer = GC.getGameINLINE().getActivePlayer();
		int maxCulture = getCulture(ePlayer);
		for (int i = 0; i < MAX_PLAYERS; i++) {
			int newCulture = getCulture((PlayerTypes)i);
			if (newCulture > maxCulture) {
				maxCulture = newCulture;
				ePlayer = (PlayerTypes)i;
			}
		}
	}

	return (GET_PLAYER(ePlayer).getCurrentEra() > GC.getNumEraInfos() / 2);
}

bool CvPlot::hasDefender(bool bCheckCanAttack, PlayerTypes eOwner, PlayerTypes eAttackingPlayer, const CvUnit* pAttacker, bool bTestAtWar, bool bTestPotentialEnemy, bool bTestCanMove) const {
	CLLNode<IDInfo>* pUnitNode = headUnitNode();
	while (pUnitNode != NULL) {
		CvUnit* pLoopUnit = ::getUnit(pUnitNode->m_data);
		pUnitNode = nextUnitNode(pUnitNode);

		if ((eOwner == NO_PLAYER) || (pLoopUnit->getOwnerINLINE() == eOwner)) {
			if ((eAttackingPlayer == NO_PLAYER) || !(pLoopUnit->isInvisible(GET_PLAYER(eAttackingPlayer).getTeam(), false))) {
				if (!bTestAtWar || eAttackingPlayer == NO_PLAYER || pLoopUnit->isEnemy(GET_PLAYER(eAttackingPlayer).getTeam(), this) || (NULL != pAttacker && pAttacker->isEnemy(GET_PLAYER(pLoopUnit->getOwnerINLINE()).getTeam(), this))) {
					if (!bTestPotentialEnemy || (eAttackingPlayer == NO_PLAYER) || pLoopUnit->isPotentialEnemy(GET_PLAYER(eAttackingPlayer).getTeam(), this) || (NULL != pAttacker && pAttacker->isPotentialEnemy(GET_PLAYER(pLoopUnit->getOwnerINLINE()).getTeam(), this))) {
						if (!bTestCanMove || (pLoopUnit->canMove() && !(pLoopUnit->isCargo()))) {
							if ((pAttacker == NULL) || (pAttacker->getDomainType() != DOMAIN_AIR) || (pLoopUnit->getDamage() < pAttacker->airCombatLimit())) {
								if (!bCheckCanAttack || (pAttacker == NULL) || (pAttacker->canAttack(*pLoopUnit))) {
									// found a valid defender
									return true;
								}
							}
						}
					}
				}
			}
		}
	}

	// there are no defenders
	return false;
}

/*
 * Returns true if the build progress array has been created; false otherwise.
 * A false return value implies that every build has zero progress.
 * A true return value DOES NOT imply that any build has a non-zero progress--just the possibility.
 */
bool CvPlot::hasAnyBuildProgress() const {
	return NULL != m_paiBuildProgress;
}

bool CvPlot::isTeamBonus(TeamTypes eTeam) const {
	if (eTeam == NO_TEAM)
		return false;

	if (getBonusType(eTeam) == NO_BONUS)
		return false;

	if (getImprovementType() == NO_IMPROVEMENT)
		return false;

	if (!isBonusNetwork(eTeam))
		return false;

	if (!isWithinTeamCityRadius(eTeam))
		return false;

	if (GC.getImprovementInfo(getImprovementType()).isImprovementBonusMakesValid(getBonusType()))
		return true;

	return false;
}

bool CvPlot::isCanFortAttack() const {
	bool bValid = false;

	bool bImprovementValid = GC.getFORT_ATTACK_CITIES_ALSO() ? isCity(true) : (getImprovementType() != NO_IMPROVEMENT && GC.getImprovementInfo(getImprovementType()).isActsAsCity());

	if (bImprovementValid) {
		// Check if there are any valid attackers in the plot
		CLLNode<IDInfo>* pUnitNode = headUnitNode();
		while (pUnitNode != NULL) {
			CvUnit* pLoopUnit = ::getUnit(pUnitNode->m_data);
			pUnitNode = nextUnitNode(pUnitNode);
			if (pLoopUnit->getUnitCombatType() == NO_UNITCOMBAT)
				continue;

			if (pLoopUnit->canFortAttack() && !pLoopUnit->isOnlyDefensive()) {
				bValid = true;
				break;
			}
		}
	}
	return bValid;
}

void CvPlot::doFortAttack() {

	// No Fort? Move along, nothing to see here
	if (!isCanFortAttack()) {
		return;
	}

	// Teams that have a unit that can attack
	std::vector<TeamTypes> attackTeams;
	// Players that have attacked
	std::vector<PlayerTypes> attackPlayers;
	// Players that have been attacked
	std::vector<PlayerTypes> targettedPlayers;
	// Units that can attack
	std::vector<CvUnit*> attackers;
	// Potential target units for each attacking team
	std::map <TeamTypes, std::vector<CvUnit*> > targets;

	TeamTypes ePlotTeam = getOwnerINLINE() == NO_PLAYER ? NO_TEAM : GET_PLAYER(getOwner()).getTeam();

	// Get set of valid attackers from each team
	CLLNode<IDInfo>* pUnitNode = headUnitNode();
	while (pUnitNode != NULL) {
		CvUnit* pLoopUnit = ::getUnit(pUnitNode->m_data);
		pUnitNode = nextUnitNode(pUnitNode);
		if (pLoopUnit->getUnitCombatType() == NO_UNITCOMBAT)
			continue;

		if (pLoopUnit->canFortAttack() && !pLoopUnit->isOnlyDefensive()) {
			attackTeams.push_back(pLoopUnit->getTeam());
			attackers.push_back(pLoopUnit);
		}
	}

	// If there are no attackers then nothing to do
	if (attackers.empty()) {
		return;
	}

	// Check if there are any valid potential targets in the vicinity
	bool bFoundTarget = false;
	for (DirectionTypes eDirection = (DirectionTypes)0; eDirection < NUM_DIRECTION_TYPES; eDirection = (DirectionTypes)(eDirection + 1)) {
		CvPlot* pAdjacentPlot = plotDirection(getX_INLINE(), getY_INLINE(), eDirection);
		if (pAdjacentPlot != NULL) {
			pUnitNode = pAdjacentPlot->headUnitNode();
			while (pUnitNode != NULL) {
				CvUnit* pLoopUnit = ::getUnit(pUnitNode->m_data);
				pUnitNode = pAdjacentPlot->nextUnitNode(pUnitNode);

				// Only attack military units and ones that are not too damaged already
				if (!pLoopUnit->isOnlyDefensive() && pLoopUnit->getDamage() < GC.getFORT_ATTACK_DAMAGE_CAP()) {
					TeamTypes eTeamToCheckForWar;
					for (TeamTypes eTeam = (TeamTypes)0; eTeam < MAX_TEAMS; eTeam = (TeamTypes)(eTeam + 1)) {
						// If the plot is in a players territory then only attack teams that they are at war with
						eTeamToCheckForWar = ePlotTeam == NO_TEAM ? eTeam : ePlotTeam;
						// If there are attackers from this team and potential target is at war and visible then add them to the list
						if (std::find(attackTeams.begin(), attackTeams.end(), eTeam) != attackTeams.end()) {
							if (GET_TEAM(eTeamToCheckForWar).isAtWar(pLoopUnit->getTeam()) && !pLoopUnit->isInvisible(eTeam, false, false)) {
								bFoundTarget = true;
								targets[eTeam].push_back(pLoopUnit);
							}
						}
					}
				}

			}

		}
	}
	if (!bFoundTarget) {
		return;
	}

	// Loop through the attackers and perform the attack
	std::random_shuffle(attackers.begin(), attackers.end());
	while (!attackers.empty()) {
		// Get the next attacking unit and remove it from the vector
		CvUnit* pAttacker = attackers.back();
		attackers.pop_back();
		TeamTypes eAttackerTeam = pAttacker->getTeam();
		PlayerTypes eAttackerPlayer = pAttacker->getOwnerINLINE();

		// Select a random target
		int iNumPotentialTargets = targets[eAttackerTeam].size();
		if (iNumPotentialTargets <= 0)
			continue;

		int iTarget = GC.getGameINLINE().getSorenRandNum(iNumPotentialTargets, "Fort Attack Target");
		CvUnit* pTarget = targets[eAttackerTeam][iTarget];
		PlayerTypes eTargetPlayer = pTarget->getOwnerINLINE();

		// Perform the attack
		float fAttackerPower = (1.0f * pAttacker->baseCombatStr() * (100 - pAttacker->getDamage()) / 100);
		float fTargetPower = (1.0f * pTarget->baseCombatStr() * (100 - pTarget->getDamage()) / 100);
		int iDamage = (int)(GC.getFORT_ATTACK_BASE_DAMAGE() * fAttackerPower / fTargetPower);
		if (iDamage > 0) {
			if (std::find(attackPlayers.begin(), attackPlayers.end(), eAttackerPlayer) == attackPlayers.end())
				attackPlayers.push_back(eAttackerPlayer);

			pTarget->setDamage(std::min(GC.getFORT_ATTACK_DAMAGE_CAP(), pTarget->getDamage() + iDamage), eAttackerPlayer);
			if (std::find(targettedPlayers.begin(), targettedPlayers.end(), eTargetPlayer) == targettedPlayers.end())
				targettedPlayers.push_back(eTargetPlayer);
			// If the target has taken the max allowed value remove it as a future potential target
			if (pTarget->getDamage() > GC.getFORT_ATTACK_DAMAGE_CAP()) {
				targets[eAttackerTeam].erase(targets[eAttackerTeam].begin() + iTarget);
			}
		}
	}

	// Send out notifications
	CvWString szBuffer;
	if (ePlotTeam == NO_TEAM) {
		szBuffer = gDLL->getText("TXT_KEY_MISC_FORT_ATTACKED_NEUTRAL");
	} else {
		szBuffer = gDLL->getText("TXT_KEY_MISC_FORT_ATTACKED_ENEMY", GET_PLAYER(getOwnerINLINE()).getCivilizationAdjective());
	}
	for (std::vector<PlayerTypes>::iterator it = targettedPlayers.begin(); it != targettedPlayers.end(); ++it) {
		gDLL->getInterfaceIFace()->addHumanMessage(*it, false, GC.getEVENT_MESSAGE_TIME(), szBuffer, "AS2D_INTERCEPT", MESSAGE_TYPE_INFO, NULL, (ColorTypes)GC.getInfoTypeForString("COLOR_GREEN"), getX_INLINE(), getY_INLINE(), true, true);
	}
	// Send out notification to the attacker
	for (std::vector<PlayerTypes>::iterator it = attackPlayers.begin(); it != attackPlayers.end(); ++it) {
		gDLL->getInterfaceIFace()->addHumanMessage(*it, false, GC.getEVENT_MESSAGE_TIME(), gDLL->getText("TXT_KEY_MISC_FORT_ATTACKED_ATTACKER"), "AS2D_INTERCEPT", MESSAGE_TYPE_INFO, NULL, (ColorTypes)GC.getInfoTypeForString("COLOR_GREEN"), getX_INLINE(), getY_INLINE(), true, true);
	}
}

int CvPlot::getNumVisibleAdjacentEnemyDefenders(const CvUnit* pUnit) const {
	int iCount = 0;
	for (DirectionTypes eDirectionType = (DirectionTypes)0; eDirectionType < NUM_DIRECTION_TYPES; eDirectionType = (DirectionTypes)(eDirectionType + 1)) {
		CvPlot* pAdjacentPlot = plotDirection(getX_INLINE(), getY_INLINE(), eDirectionType);
		if (pAdjacentPlot != NULL) {
			iCount += pAdjacentPlot->plotCount(PUF_canDefendEnemy, pUnit->getOwnerINLINE(), pUnit->isAlwaysHostile(this), NO_PLAYER, NO_TEAM, PUF_isVisible, pUnit->getOwnerINLINE());
		}
	}
	return iCount;
}

bool CvPlot::isSubjectToFortAttack() const {
	bool bActiveFort = false;
	for (DirectionTypes eDirection = (DirectionTypes)0; eDirection < NUM_DIRECTION_TYPES; eDirection = (DirectionTypes)(eDirection + 1)) {
		CvPlot* pAdjacentPlot = plotDirection(getX_INLINE(), getY_INLINE(), eDirection);
		if (pAdjacentPlot != NULL) {
			if (pAdjacentPlot->isCanFortAttack()) {
				bActiveFort = true;
				break;
			}
		}
	}
	return bActiveFort;
}

bool CvPlot::isBorder(bool bIgnoreWater) const {
	for (DirectionTypes eDirection = (DirectionTypes)0; eDirection < NUM_DIRECTION_TYPES; eDirection = (DirectionTypes)(eDirection + 1)) {
		CvPlot* pAdjacentPlot = plotDirection(getX_INLINE(), getY_INLINE(), eDirection);
		if (pAdjacentPlot != NULL) {
			if (!isWater() && pAdjacentPlot->isWater() && !bIgnoreWater)
				return true;

			if (pAdjacentPlot->getOwnerINLINE() != getOwnerINLINE())
				return true;
		}
	}

	return false;
}

void CvPlot::doImprovementSpawn() {
	if (getImprovementType() != NO_IMPROVEMENT) {
		const CvImprovementInfo& kImprovement = GC.getImprovementInfo(getImprovementType());
		UnitTypes eSpawn = NO_UNIT;
		bool  bResident = false;
		int iSpawnRatePercentage = 0;

		bool bSpawnAnimal = false;
		bool bSpawnBarbarian = false;
		if (kImprovement.getAnimalSpawnRatePercentage() > 0) {
			bSpawnAnimal = true;
			iSpawnRatePercentage = kImprovement.getAnimalSpawnRatePercentage();
		} else if (kImprovement.getBarbarianSpawnRatePercentage() > 0) {
			bSpawnBarbarian = true;
			iSpawnRatePercentage = kImprovement.getBarbarianSpawnRatePercentage();
		}

		if (bSpawnAnimal || bSpawnBarbarian) {
			CvUnit* pResident;
			// If we have an existing resident spawn another of those
			CLLNode<IDInfo>* pUnitNode = headUnitNode();
			while (pUnitNode != NULL) {
				CvUnit* pLoopUnit = ::getUnit(pUnitNode->m_data);
				pUnitNode = nextUnitNode(pUnitNode);
				if (!bResident && NULL != pLoopUnit && pLoopUnit->isImmobile()) {
					bResident = true;
					pResident = pLoopUnit;
					eSpawn = pLoopUnit->getUnitType();
				}
			}

			// If we have no resident then look to create another

			if (bSpawnAnimal) {
				if (!bResident) {
					// If we have an existing animal on the plot use that type as the resident otherwise pick a new type
					CLLNode<IDInfo>* pUnitNode = headUnitNode();
					if (pUnitNode) {
						CvUnit* pLoopUnit = ::getUnit(pUnitNode->m_data);
						if (pLoopUnit->isAnimal()) {
							eSpawn = pLoopUnit->getUnitType();
						}
					}
					if (eSpawn == NO_UNIT) {
						eSpawn = getNativeAnimalRand();
					}
				}
			} else if (bSpawnBarbarian) {
				UnitTypes eBestUnit = getNativeBarbarianBest(NO_UNITAI, true, true);
				if (eSpawn != eBestUnit && eBestUnit != NO_UNIT) {
					eSpawn = eBestUnit;
					pResident->kill(false);
					bResident = false;
				}
			}

			if (eSpawn != NO_UNIT) {
				CvUnitInfo& kUnitInfo = GC.getUnitInfo(eSpawn);
				CvGame& kGame = GC.getGameINLINE();
				if (!kGame.isOption(GAMEOPTION_NO_BARBARIANS)) {
					CvArea* pArea = GC.getMapINLINE().getArea(getArea());
					if (pArea->getNumUnownedTiles() > 0) {
						int iTiles = GC.getTILES_PER_SPAWN();
						if (kUnitInfo.isAnimal()) {
							// Powerful animals need larger ranges
							iTiles *= CEIL_DIVIDE(kUnitInfo.getCombat(), 3);
						}
						if (!bResident || pArea->getUnitsPerPlayer((PlayerTypes)BARBARIAN_PLAYER) == 0 || (pArea->getNumUnownedTiles() / pArea->getUnitsPerPlayer((PlayerTypes)BARBARIAN_PLAYER)) > iTiles) {
							int iChance = GC.getHandicapInfo(kGame.getHandicapType()).getLairSpawnRate();
							iChance *= 10000;

							iChance *= iSpawnRatePercentage;
							iChance /= 100;

							iChance /= GC.getGameSpeedInfo(kGame.getGameSpeedType()).getTrainPercent();

							if (kGame.isOption(GAMEOPTION_RAGING_BARBARIANS))
								iChance *= 3;

							if (!bResident)
								iChance *= 2;


							if (kGame.getSorenRandNum(100, "Spawn Unit") < iChance) {
								if (!isVisibleOtherUnit(BARBARIAN_PLAYER)) {
									UnitAITypes eAI;
									if (kUnitInfo.isAnimal())
										eAI = UNITAI_ANIMAL;
									else
										eAI = kUnitInfo.getDomainType() == DOMAIN_SEA ? UNITAI_ATTACK_SEA : UNITAI_ATTACK;
									CvUnit* pUnit = GET_PLAYER(BARBARIAN_PLAYER).initUnit(eSpawn, getX_INLINE(), getY_INLINE(), eAI);
									if (!bResident) {
										// Set the unit to stay put and defend the improvement
										pUnit->setImmobile(true);
										int iStrength = pUnit->getUnitInfo().getCombat() * 125;
										iStrength /= 100;
										pUnit->setBaseCombatStr(iStrength);
										pUnit->changeFortifyTurns(5);
										pUnit->changeAlwaysHealCount(1);
									}
								}
							}
						}
					}
				}
				// Remove the spawning improvement if it is in territory owned by a civ
				if (isOwned() && !isBarbarian()) {
					setImprovementType(NO_IMPROVEMENT);
				}
			}
		}
	}
}

void CvPlot::getNativeAnimals(std::vector<UnitTypes>& vAnimals) const {
	vAnimals.clear();
	for (UnitTypes eUnit = (UnitTypes)0; eUnit < GC.getNumUnitInfos(); eUnit = (UnitTypes)(eUnit + 1)) {
		const CvUnitInfo& kUnit = GC.getUnitInfo(eUnit);

		// If this is not an animal then go to the next unit
		if (!kUnit.isAnimal()) continue;

		// If there are any terrain natives defined for the animal then it must match
		// If there are no terrain natives then we are neutral, but we must have a feature to match
		bool bTerrainMatch = false;
		bool bAnyTerrain = true;
		TerrainTypes ePlotTerrain = getTerrainType();
		for (TerrainTypes eTerrain = (TerrainTypes)0; eTerrain < GC.getNumTerrainInfos(); eTerrain = (TerrainTypes)(eTerrain + 1)) {
			if (kUnit.getTerrainNative(eTerrain)) {
				bAnyTerrain = false;
				if (eTerrain == ePlotTerrain) {
					bTerrainMatch = true;
					break;
				}
			}
		}
		if (!bAnyTerrain && !bTerrainMatch) continue;

		// If no terrain match then we need to check the features
		if (!bTerrainMatch) {
			bool bFeatureMatch = false;
			bool bAnyFeature = true;
			FeatureTypes ePlotFeature = getFeatureType();
			for (FeatureTypes eFeature = (FeatureTypes)0; eFeature < GC.getNumFeatureInfos(); eFeature = (FeatureTypes)(eFeature + 1)) {
				if (kUnit.getFeatureNative(eFeature)) {
					bAnyFeature = false;
					if (eFeature == ePlotFeature) {
						bFeatureMatch = true;
						break;
					}
				}
			}
			if (!bAnyFeature && !bFeatureMatch) continue;
		}

		vAnimals.push_back(eUnit);
	}
}

UnitTypes CvPlot::getNativeAnimalRand() const {
	UnitTypes eAnimal = NO_UNIT;
	std::vector<UnitTypes> vAnimals;
	getNativeAnimals(vAnimals);
	if (vAnimals.size() > 0) {
		std::random_shuffle(vAnimals.begin(), vAnimals.end());
		eAnimal = vAnimals.front();
	}
	return eAnimal;
}

void CvPlot::getNativeBarbarians(std::vector<std::pair<UnitTypes, int> >& vBarbarians, UnitAITypes eAI, bool bIncludeWater, bool bIncludeDefensive) const {
	vBarbarians.clear();
	for (UnitClassTypes eUnitClass = (UnitClassTypes)0; eUnitClass < GC.getNumUnitClassInfos(); eUnitClass = (UnitClassTypes)(eUnitClass + 1)) {
		int iTmp = eUnitClass;
		bool bValid = false;
		UnitTypes eLoopUnit = ((UnitTypes)(GC.getCivilizationInfo(GET_PLAYER(BARBARIAN_PLAYER).getCivilizationType()).getCivilizationUnits(eUnitClass)));

		if (eLoopUnit != NO_UNIT) {
			const CvUnitInfo& kUnit = GC.getUnitInfo(eLoopUnit);

			if (kUnit.getCombat() <= 0)
				continue;

			if (eAI != NO_UNITAI && !kUnit.getUnitAIType(eAI))
				continue;

			if (!bIncludeDefensive && kUnit.isOnlyDefensive())
				continue;

			if (kUnit.getDomainType() == DOMAIN_SEA) {
				if (!bIncludeWater)
					continue;

				if (!isWater())
					continue;
			}

			if (!GET_PLAYER(BARBARIAN_PLAYER).canTrain(eLoopUnit))
				continue;

			if (!canTrain(eLoopUnit, false, true))
				continue;

			vBarbarians.push_back(std::make_pair(eLoopUnit, kUnit.getCombat()));
		}
	}
}

UnitTypes CvPlot::getNativeBarbarianRand(UnitAITypes eAI, bool bIncludeWater) const {
	UnitTypes eBestUnit = NO_UNIT;
	std::vector<std::pair<UnitTypes, int> > vBarbarians;
	getNativeBarbarians(vBarbarians, eAI);
	if (vBarbarians.size() > 0) {
		std::random_shuffle(vBarbarians.begin(), vBarbarians.end());
		eBestUnit = vBarbarians[0].first;
	}
	return eBestUnit;
}

UnitTypes CvPlot::getNativeBarbarianBest(UnitAITypes eAI, bool bIncludeWater, bool bIncludeDefensive) const {
	UnitTypes eBestUnit = NO_UNIT;
	int iBestValue = 0;

	std::vector<std::pair<UnitTypes, int> > vBarbarians;
	getNativeBarbarians(vBarbarians, eAI, bIncludeWater, bIncludeDefensive);
	if (vBarbarians.size() > 0) {
		for (std::vector<std::pair<UnitTypes, int> >::iterator it = vBarbarians.begin(); it != vBarbarians.end(); ++it) {
			if ((*it).second > iBestValue) {
				eBestUnit = (*it).first;
				iBestValue = (*it).second;
			}
		}
	}
	return eBestUnit;
}

bool CvPlot::isLandUnitWaterSafe() const {
	if (!isWater()) {
		return false;
	}

	if (isSeaBridge() && isAdjacentToLand()) {
		return true;
	}

	if (isSeaTunnel()) {
		return true;
	}

	return false;
}

bool CvPlot::isSeaTunnel() const {
	if (isRoute()) {
		if (GC.getRouteInfo(getRouteType()).isSeaTunnel()) {
			return true;
		}
	}

	return false;
}

bool CvPlot::isSeaBridge() const {
	if (getImprovementType() != NO_IMPROVEMENT) {
		if (GC.getImprovementInfo(getImprovementType()).isSeaBridge()) {
			return true;
		}
	}
	return false;
}
