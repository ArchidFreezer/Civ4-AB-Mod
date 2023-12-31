// team.cpp

#include "CvGameCoreDLL.h"
#include "CvPlayerAI.h"
#include "CvDefines.h"
#include "CvGameCoreUtils.h"
#include "CvGlobals.h"
#include "CvTeamAI.h"
#include "CvPlayerAI.h"
#include "CvGameAI.h"
#include "CvMap.h"
#include "CvPlot.h"
#include "CvTeam.h"
#include "CvDLLInterfaceIFaceBase.h"
#include "CvEventReporter.h"
#include "CvDLLEngineIFaceBase.h"
#include "CvArtFileMgr.h"
#include "CvDiploParameters.h"
#include "CvInfos.h"
#include "CvPopupInfo.h"
#include "CvDLLPythonIFaceBase.h"
#include "CyArgsList.h"
#include "FProfiler.h"
#include "BetterBTSAI.h"

// Public Functions...

CvTeam::CvTeam() {
	m_aiStolenVisibilityTimer = new int[MAX_TEAMS];
	m_aiWarWeariness = new int[MAX_TEAMS];
	m_aiTechShareCount = new int[MAX_TEAMS];
	m_aiCommerceFlexibleCount = new int[NUM_COMMERCE_TYPES];
	m_aiExtraMoves = new int[NUM_DOMAIN_TYPES];
	m_aiEspionagePointsAgainstTeam = new int[MAX_TEAMS];
	m_aiCounterespionageTurnsLeftAgainstTeam = new int[MAX_TEAMS];
	m_aiCounterespionageModAgainstTeam = new int[MAX_TEAMS];

	m_abAtWar = new bool[MAX_TEAMS];
	m_abHasMet = new bool[MAX_TEAMS];
	m_abHasSeen = new bool[MAX_TEAMS]; // K-Mod
	m_abPermanentWarPeace = new bool[MAX_TEAMS];
	m_abOpenBorders = new bool[MAX_TEAMS];
	m_abDefensivePact = new bool[MAX_TEAMS];
	m_abForcePeace = new bool[MAX_TEAMS];
	m_abVassal = new bool[MAX_TEAMS];
	m_abEmbassy = new bool[MAX_TEAMS];
	m_abLimitedBorders = new bool[MAX_TEAMS];
	m_abFreeTradeAgreement = new bool[MAX_TEAMS];
	m_abHasNonAggression = new bool[MAX_TEAMS];
	m_abCanLaunch = NULL;

	m_paiRouteChange = NULL;
	m_paiProjectCount = NULL;
	m_paiProjectDefaultArtTypes = NULL;
	m_pavProjectArtTypes = NULL;
	m_paiProjectMaking = NULL;
	m_paiUnitClassCount = NULL;
	m_paiBuildingClassCount = NULL;
	m_paiObsoleteBuildingCount = NULL;
	m_paiResearchProgress = NULL;
	m_paiTechCount = NULL;
	m_paiTerrainTradeCount = NULL;
	m_aiVictoryCountdown = NULL;
	m_aiForceTeamVoteEligibilityCount = NULL;

	m_pabHasTech = NULL;
	m_pabNoTradeTech = NULL;

	m_ppaaiImprovementYieldChange = NULL;

	reset((TeamTypes)0, true);
}


CvTeam::~CvTeam() {
	uninit();

	SAFE_DELETE_ARRAY(m_aiStolenVisibilityTimer);
	SAFE_DELETE_ARRAY(m_aiWarWeariness);
	SAFE_DELETE_ARRAY(m_aiTechShareCount);
	SAFE_DELETE_ARRAY(m_aiCommerceFlexibleCount);
	SAFE_DELETE_ARRAY(m_aiExtraMoves);
	SAFE_DELETE_ARRAY(m_aiEspionagePointsAgainstTeam);
	SAFE_DELETE_ARRAY(m_aiCounterespionageTurnsLeftAgainstTeam);
	SAFE_DELETE_ARRAY(m_aiCounterespionageModAgainstTeam);
	SAFE_DELETE_ARRAY(m_abAtWar);
	SAFE_DELETE_ARRAY(m_abHasMet);
	SAFE_DELETE_ARRAY(m_abHasSeen); // K-Mod
	SAFE_DELETE_ARRAY(m_abPermanentWarPeace);
	SAFE_DELETE_ARRAY(m_abOpenBorders);
	SAFE_DELETE_ARRAY(m_abDefensivePact);
	SAFE_DELETE_ARRAY(m_abForcePeace);
	SAFE_DELETE_ARRAY(m_abVassal);
	SAFE_DELETE_ARRAY(m_abEmbassy);
	SAFE_DELETE_ARRAY(m_abLimitedBorders);
	SAFE_DELETE_ARRAY(m_abFreeTradeAgreement);
	SAFE_DELETE_ARRAY(m_abHasNonAggression);
}


void CvTeam::init(TeamTypes eID) {
	//--------------------------------
	// Init saved data
	reset(eID);

	//--------------------------------
	// Init non-saved data

	//--------------------------------
	// Init other game data
	AI_init();

	if (GC.getGame().isFinalInitialized()) {
		for (int iI = 0; iI < MAX_TEAMS; iI++) {
			if (iI != getID()) {
				if (GET_TEAM((TeamTypes)iI).isMinorCiv()) {
					GET_TEAM((TeamTypes)iI).declareWar(getID(), false, WARPLAN_LIMITED);
				}
				if (GET_TEAM((TeamTypes)iI).isBarbarian()) {
					GET_TEAM((TeamTypes)iI).declareWar(getID(), false, WARPLAN_LIMITED);
				}
			}
		}
	}
}


void CvTeam::uninit() {
	SAFE_DELETE_ARRAY(m_abCanLaunch);

	SAFE_DELETE_ARRAY(m_paiRouteChange);
	SAFE_DELETE_ARRAY(m_paiProjectCount);
	SAFE_DELETE_ARRAY(m_paiProjectDefaultArtTypes);
	SAFE_DELETE_ARRAY(m_pavProjectArtTypes);
	SAFE_DELETE_ARRAY(m_paiProjectMaking);
	SAFE_DELETE_ARRAY(m_paiUnitClassCount);
	SAFE_DELETE_ARRAY(m_paiBuildingClassCount);
	SAFE_DELETE_ARRAY(m_paiObsoleteBuildingCount);
	SAFE_DELETE_ARRAY(m_paiResearchProgress);
	SAFE_DELETE_ARRAY(m_paiTechCount);
	SAFE_DELETE_ARRAY(m_paiTerrainTradeCount);
	SAFE_DELETE_ARRAY(m_aiVictoryCountdown);
	SAFE_DELETE_ARRAY(m_aiForceTeamVoteEligibilityCount);

	SAFE_DELETE_ARRAY(m_pabHasTech);
	SAFE_DELETE_ARRAY(m_pabNoTradeTech);

	if (m_ppaaiImprovementYieldChange != NULL) {
		for (int iI = 0; iI < GC.getNumImprovementInfos(); iI++) {
			SAFE_DELETE_ARRAY(m_ppaaiImprovementYieldChange[iI]);
		}
		SAFE_DELETE_ARRAY(m_ppaaiImprovementYieldChange);
	}
}


// FUNCTION: reset()
// Initializes data members that are serialized.
void CvTeam::reset(TeamTypes eID, bool bConstructorCall) {
	//--------------------------------
	// Uninit class
	uninit();

	m_iNumMembers = 0;
	m_iAliveCount = 0;
	m_iEverAliveCount = 0;
	m_iNumCities = 0;
	m_iTotalPopulation = 0;
	m_iTotalLand = 0;
	m_iNukeInterception = 0;
	m_iExtraWaterSeeFromCount = 0;
	m_iMapTradingCount = 0;
	m_iTechTradingCount = 0;
	m_iGoldTradingCount = 0;
	m_iOpenBordersTradingCount = 0;
	m_iDefensivePactTradingCount = 0;
	m_iPermanentAllianceTradingCount = 0;
	m_iVassalTradingCount = 0;
	m_iBridgeBuildingCount = 0;
	m_iIrrigationCount = 0;
	m_iIgnoreIrrigationCount = 0;
	m_iWaterWorkCount = 0;
	m_iVassalPower = 0;
	m_iMasterPower = 0;
	m_iEnemyWarWearinessModifier = 0;
	m_iRiverTradeCount = 0;
	m_iEspionagePointsEver = 0;
	m_iCanPassPeaksCount = 0;
	m_iMoveFastPeaksCount = 0;
	m_iCanFoundOnPeaksCount = 0;
	m_iEmbassyTradingCount = 0;
	m_iLimitedBordersTradingCount = 0;
	m_iFreeTradeAgreementTradingCount = 0;
	m_iNonAggressionTradingCount = 0;
	m_iStarSignImpactedCount = 0;

	m_bMapCentering = false;
	m_bCapitulated = false;

	m_eID = eID;

	for (TeamTypes eTeam = (TeamTypes)0; eTeam < MAX_TEAMS; eTeam = (TeamTypes)(eTeam + 1)) {
		m_aiStolenVisibilityTimer[eTeam] = 0;
		m_aiWarWeariness[eTeam] = 0;
		m_aiTechShareCount[eTeam] = 0;
		m_aiEspionagePointsAgainstTeam[eTeam] = 0;
		m_aiCounterespionageTurnsLeftAgainstTeam[eTeam] = 0;
		m_aiCounterespionageModAgainstTeam[eTeam] = 0;
		m_abHasMet[eTeam] = false;
		m_abHasSeen[eTeam] = false; // K-Mod
		m_abAtWar[eTeam] = false;
		m_abPermanentWarPeace[eTeam] = false;
		m_abOpenBorders[eTeam] = false;
		m_abDefensivePact[eTeam] = false;
		m_abForcePeace[eTeam] = false;
		m_abVassal[eTeam] = false;
		m_abEmbassy[eTeam] = false;
		m_abLimitedBorders[eTeam] = false;
		m_abFreeTradeAgreement[eTeam] = false;
		m_abHasNonAggression[eTeam] = false;

		if (!bConstructorCall && getID() != NO_TEAM) {
			CvTeam& kLoopTeam = GET_TEAM(eTeam);
			kLoopTeam.m_aiStolenVisibilityTimer[getID()] = 0;
			kLoopTeam.m_aiWarWeariness[getID()] = 0;
			kLoopTeam.m_aiTechShareCount[getID()] = 0;
			kLoopTeam.m_aiEspionagePointsAgainstTeam[getID()] = 0;
			kLoopTeam.m_aiCounterespionageTurnsLeftAgainstTeam[getID()] = 0;
			kLoopTeam.m_aiCounterespionageModAgainstTeam[getID()] = 0;
			kLoopTeam.m_abHasMet[getID()] = false;
			kLoopTeam.m_abHasSeen[getID()] = false; // K-Mod
			kLoopTeam.m_abAtWar[getID()] = false;
			kLoopTeam.m_abPermanentWarPeace[getID()] = false;
			kLoopTeam.m_abOpenBorders[getID()] = false;
			kLoopTeam.m_abDefensivePact[getID()] = false;
			kLoopTeam.m_abForcePeace[getID()] = false;
			kLoopTeam.m_abVassal[getID()] = false;
			kLoopTeam.m_abLimitedBorders[getID()] = false;
			kLoopTeam.m_abFreeTradeAgreement[getID()] = false;
			kLoopTeam.m_abHasNonAggression[getID()] = false;
		}
	}

	for (CommerceTypes eCommerce = (CommerceTypes)0; eCommerce < NUM_COMMERCE_TYPES; eCommerce = (CommerceTypes)(eCommerce + 1)) {
		m_aiCommerceFlexibleCount[eCommerce] = 0;
	}

	for (DomainTypes eDomain = (DomainTypes)0; eDomain < NUM_DOMAIN_TYPES; eDomain = (DomainTypes)(eDomain + 1)) {
		m_aiExtraMoves[eDomain] = 0;
	}

	if (!bConstructorCall) {
		FAssertMsg(m_abCanLaunch == NULL, "about to leak memory, CvTeam::m_abCanLaunch");
		m_abCanLaunch = new bool[GC.getNumVictoryInfos()];
		for (VictoryTypes eVictory = (VictoryTypes)0; eVictory < GC.getNumVictoryInfos(); eVictory = (VictoryTypes)(eVictory + 1)) {
			m_abCanLaunch[eVictory] = false;
		}

		FAssertMsg(m_paiRouteChange == NULL, "about to leak memory, CvTeam::m_paiRouteChange");
		m_paiRouteChange = new int[GC.getNumRouteInfos()];
		for (RouteTypes eRoute = (RouteTypes)0; eRoute < GC.getNumRouteInfos(); eRoute = (RouteTypes)(eRoute + 1)) {
			m_paiRouteChange[eRoute] = 0;
		}

		FAssertMsg(m_paiProjectCount == NULL, "about to leak memory, CvPlayer::m_paiProjectCount");
		m_paiProjectCount = new int[GC.getNumProjectInfos()];
		FAssertMsg(m_paiProjectDefaultArtTypes == NULL, "about to leak memory, CvPlayer::m_paiProjectDefaultArtTypes");
		m_paiProjectDefaultArtTypes = new int[GC.getNumProjectInfos()];
		FAssertMsg(m_pavProjectArtTypes == NULL, "about to leak memory, CvPlayer::m_pavProjectArtTypes");
		m_pavProjectArtTypes = new std::vector<int>[GC.getNumProjectInfos()];
		FAssertMsg(m_paiProjectMaking == NULL, "about to leak memory, CvPlayer::m_paiProjectMaking");
		m_paiProjectMaking = new int[GC.getNumProjectInfos()];
		for (ProjectTypes eProject = (ProjectTypes)0; eProject < GC.getNumProjectInfos(); eProject = (ProjectTypes)(eProject + 1)) {
			m_paiProjectCount[eProject] = 0;
			m_paiProjectDefaultArtTypes[eProject] = 0;
			m_paiProjectMaking[eProject] = 0;
		}

		FAssertMsg(m_paiUnitClassCount == NULL, "about to leak memory, CvTeam::m_paiUnitClassCount");
		m_paiUnitClassCount = new int[GC.getNumUnitClassInfos()];
		for (UnitClassTypes eUnitClass = (UnitClassTypes)0; eUnitClass < GC.getNumUnitClassInfos(); eUnitClass = (UnitClassTypes)(eUnitClass + 1)) {
			m_paiUnitClassCount[eUnitClass] = 0;
		}

		FAssertMsg(m_paiBuildingClassCount == NULL, "about to leak memory, CvTeam::m_paiBuildingClassCount");
		m_paiBuildingClassCount = new int[GC.getNumBuildingClassInfos()];
		for (BuildingClassTypes eBuildingClass = (BuildingClassTypes)0; eBuildingClass < GC.getNumBuildingClassInfos(); eBuildingClass = (BuildingClassTypes)(eBuildingClass + 1)) {
			m_paiBuildingClassCount[eBuildingClass] = 0;
		}

		FAssertMsg(m_paiObsoleteBuildingCount == NULL, "about to leak memory, CvTeam::m_paiObsoleteBuildingCount");
		m_paiObsoleteBuildingCount = new int[GC.getNumBuildingInfos()];
		for (BuildingTypes eBuilding = (BuildingTypes)0; eBuilding < GC.getNumBuildingInfos(); eBuilding = (BuildingTypes)(eBuilding + 1)) {
			m_paiObsoleteBuildingCount[eBuilding] = 0;
		}

		FAssertMsg(m_paiResearchProgress == NULL, "about to leak memory, CvPlayer::m_paiResearchProgress");
		m_paiResearchProgress = new int[GC.getNumTechInfos()];
		FAssertMsg(m_paiTechCount == NULL, "about to leak memory, CvPlayer::m_paiTechCount");
		m_paiTechCount = new int[GC.getNumTechInfos()];
		for (TechTypes eTech = (TechTypes)0; eTech < GC.getNumTechInfos(); eTech = (TechTypes)(eTech + 1)) {
			m_paiResearchProgress[eTech] = 0;
			m_paiTechCount[eTech] = 0;
		}

		FAssertMsg(m_paiTerrainTradeCount == NULL, "about to leak memory, CvTeam::m_paiTerrainTradeCount");
		m_paiTerrainTradeCount = new int[GC.getNumTerrainInfos()];
		for (TerrainTypes eTerrain = (TerrainTypes)0; eTerrain < GC.getNumTerrainInfos(); eTerrain = (TerrainTypes)(eTerrain + 1)) {
			m_paiTerrainTradeCount[eTerrain] = 0;
		}

		FAssertMsg(m_aiVictoryCountdown == NULL, "about to leak memory, CvTeam::m_aiVictoryCountdown");
		m_aiVictoryCountdown = new int[GC.getNumVictoryInfos()];
		for (VictoryTypes eVictory = (VictoryTypes)0; eVictory < GC.getNumVictoryInfos(); eVictory = (VictoryTypes)(eVictory + 1)) {
			m_aiVictoryCountdown[eVictory] = -1;
		}

		FAssertMsg(m_pabHasTech == NULL, "about to leak memory, CvTeam::m_pabHasTech");
		m_pabHasTech = new bool[GC.getNumTechInfos()];
		FAssertMsg(m_pabNoTradeTech == NULL, "about to leak memory, CvTeam::m_pabNoTradeTech");
		m_pabNoTradeTech = new bool[GC.getNumTechInfos()];
		for (TechTypes eTech = (TechTypes)0; eTech < GC.getNumTechInfos(); eTech = (TechTypes)(eTech + 1)) {
			m_pabHasTech[eTech] = false;
			m_pabNoTradeTech[eTech] = false;
		}

		FAssertMsg(m_ppaaiImprovementYieldChange == NULL, "about to leak memory, CvTeam::m_ppaaiImprovementYieldChange");
		m_ppaaiImprovementYieldChange = new int* [GC.getNumImprovementInfos()];
		for (ImprovementTypes eImprovement = (ImprovementTypes)0; eImprovement < GC.getNumImprovementInfos(); eImprovement = (ImprovementTypes)(eImprovement + 1)) {
			m_ppaaiImprovementYieldChange[eImprovement] = new int[NUM_YIELD_TYPES];
			for (YieldTypes eYield = (YieldTypes)0; eYield < NUM_YIELD_TYPES; eYield = (YieldTypes)(eYield + 1)) {
				m_ppaaiImprovementYieldChange[eImprovement][eYield] = 0;
			}
		}

		FAssertMsg(m_aiForceTeamVoteEligibilityCount == NULL, "about to leak memory, CvTeam::m_aiForceTeamVoteEligibilityCount");
		m_aiForceTeamVoteEligibilityCount = new int[GC.getNumVoteSourceInfos()];
		for (VoteSourceTypes eVoteSource = (VoteSourceTypes)0; eVoteSource < GC.getNumVoteSourceInfos(); eVoteSource = (VoteSourceTypes)(eVoteSource + 1)) {
			m_aiForceTeamVoteEligibilityCount[eVoteSource] = 0;
		}

		m_aeRevealedBonuses.clear();

		AI_reset(false);
	}
}

// for clearing data stored in plots and cities for this team
void CvTeam::resetPlotAndCityData() {
	for (int iI = 0; iI < GC.getMapINLINE().numPlotsINLINE(); iI++) {
		CvPlot* pLoopPlot = GC.getMapINLINE().plotByIndexINLINE(iI);

		pLoopPlot->setRevealedOwner(getID(), NO_PLAYER);
		pLoopPlot->setRevealedImprovementType(getID(), NO_IMPROVEMENT);
		pLoopPlot->setRevealedRouteType(getID(), NO_ROUTE);
		pLoopPlot->setRevealed(getID(), false, false, getID(), true);

		CvCity* pLoopCity = pLoopPlot->getPlotCity();
		if (pLoopCity != NULL) {
			pLoopCity->setRevealed(getID(), false);
			pLoopCity->setEspionageVisibility(getID(), false, true);
		}
	}
}

void CvTeam::addTeam(TeamTypes eTeam) {
	FAssert(eTeam != NO_TEAM);
	FAssert(eTeam != getID());

	CvTeam& kTeam = GET_TEAM(eTeam);
	int iOriginalTeamSize = getNumMembers();// K-Mod

	CvWString szBuffer;
	for (PlayerTypes ePlayer = (PlayerTypes)0; ePlayer < MAX_PLAYERS; ePlayer = (PlayerTypes)(ePlayer + 1)) {
		const CvPlayer& kLoopPlayer = GET_PLAYER(ePlayer);
		TeamTypes eLoopTeam = kLoopPlayer.getTeam();
		const CvTeam& kLoopTeam = GET_TEAM(eLoopTeam);
		if (kLoopPlayer.isAlive()) {
			if ((eLoopTeam != getID()) && (eLoopTeam != eTeam)) {
				if (kLoopTeam.isHasMet(getID()) && kLoopTeam.isHasMet(eTeam)) {
					szBuffer = gDLL->getText("TXT_KEY_MISC_PLAYER_PERMANENT_ALLIANCE", getName().GetCString(), kTeam.getName().GetCString());
					gDLL->getInterfaceIFace()->addHumanMessage(ePlayer, false, GC.getEVENT_MESSAGE_TIME(), szBuffer, "AS2D_THEIRALLIANCE", MESSAGE_TYPE_MINOR_EVENT, NULL, (ColorTypes)GC.getInfoTypeForString("COLOR_HIGHLIGHT_TEXT"));
				}
			}
		}
	}

	szBuffer = gDLL->getText("TXT_KEY_MISC_PLAYER_PERMANENT_ALLIANCE", getReplayName().GetCString(), kTeam.getReplayName().GetCString());
	GC.getGameINLINE().addReplayMessage(REPLAY_MESSAGE_MAJOR_EVENT, getLeaderID(), szBuffer, -1, -1, (ColorTypes)GC.getInfoTypeForString("COLOR_HIGHLIGHT_TEXT"));


	// K-Mod note: the cancel deals code use to be here. I've moved it lower down.

	shareItems(eTeam);
	kTeam.shareItems(getID());

	for (TeamTypes eLoopTeam = (TeamTypes)0; eLoopTeam < MAX_TEAMS; eLoopTeam = (TeamTypes)(eLoopTeam + 1)) {
		if (eLoopTeam != getID() && eLoopTeam != eTeam) {
			if (GET_TEAM(eLoopTeam).isAlive()) {
				if (kTeam.isHasMet(eLoopTeam)) {
					meet(eLoopTeam, false);
				} else if (isHasMet(eLoopTeam)) {
					kTeam.meet(eLoopTeam, false);
				}
			}
		}
	}

	for (TeamTypes eLoopTeam = (TeamTypes)0; eLoopTeam < MAX_TEAMS; eLoopTeam = (TeamTypes)(eLoopTeam + 1)) {
		if (eLoopTeam != getID() && eLoopTeam != eTeam) {
			if (GET_TEAM(eLoopTeam).isAlive()) {
				if (kTeam.isAtWar(eLoopTeam)) {
					declareWar(eLoopTeam, false, kTeam.AI_getWarPlan(eLoopTeam));
				} else if (isAtWar(eLoopTeam)) {
					kTeam.declareWar(eLoopTeam, false, AI_getWarPlan(eLoopTeam));
				}
			}
		}
	}

	for (TeamTypes eLoopTeam = (TeamTypes)0; eLoopTeam < MAX_TEAMS; eLoopTeam = (TeamTypes)(eLoopTeam + 1)) {
		if (eLoopTeam != getID() && eLoopTeam != eTeam) {
			if (GET_TEAM(eLoopTeam).isAlive()) {
				if (kTeam.isPermanentWarPeace(eLoopTeam)) {
					setPermanentWarPeace(eLoopTeam, true);
				} else if (isPermanentWarPeace(eLoopTeam)) {
					kTeam.setPermanentWarPeace(eLoopTeam, true);
				}
			}
		}
	}

	for (TeamTypes eLoopTeam = (TeamTypes)0; eLoopTeam < MAX_TEAMS; eLoopTeam = (TeamTypes)(eLoopTeam + 1)) {
		if (eLoopTeam != getID() && eLoopTeam != eTeam) {
			CvTeam& kLoopTeam = GET_TEAM(eLoopTeam);
			if (kLoopTeam.isAlive()) {
				if (kTeam.isOpenBorders(eLoopTeam)) {
					setOpenBorders(eLoopTeam, true);
					kLoopTeam.setOpenBorders(getID(), true);
				} else if (isOpenBorders(eLoopTeam)) {
					kTeam.setOpenBorders(eLoopTeam, true);
					kLoopTeam.setOpenBorders(eTeam, true);
				}
			}
		}
	}

	for (TeamTypes eLoopTeam = (TeamTypes)0; eLoopTeam < MAX_TEAMS; eLoopTeam = (TeamTypes)(eLoopTeam + 1)) {
		if (eLoopTeam != getID() && eLoopTeam != eTeam) {
			CvTeam& kLoopTeam = GET_TEAM(eLoopTeam);
			if (kLoopTeam.isAlive()) {
				if (kTeam.isDefensivePact(eLoopTeam)) {
					setDefensivePact(eLoopTeam, true);
					kLoopTeam.setDefensivePact(getID(), true);
				} else if (isDefensivePact(eLoopTeam)) {
					kTeam.setDefensivePact(eLoopTeam, true);
					kLoopTeam.setDefensivePact(eTeam, true);
				}
			}
		}
	}

	for (TeamTypes eLoopTeam = (TeamTypes)0; eLoopTeam < MAX_TEAMS; eLoopTeam = (TeamTypes)(eLoopTeam + 1)) {
		if (eLoopTeam != getID() && eLoopTeam != eTeam) {
			CvTeam& kLoopTeam = GET_TEAM(eLoopTeam);
			if (kLoopTeam.isAlive()) {
				if (kTeam.isForcePeace(eLoopTeam)) {
					setForcePeace(eLoopTeam, true);
					kLoopTeam.setForcePeace(getID(), true);
				} else if (isForcePeace(eLoopTeam)) {
					kTeam.setForcePeace(eLoopTeam, true);
					kLoopTeam.setForcePeace(eTeam, true);
				}
			}
		}
	}

	for (TeamTypes eLoopTeam = (TeamTypes)0; eLoopTeam < MAX_TEAMS; eLoopTeam = (TeamTypes)(eLoopTeam + 1)) {
		if (eLoopTeam != getID() && eLoopTeam != eTeam) {
			if (GET_TEAM(eLoopTeam).isAlive()) {
				if (kTeam.isVassal(eLoopTeam)) {
					setVassal(eLoopTeam, true, kTeam.isCapitulated()); // K-Mod
				} else if (isVassal(eLoopTeam)) {
					kTeam.setVassal(eLoopTeam, true, isCapitulated());
				}
			}
		}
	}

	for (TeamTypes eLoopTeam = (TeamTypes)0; eLoopTeam < MAX_TEAMS; eLoopTeam = (TeamTypes)(eLoopTeam + 1)) {
		if (eLoopTeam != getID() && eLoopTeam != eTeam) {
			CvTeam& kLoopTeam = GET_TEAM(eLoopTeam);
			if (kLoopTeam.isAlive()) {
				if (kLoopTeam.isVassal(eTeam)) {
					kLoopTeam.setVassal(getID(), true, kLoopTeam.isCapitulated());
				} else if (kLoopTeam.isVassal(getID())) {
					kLoopTeam.setVassal(eTeam, true, kLoopTeam.isCapitulated());
				}
			}
		}
	}

	for (TeamTypes eLoopTeam = (TeamTypes)0; eLoopTeam < MAX_TEAMS; eLoopTeam = (TeamTypes)(eLoopTeam + 1)) {
		if ((eLoopTeam != getID()) && (eLoopTeam != eTeam)) {
			CvTeam& kLoopTeam = GET_TEAM(eLoopTeam);
			if (kLoopTeam.isAlive()) {
				if (kTeam.isHasEmbassy(eLoopTeam)) {
					setHasEmbassy(eLoopTeam, true);
					kLoopTeam.setHasEmbassy(getID(), true);
				} else if (isDefensivePact(eLoopTeam) || isOpenBorders(eLoopTeam)) {
					kTeam.setHasEmbassy(eLoopTeam, true);
					kLoopTeam.setHasEmbassy(eTeam, true);
				}
			}
		}
	}

	for (TeamTypes eLoopTeam = (TeamTypes)0; eLoopTeam < MAX_TEAMS; eLoopTeam = (TeamTypes)(eLoopTeam + 1)) {
		if ((eLoopTeam != getID()) && (eLoopTeam != eTeam)) {
			CvTeam& kLoopTeam = GET_TEAM(eLoopTeam);
			if (kLoopTeam.isAlive()) {
				if (kTeam.isLimitedBorders(eLoopTeam)) {
					setLimitedBorders(eLoopTeam, true);
					kLoopTeam.setLimitedBorders(getID(), true);
				} else if (isLimitedBorders(eLoopTeam)) {
					kTeam.setLimitedBorders(eLoopTeam, true);
					kLoopTeam.setLimitedBorders(eTeam, true);
				}
			}
		}
	}

	shareCounters(eTeam);
	// K-Mod note: eTeam is not going to be used after we've finished this merge, so the sharing does not need to be two-way.

	for (PlayerTypes ePlayer = (PlayerTypes)0; ePlayer < MAX_PLAYERS; ePlayer = (PlayerTypes)(ePlayer + 1)) {
		if (GET_PLAYER(ePlayer).getTeam() == eTeam) {
			GET_PLAYER(ePlayer).setTeam(getID());
		}
	}

	// K-Mod. Adjust the progress of unfinished research so that it is proportionally the same as it was before the merge.
	{
		// cf. CvTeam::getResearchCost
		int iCostMultiplier = 100;
		iCostMultiplier *= 100 + GC.getDefineINT("TECH_COST_EXTRA_TEAM_MEMBER_MODIFIER") * (getNumMembers() - 1); // new
		iCostMultiplier /= 100 + GC.getDefineINT("TECH_COST_EXTRA_TEAM_MEMBER_MODIFIER") * (iOriginalTeamSize - 1); // old

		FAssert(iCostMultiplier >= 100);

		for (TechTypes i = (TechTypes)0; i < GC.getNumTechInfos(); i = (TechTypes)(i + 1)) {
			if (!isHasTech(i) && getResearchProgress(i) > 0)
				setResearchProgress(i, getResearchProgress(i) * iCostMultiplier / 100, getLeaderID());
		}
	}

	// K-Mod. The following cancel deals code has been moved from higher up.
	// I've done this so that when open-borders is canceled, it doesn't bump our new allies out of our borders.
	int iLoop;
	for (CvDeal* pLoopDeal = GC.getGameINLINE().firstDeal(&iLoop); pLoopDeal != NULL; pLoopDeal = GC.getGameINLINE().nextDeal(&iLoop)) {
		// K-Mod. The player's teams have already been reassigned - so we don't check for eTeam anymore.
		if (GET_PLAYER(pLoopDeal->getFirstPlayer()).getTeam() == getID() && GET_PLAYER(pLoopDeal->getSecondPlayer()).getTeam() == getID()) {
			bool bValid = true;

			if (pLoopDeal->getFirstTrades() != NULL) {
				for (CLLNode<TradeData>* pNode = pLoopDeal->getFirstTrades()->head(); pNode; pNode = pLoopDeal->getFirstTrades()->next(pNode)) {
					if ((pNode->m_data.m_eItemType == TRADE_OPEN_BORDERS) ||
						(pNode->m_data.m_eItemType == TRADE_LIMITED_BORDERS) ||
						(pNode->m_data.m_eItemType == TRADE_DEFENSIVE_PACT) ||
						(pNode->m_data.m_eItemType == TRADE_PEACE_TREATY) ||
						(pNode->m_data.m_eItemType == TRADE_VASSAL) ||
						(pNode->m_data.m_eItemType == TRADE_FREE_TRADE_ZONE) ||
						(pNode->m_data.m_eItemType == TRADE_NON_AGGRESSION) ||
						(pNode->m_data.m_eItemType == TRADE_SURRENDER)) {
						bValid = false;
					}
				}
			}

			if (pLoopDeal->getSecondTrades() != NULL) {
				for (CLLNode<TradeData>* pNode = pLoopDeal->getSecondTrades()->head(); pNode; pNode = pLoopDeal->getSecondTrades()->next(pNode)) {
					if ((pNode->m_data.m_eItemType == TRADE_OPEN_BORDERS) ||
						(pNode->m_data.m_eItemType == TRADE_LIMITED_BORDERS) ||
						(pNode->m_data.m_eItemType == TRADE_DEFENSIVE_PACT) ||
						(pNode->m_data.m_eItemType == TRADE_PEACE_TREATY) ||
						(pNode->m_data.m_eItemType == TRADE_VASSAL) ||
						(pNode->m_data.m_eItemType == TRADE_FREE_TRADE_ZONE) ||
						(pNode->m_data.m_eItemType == TRADE_NON_AGGRESSION) ||
						(pNode->m_data.m_eItemType == TRADE_SURRENDER)) {
						bValid = false;
					}
				}
			}

			if (!bValid) {
				pLoopDeal->kill();
			}
		}
	}

	for (int iI = 0; iI < GC.getMapINLINE().numPlotsINLINE(); iI++) {
		CvPlot* pLoopPlot = GC.getMapINLINE().plotByIndexINLINE(iI);

		pLoopPlot->changeVisibilityCount(getID(), pLoopPlot->getVisibilityCount(eTeam), NO_INVISIBLE, false);

		for (int iJ = 0; iJ < GC.getNumInvisibleInfos(); iJ++) {
			pLoopPlot->changeInvisibleVisibilityCount(getID(), ((InvisibleTypes)iJ), pLoopPlot->getInvisibleVisibilityCount(eTeam, ((InvisibleTypes)iJ)));
		}

		if (pLoopPlot->isRevealed(eTeam, false)) {
			pLoopPlot->setRevealed(getID(), true, false, eTeam, false);
		}
	}

	GC.getGameINLINE().updatePlotGroups();

	for (TeamTypes eLoopTeam = (TeamTypes)0; eLoopTeam < MAX_TEAMS; eLoopTeam = (TeamTypes)(eLoopTeam + 1)) {
		if ((eLoopTeam != getID()) && (eLoopTeam != eTeam)) {
			CvTeamAI& kLoopTeam = GET_TEAM(eLoopTeam); // K-Mod
			kLoopTeam.setWarWeariness(getID(), ((kLoopTeam.getWarWeariness(getID()) + kLoopTeam.getWarWeariness(eTeam)) / 2));
			kLoopTeam.setStolenVisibilityTimer(getID(), ((kLoopTeam.getStolenVisibilityTimer(getID()) + kLoopTeam.getStolenVisibilityTimer(eTeam)) / 2));
			kLoopTeam.AI_setAtWarCounter(getID(), ((kLoopTeam.AI_getAtWarCounter(getID()) + kLoopTeam.AI_getAtWarCounter(eTeam)) / 2));
			kLoopTeam.AI_setAtPeaceCounter(getID(), ((kLoopTeam.AI_getAtPeaceCounter(getID()) + kLoopTeam.AI_getAtPeaceCounter(eTeam)) / 2));
			kLoopTeam.AI_setHasMetCounter(getID(), ((kLoopTeam.AI_getHasMetCounter(getID()) + kLoopTeam.AI_getHasMetCounter(eTeam)) / 2));
			kLoopTeam.AI_setDefensivePactCounter(getID(), ((kLoopTeam.AI_getDefensivePactCounter(getID()) + kLoopTeam.AI_getDefensivePactCounter(eTeam)) / 2));
			kLoopTeam.AI_setShareWarCounter(getID(), ((kLoopTeam.AI_getShareWarCounter(getID()) + kLoopTeam.AI_getShareWarCounter(eTeam)) / 2));
			kLoopTeam.AI_setWarSuccess(getID(), ((kLoopTeam.AI_getWarSuccess(getID()) + kLoopTeam.AI_getWarSuccess(eTeam)) / 2));
			kLoopTeam.AI_setEnemyPeacetimeTradeValue(getID(), ((kLoopTeam.AI_getEnemyPeacetimeTradeValue(getID()) + kLoopTeam.AI_getEnemyPeacetimeTradeValue(eTeam)) / 2));
			kLoopTeam.AI_setEnemyPeacetimeGrantValue(getID(), ((kLoopTeam.AI_getEnemyPeacetimeGrantValue(getID()) + kLoopTeam.AI_getEnemyPeacetimeGrantValue(eTeam)) / 2));
			kLoopTeam.setEspionagePointsAgainstTeam(getID(), std::max(kLoopTeam.getEspionagePointsAgainstTeam(getID()), kLoopTeam.getEspionagePointsAgainstTeam(eTeam))); // unofficial patch

			if (kLoopTeam.isAlive()) {
				kLoopTeam.AI_setWarPlan(getID(), NO_WARPLAN, false);
				kLoopTeam.AI_setWarPlan(eTeam, NO_WARPLAN, false);
			}
		}
	}

	AI_updateWorstEnemy();

	AI_updateAreaStragies();

	GC.getGameINLINE().updateScore(true);
}


void CvTeam::shareItems(TeamTypes eTeam) {
	FAssert(eTeam != NO_TEAM);
	FAssert(eTeam != getID());

	for (int iI = 0; iI < GC.getNumTechInfos(); iI++) {
		if (GET_TEAM(eTeam).isHasTech((TechTypes)iI)) {
			setHasTech(((TechTypes)iI), true, NO_PLAYER, true, false);
		}
	}

	for (int iI = 0; iI < GC.getNumBonusInfos(); ++iI) {
		if (GET_TEAM(eTeam).isForceRevealedBonus((BonusTypes)iI)) {
			setForceRevealedBonus((BonusTypes)iI, true);
		}
	}

	for (int iTeam = 0; iTeam < MAX_TEAMS; ++iTeam) {
		setEspionagePointsAgainstTeam((TeamTypes)iTeam, std::max(GET_TEAM(eTeam).getEspionagePointsAgainstTeam((TeamTypes)iTeam), getEspionagePointsAgainstTeam((TeamTypes)iTeam)));
	}
	setEspionagePointsEver(std::max(GET_TEAM(eTeam).getEspionagePointsEver(), getEspionagePointsEver())); // K-Mod

	for (int iI = 0; iI < MAX_PLAYERS; iI++) {
		if (GET_PLAYER((PlayerTypes)iI).isAlive()) {
			if (GET_PLAYER((PlayerTypes)iI).getTeam() == eTeam) {
				int iLoop;
				for (CvCity* pLoopCity = GET_PLAYER((PlayerTypes)iI).firstCity(&iLoop); pLoopCity != NULL; pLoopCity = GET_PLAYER((PlayerTypes)iI).nextCity(&iLoop)) {
					for (int iJ = 0; iJ < GC.getNumBuildingInfos(); iJ++) {
						if (pLoopCity->getNumBuilding((BuildingTypes)iJ) > 0) {
							if (!isObsoleteBuilding((BuildingTypes)iJ)) {
								if (GC.getBuildingInfo((BuildingTypes)iJ).isTeamShare()) {
									for (int iK = 0; iK < MAX_PLAYERS; iK++) {
										if (GET_PLAYER((PlayerTypes)iK).getTeam() == getID()) {
											GET_PLAYER((PlayerTypes)iK).processBuilding(((BuildingTypes)iJ), pLoopCity->getNumBuilding((BuildingTypes)iJ), pLoopCity->area());
										}
									}
								}

								processBuilding(((BuildingTypes)iJ), pLoopCity->getNumBuilding((BuildingTypes)iJ));
							}
						}
					}
				}
			}
		}
	}

	for (int iI = 0; iI < MAX_PLAYERS; iI++) {
		if (GET_PLAYER((PlayerTypes)iI).isAlive()) {
			if (GET_PLAYER((PlayerTypes)iI).getTeam() == eTeam) {
				GET_PLAYER((PlayerTypes)iI).AI_updateBonusValue();
			}
		}
	}
}

// K-Mod. I've editted this function quite a lot. (for reasons that have been lost in the sands of time)
void CvTeam::shareCounters(TeamTypes eTeam) {
	CvTeamAI& kShareTeam = GET_TEAM(eTeam); // K-Mod
	for (TeamTypes eLoopTeam = (TeamTypes)0; eLoopTeam < MAX_TEAMS; eLoopTeam = (TeamTypes)(eLoopTeam + 1)) {
		if (eLoopTeam != getID() && eLoopTeam != eTeam) {
			if (kShareTeam.getWarWeariness(eLoopTeam) > getWarWeariness(eLoopTeam))
				setWarWeariness(eLoopTeam, kShareTeam.getWarWeariness(eLoopTeam));

			if (kShareTeam.getStolenVisibilityTimer(eLoopTeam) > getStolenVisibilityTimer(eLoopTeam))
				setStolenVisibilityTimer(eLoopTeam, kShareTeam.getStolenVisibilityTimer(eLoopTeam));

			if (kShareTeam.AI_getAtWarCounter(eLoopTeam) > AI_getAtWarCounter(eLoopTeam))
				AI_setAtWarCounter(eLoopTeam, kShareTeam.AI_getAtWarCounter(eLoopTeam));

			if (kShareTeam.AI_getAtPeaceCounter(eLoopTeam) > AI_getAtPeaceCounter(eLoopTeam))
				AI_setAtPeaceCounter(eLoopTeam, kShareTeam.AI_getAtPeaceCounter(eLoopTeam));

			if (kShareTeam.AI_getHasMetCounter(eLoopTeam) > AI_getHasMetCounter(eLoopTeam))
				AI_setHasMetCounter(eLoopTeam, kShareTeam.AI_getHasMetCounter(eLoopTeam));

			if (kShareTeam.AI_getOpenBordersCounter(eLoopTeam) > AI_getOpenBordersCounter(eLoopTeam))
				AI_setOpenBordersCounter(eLoopTeam, kShareTeam.AI_getOpenBordersCounter(eLoopTeam));

			if (kShareTeam.AI_getDefensivePactCounter(eLoopTeam) > AI_getDefensivePactCounter(eLoopTeam))
				AI_setDefensivePactCounter(eLoopTeam, kShareTeam.AI_getDefensivePactCounter(eLoopTeam));

			if (kShareTeam.AI_getShareWarCounter(eLoopTeam) > AI_getShareWarCounter(eLoopTeam))
				AI_setShareWarCounter(eLoopTeam, kShareTeam.AI_getShareWarCounter(eLoopTeam));

			if (kShareTeam.AI_getWarSuccess(eLoopTeam) > AI_getWarSuccess(eLoopTeam))
				AI_setWarSuccess(eLoopTeam, kShareTeam.AI_getWarSuccess(eLoopTeam));

			if (kShareTeam.AI_getEnemyPeacetimeTradeValue(eLoopTeam) > AI_getEnemyPeacetimeTradeValue(eLoopTeam))
				AI_setEnemyPeacetimeTradeValue(eLoopTeam, kShareTeam.AI_getEnemyPeacetimeTradeValue(eLoopTeam));

			if (kShareTeam.AI_getEnemyPeacetimeGrantValue(eLoopTeam) > AI_getEnemyPeacetimeGrantValue(eLoopTeam))
				AI_setEnemyPeacetimeGrantValue(eLoopTeam, kShareTeam.AI_getEnemyPeacetimeGrantValue(eLoopTeam));

			kShareTeam.AI_setWarPlan(eLoopTeam, NO_WARPLAN, false);
			// K-Mod note. presumably, the warplan is cleared under the assumption that kShareTeam is going to be removed.
		}
	}

	for (ProjectTypes eProject = (ProjectTypes)0; eProject < GC.getNumProjectInfos(); eProject = (ProjectTypes)(eProject + 1)) {
		// set project counts to the max of the two teams.
		int iDelta = kShareTeam.getProjectCount(eProject) - getProjectCount(eProject);
		if (iDelta > 0) {
			changeProjectCount(eProject, iDelta);
			// don't count the additional projects that have been added in this way
			GC.getGameINLINE().incrementProjectCreatedCount(eProject, -iDelta);
		}


		// projects still under construction should be counted for both teams
		changeProjectMaking(eProject, kShareTeam.getProjectMaking(eProject));
	}

	for (UnitClassTypes eUnitClass = (UnitClassTypes)0; eUnitClass < GC.getNumUnitClassInfos(); eUnitClass = (UnitClassTypes)(eUnitClass + 1)) {
		changeUnitClassCount(eUnitClass, kShareTeam.getUnitClassCount(eUnitClass));
	}

	for (BuildingClassTypes eBuildingClass = (BuildingClassTypes)0; eBuildingClass < GC.getNumBuildingClassInfos(); eBuildingClass = (BuildingClassTypes)(eBuildingClass + 1)) {
		changeBuildingClassCount(eBuildingClass, kShareTeam.getBuildingClassCount(eBuildingClass));
	}

	for (TechTypes eTech = (TechTypes)0; eTech < GC.getNumTechInfos(); eTech = (TechTypes)(eTech + 1)) {
		if (!isHasTech(eTech) && !kShareTeam.isHasTech(eTech)) {
			// K-Mod note: it's difficult to do any combined proportionality adjustments here, because if we set
			// the progress higher than the current cost then we'll get the tech right now before the cost is increased.
			// We can however adjust for uneven tech costs before the teams are merged.
			// (eg. suppose techs are more expensive for team 2; if team 2 almost has a tech - and if progress is
			//  transfered without adjustment, team 1 will immediately get the tech even though team 2 didn't finish it.)

			if (kShareTeam.getResearchProgress(eTech) * getResearchCost(eTech) > getResearchProgress(eTech) * kShareTeam.getResearchCost(eTech)) {
				setResearchProgress(eTech, kShareTeam.getResearchProgress(eTech) * getResearchCost(eTech) / std::max(1, kShareTeam.getResearchCost(eTech)), getLeaderID());
			}
		}

		// unofficial patch
		if (kShareTeam.isHasTech(eTech) && !(kShareTeam.isNoTradeTech(eTech))) {
			setNoTradeTech((eTech), false);
		}
	}

	// K-Mod. Share extra moves.
	// Note: there is no reliable way to do this. We can't tell if the bonus is from something unique- such as circumnavigation,
	//       or from something that is already taken into account - such as refrigeration.
	for (DomainTypes eDomain = (DomainTypes)0; eDomain < NUM_DOMAIN_TYPES; eDomain = (DomainTypes)(eDomain + 1)) {
		if (kShareTeam.getExtraMoves(eDomain) > getExtraMoves(eDomain))
			changeExtraMoves(eDomain, kShareTeam.getExtraMoves(eDomain) - getExtraMoves(eDomain));
	}
}


void CvTeam::processBuilding(BuildingTypes eBuilding, int iChange) {
	for (int i = 0; i < GC.getNumVoteSourceInfos(); ++i) {
		if (GC.getBuildingInfo(eBuilding).getVoteSourceType() == i) {
			changeForceTeamVoteEligibilityCount((VoteSourceTypes)i, (GC.getBuildingInfo(eBuilding).isForceTeamVoteEligible()) ? iChange : 0);
		}
	}

	if (GC.getBuildingInfo(eBuilding).isMapCentering()) {
		if (iChange > 0) {
			setMapCentering(true);
		}
	}

	changeEnemyWarWearinessModifier(GC.getBuildingInfo(eBuilding).getEnemyWarWearinessModifier() * iChange);
}


void CvTeam::doTurn() {
	PROFILE("CvTeam::doTurn()")

		FAssert(isAlive());

	AI_doTurnPre();

	if (isBarbarian()) {
		// K-Mod. Delay the start of the barbarian research. (This is an experimental change. It is currently compensated by an increase in the barbarian tech rate.)
		const CvPlayerAI& kBarbPlayer = GET_PLAYER(getLeaderID());
		const CvGame& kGame = GC.getGameINLINE();
		if (kGame.getElapsedGameTurns() >= GC.getHandicapInfo(kGame.getHandicapType()).getBarbarianCreationTurnsElapsed() * GC.getGameSpeedInfo(kGame.getGameSpeedType()).getBarbPercent() / 200) {
			for (TechTypes i = (TechTypes)0; i < GC.getNumTechInfos(); i = (TechTypes)(i + 1)) {
				if (!isHasTech(i) && kBarbPlayer.canResearch(i, 0, true)) // K-Mod. Make no progress on techs until prereqs are researched.
				{
					int iCount = 0;
					int iPossibleCount = 0;

					for (int iJ = 0; iJ < MAX_CIV_TEAMS; iJ++) {
						if (GET_TEAM((TeamTypes)iJ).isAlive()) {
							if (GET_TEAM((TeamTypes)iJ).isHasTech(i)) {
								iCount++;
							}

							iPossibleCount++;
						}
					}

					if (iCount > 0) {
						FAssertMsg(iPossibleCount > 0, "iPossibleCount is expected to be greater than 0");

						// K-Mod. Adjust research rate for game-speed & start-era - but _not_ world-size. And fix the rounding error.
						int iBaseCost = getResearchCost(i, false) * GC.getWorldInfo(GC.getMapINLINE().getWorldSize()).getResearchPercent() / 100;
						changeResearchProgress(i, std::max(1, iBaseCost * GC.getDefineINT("BARBARIAN_FREE_TECH_PERCENT") * iCount / (100 * iPossibleCount)), kBarbPlayer.getID());
					}
				}
			}
		}
	}

	for (int iI = 0; iI < MAX_TEAMS; iI++) {
		if (GET_TEAM((TeamTypes)iI).isAlive()) {
			if (getStolenVisibilityTimer((TeamTypes)iI) > 0) {
				changeStolenVisibilityTimer(((TeamTypes)iI), -1);
			}

			if (getCounterespionageTurnsLeftAgainstTeam((TeamTypes)iI) > 0) {
				changeCounterespionageTurnsLeftAgainstTeam((TeamTypes)iI, -1);
			}

			if (getCounterespionageTurnsLeftAgainstTeam((TeamTypes)iI) == 0) {
				setCounterespionageModAgainstTeam((TeamTypes)iI, 0);
			}
		}
	}

	if (!GC.getGameINLINE().isOption(GAMEOPTION_NO_TECH_BROKERING)) {
		for (int iI = 0; iI < GC.getNumTechInfos(); iI++) {
			setNoTradeTech(((TechTypes)iI), false);
		}

	}

	doWarWeariness();

	testCircumnavigated(); // K-Mod note: is it a bit unfair to test circumnavigation in this function?

	AI_doTurnPost();
}


void CvTeam::updateYield() {
	for (PlayerTypes ePlayer = (PlayerTypes)0; ePlayer < MAX_PLAYERS; ePlayer = (PlayerTypes)(ePlayer + 1)) {
		CvPlayer& kPlayer = GET_PLAYER(ePlayer);
		if (kPlayer.isAlive()) {
			if (kPlayer.getTeam() == getID()) {
				kPlayer.updateYield();
			}
		}
	}
}


void CvTeam::updatePowerHealth() {
	for (PlayerTypes ePlayer = (PlayerTypes)0; ePlayer < MAX_PLAYERS; ePlayer = (PlayerTypes)(ePlayer + 1)) {
		CvPlayer& kPlayer = GET_PLAYER(ePlayer);
		if (kPlayer.isAlive()) {
			if (kPlayer.getTeam() == getID()) {
				kPlayer.updatePowerHealth();
			}
		}
	}
}


void CvTeam::updatePowerStatus(bool bAcquire) {
	for (PlayerTypes ePlayer = (PlayerTypes)0; ePlayer < MAX_PLAYERS; ePlayer = (PlayerTypes)(ePlayer + 1)) {
		CvPlayer& kPlayer = GET_PLAYER(ePlayer);
		if (kPlayer.isAlive()) {
			if (kPlayer.getTeam() == getID()) {
				kPlayer.updatePowerStatus(bAcquire);
			}
		}
	}
}

void CvTeam::updateCommerce() {
	for (PlayerTypes ePlayer = (PlayerTypes)0; ePlayer < MAX_PLAYERS; ePlayer = (PlayerTypes)(ePlayer + 1)) {
		CvPlayer& kPlayer = GET_PLAYER(ePlayer);
		if (kPlayer.isAlive()) {
			if (kPlayer.getTeam() == getID()) {
				kPlayer.updateCommerce();
			}
		}
	}
}


bool CvTeam::canChangeWarPeace(TeamTypes eTeam, bool bAllowVassal) const {
	if (GC.getGameINLINE().isOption(GAMEOPTION_NO_CHANGING_WAR_PEACE)) {
		return false;
	}

	if (eTeam == getID()) {
		return false;
	}

	if (isPermanentWarPeace(eTeam) || GET_TEAM(eTeam).isPermanentWarPeace(getID())) {
		return false;
	}

	for (int iLoopTeam = 0; iLoopTeam < MAX_CIV_TEAMS; ++iLoopTeam) {
		CvTeam& kLoopTeam = GET_TEAM((TeamTypes)iLoopTeam);
		if (kLoopTeam.isAlive()) {
			if (kLoopTeam.isVassal(getID()) && kLoopTeam.isPermanentWarPeace(eTeam)) {
				return false;
			}

			if (kLoopTeam.isVassal(eTeam) && kLoopTeam.isPermanentWarPeace(getID())) {
				return false;
			}
		}
	}

	if (isAVassal()) {
		return false;
	}

	if (bAllowVassal) {
		if (GET_TEAM(eTeam).isVassal(getID())) {
			return false;
		}
	} else {
		if (GET_TEAM(eTeam).isAVassal()) {
			return false;
		}
	}

	return true;
}

// K-Mod, I've removed the bulk of this function and replaced it with just a call to "canEventuallyDeclareWar",
// which contains all of the original checks. I've done this to reduce code dupliation.
bool CvTeam::canDeclareWar(TeamTypes eTeam) const {
	if (!canEventuallyDeclareWar(eTeam))
		return false;

	if (isForcePeace(eTeam)) {
		return false;
	}

	for (int i = 0; i < MAX_TEAMS; ++i) {
		if (i != eTeam && i != getID() && GET_TEAM(eTeam).isVassal((TeamTypes)i)) {
			if (isForcePeace((TeamTypes)i)) {
				return false;
			}
		}
	}

	return true;
}

bool CvTeam::canEventuallyDeclareWar(TeamTypes eTeam) const {
	if (eTeam == getID()) {
		return false;
	}

	if (!(isAlive()) || !(GET_TEAM(eTeam).isAlive())) {
		return false;
	}

	if (isAtWar(eTeam)) {
		return false;
	}

	if (!isHasMet(eTeam)) {
		return false;
	}

	if (!canChangeWarPeace(eTeam, true)) {
		return false;
	}

	if (GC.getGameINLINE().isOption(GAMEOPTION_ALWAYS_PEACE)) {
		return false;
	}

	if (GC.getUSE_CAN_DECLARE_WAR_CALLBACK()) {
		CyArgsList argsList;
		argsList.add(getID());	// Team ID
		argsList.add(eTeam);	// pass in city class
		long lResult = 0;
		gDLL->getPythonIFace()->callFunction(PYGameModule, "canDeclareWar", argsList.makeFunctionArgs(), &lResult);

		if (lResult == 0) {
			return false;
		}
	}

	return true;
}

// K-Mod note: I've shuffled things around a bit in this function.
void CvTeam::declareWar(TeamTypes eTeam, bool bNewDiplo, WarPlanTypes eWarPlan, bool bPrimaryDoW) {
	PROFILE_FUNC();

	FAssertMsg(eTeam != NO_TEAM, "eTeam is not assigned a valid value");
	FAssertMsg(eTeam != getID(), "eTeam is not expected to be equal with getID()");

	if (isAtWar(eTeam))
		return;

	if (gTeamLogLevel >= 1) {
		logBBAI("  Team %d (%S) declares war on team %d", getID(), GET_PLAYER(getLeaderID()).getCivilizationDescription(0), eTeam);
	}

	int iLoop;
	for (CvDeal* pLoopDeal = GC.getGameINLINE().firstDeal(&iLoop); pLoopDeal != NULL; pLoopDeal = GC.getGameINLINE().nextDeal(&iLoop)) {
		if (((GET_PLAYER(pLoopDeal->getFirstPlayer()).getTeam() == getID()) && (GET_PLAYER(pLoopDeal->getSecondPlayer()).getTeam() == eTeam)) ||
			((GET_PLAYER(pLoopDeal->getFirstPlayer()).getTeam() == eTeam) && (GET_PLAYER(pLoopDeal->getSecondPlayer()).getTeam() == getID()))) {
			pLoopDeal->kill();
		}
	}

	for (int iI = 0; iI < MAX_PLAYERS; iI++) {
		if ((GET_PLAYER((PlayerTypes)iI).getTeam() == getID()) || (GET_PLAYER((PlayerTypes)iI).getTeam() == eTeam)) {
			GET_PLAYER((PlayerTypes)iI).updatePlunder(-1, false);
		}
	}

	FAssertMsg(eTeam != getID(), "eTeam is not expected to be equal with getID()");
	setAtWar(eTeam, true);
	GET_TEAM(eTeam).setAtWar(getID(), true);

	// Plot danger cache (bbai)
	GC.getMapINLINE().invalidateBorderDangerCache(eTeam);
	GC.getMapINLINE().invalidateBorderDangerCache(getID());
	//

	for (int iI = 0; iI < MAX_PLAYERS; iI++) {
		if ((GET_PLAYER((PlayerTypes)iI).getTeam() == getID()) || (GET_PLAYER((PlayerTypes)iI).getTeam() == eTeam)) {
			GET_PLAYER((PlayerTypes)iI).updatePlunder(1, false);
		}
	}

	meet(eTeam, false);

	AI_setAtPeaceCounter(eTeam, 0);
	GET_TEAM(eTeam).AI_setAtPeaceCounter(getID(), 0);

	AI_setShareWarCounter(eTeam, 0);
	GET_TEAM(eTeam).AI_setShareWarCounter(getID(), 0);

	GET_TEAM(eTeam).AI_setWarPlan(getID(), ((isBarbarian() || isMinorCiv()) ? WARPLAN_ATTACKED : WARPLAN_ATTACKED_RECENT));

	for (int iI = 0; iI < MAX_TEAMS; iI++) {
		if (GET_TEAM((TeamTypes)iI).isAlive()) {
			if (!GET_TEAM(eTeam).isAtWar((TeamTypes)iI) && GET_TEAM(eTeam).AI_isChosenWar((TeamTypes)iI)) {
				GET_TEAM(eTeam).AI_setWarPlan(((TeamTypes)iI), NO_WARPLAN);
			}
		}
	}

	if (NO_WARPLAN != eWarPlan) {
		AI_setWarPlan(eTeam, eWarPlan);
	}

	FAssert(!(AI_isSneakAttackPreparing(eTeam)));
	if ((AI_getWarPlan(eTeam) == NO_WARPLAN) || AI_isSneakAttackPreparing(eTeam)) {
		if (isHuman() || AI_getWarPlan(eTeam) == WARPLAN_PREPARING_TOTAL) // K-Mod. (for vassals that have been told to prepare for war)
		{
			AI_setWarPlan(eTeam, WARPLAN_TOTAL);
		} else if (isMinorCiv() || isBarbarian() || (GET_TEAM(eTeam).getAtWarCount(true) == 1)) {
			AI_setWarPlan(eTeam, WARPLAN_LIMITED);
		} else {
			AI_setWarPlan(eTeam, WARPLAN_DOGPILE);
		}
	}

	GC.getMapINLINE().verifyUnitValidPlot();

	for (int iI = 0; iI < MAX_PLAYERS; iI++) {
		if (GET_PLAYER((PlayerTypes)iI).getTeam() == getID()) {
			GET_PLAYER((PlayerTypes)iI).verifyUnitStacksValid();
		}
	}

	GC.getGameINLINE().AI_makeAssignWorkDirty();

	if ((getID() == GC.getGameINLINE().getActiveTeam()) || (eTeam == GC.getGameINLINE().getActiveTeam())) {
		gDLL->getInterfaceIFace()->setDirty(Score_DIRTY_BIT, true);
		gDLL->getInterfaceIFace()->setDirty(CityInfo_DIRTY_BIT, true);
	}

	for (int iI = 0; iI < MAX_PLAYERS; iI++) {
		if (GET_PLAYER((PlayerTypes)iI).isAlive()) {
			for (int iJ = 0; iJ < MAX_PLAYERS; iJ++) {
				if (GET_PLAYER((PlayerTypes)iJ).isAlive()) {
					if ((GET_PLAYER((PlayerTypes)iI).getTeam() == getID()) && (GET_PLAYER((PlayerTypes)iJ).getTeam() == eTeam)) {
						GET_PLAYER((PlayerTypes)iI).AI_setFirstContact(((PlayerTypes)iJ), true);
						GET_PLAYER((PlayerTypes)iJ).AI_setFirstContact(((PlayerTypes)iI), true);
					}
				}
			}
		}
	}

	for (PlayerTypes i = (PlayerTypes)0; i < MAX_PLAYERS; i = (PlayerTypes)(i + 1)) {
		const CvPlayerAI& kPlayer_i = GET_PLAYER(i);

		if (!kPlayer_i.isAlive() || kPlayer_i.getTeam() != getID())
			continue;
		// player i is a member of this team.

		for (PlayerTypes j = (PlayerTypes)0; j < MAX_PLAYERS; j = (PlayerTypes)(j + 1)) {
			CvPlayerAI& kPlayer_j = GET_PLAYER(j);

			if (!kPlayer_j.isAlive())
				continue;

			if (kPlayer_j.getTeam() == eTeam) {
				kPlayer_j.AI_changeMemoryCount(i, MEMORY_DECLARED_WAR, 1);
			} else if (kPlayer_j.getTeam() != getID()) {
				const CvTeamAI& kTeam_j = GET_TEAM(kPlayer_j.getTeam());

				if (kTeam_j.isHasMet(eTeam) && !kTeam_j.isAtWar(eTeam)) {
					if (kTeam_j.AI_getAttitude(eTeam) >= ATTITUDE_PLEASED) {
						kPlayer_j.AI_changeMemoryCount(i, MEMORY_DECLARED_WAR_ON_FRIEND, 1);
					}
				}
			}
		}
	}
	// K-Mod end.

	for (int iI = 0; iI < MAX_PLAYERS; iI++) {
		if (GET_PLAYER((PlayerTypes)iI).isAlive()) {
			if ((GET_PLAYER((PlayerTypes)iI).getTeam() == getID()) || (GET_PLAYER((PlayerTypes)iI).getTeam() == eTeam)) {
				GET_PLAYER((PlayerTypes)iI).updateWarWearinessPercentAnger();
			}
		}
	}

	for (int iI = 0; iI < MAX_PLAYERS; iI++) {
		if (GET_PLAYER((PlayerTypes)iI).isAlive()) {
			if ((GET_PLAYER((PlayerTypes)iI).getTeam() == getID()) || (GET_PLAYER((PlayerTypes)iI).getTeam() == eTeam)) {
				GET_PLAYER((PlayerTypes)iI).updatePlotGroups();
			}
		}
	}

	for (int iI = 0; iI < MAX_PLAYERS; iI++) {
		if (GET_PLAYER((PlayerTypes)iI).isAlive()) {
			if ((GET_PLAYER((PlayerTypes)iI).getTeam() == getID()) || (GET_PLAYER((PlayerTypes)iI).getTeam() == eTeam)) {
				GET_PLAYER((PlayerTypes)iI).updateTradeRoutes();
			}
		}
	}

	if (GC.getGameINLINE().isFinalInitialized() && !(gDLL->GetWorldBuilderMode())) {
		if (bNewDiplo) {
			if (!isHuman()) {
				for (int iI = 0; iI < MAX_PLAYERS; iI++) {
					if (GET_PLAYER((PlayerTypes)iI).isAlive()) {
						if (GET_PLAYER((PlayerTypes)iI).getTeam() == eTeam) {
							if (GET_PLAYER(getLeaderID()).canContact((PlayerTypes)iI)) {
								if (GET_PLAYER((PlayerTypes)iI).isHuman()) {
									CvDiploParameters* pDiplo = new CvDiploParameters(getLeaderID());
									FAssertMsg(pDiplo != NULL, "pDiplo must be valid");
									pDiplo->setDiploComment((DiploCommentTypes)GC.getInfoTypeForString("AI_DIPLOCOMMENT_DECLARE_WAR"));
									pDiplo->setAIContact(true);
									gDLL->beginDiplomacy(pDiplo, ((PlayerTypes)iI));
								}
							}
						}
					}
				}
			}
		}

		if (!isBarbarian() && !(GET_TEAM(eTeam).isBarbarian()) &&
			!isMinorCiv() && !(GET_TEAM(eTeam).isMinorCiv())) {
			CvWString szBuffer;
			for (int iI = 0; iI < MAX_PLAYERS; iI++) {
				if (GET_PLAYER((PlayerTypes)iI).isAlive()) {
					if (GET_PLAYER((PlayerTypes)iI).getTeam() == getID()) {
						szBuffer = gDLL->getText("TXT_KEY_MISC_YOU_DECLARED_WAR_ON", GET_TEAM(eTeam).getName().GetCString());
						gDLL->getInterfaceIFace()->addHumanMessage(((PlayerTypes)iI), true, GC.getEVENT_MESSAGE_TIME(), szBuffer, "AS2D_DECLAREWAR", MESSAGE_TYPE_MAJOR_EVENT, NULL, (ColorTypes)GC.getInfoTypeForString("COLOR_WARNING_TEXT"));
					} else if (GET_PLAYER((PlayerTypes)iI).getTeam() == eTeam) {
						szBuffer = gDLL->getText("TXT_KEY_MISC_DECLARED_WAR_ON_YOU", getName().GetCString());
						gDLL->getInterfaceIFace()->addHumanMessage(((PlayerTypes)iI), true, GC.getEVENT_MESSAGE_TIME(), szBuffer, "AS2D_DECLAREWAR", MESSAGE_TYPE_MAJOR_EVENT, NULL, (ColorTypes)GC.getInfoTypeForString("COLOR_WARNING_TEXT"));
					} else if (GET_TEAM(GET_PLAYER((PlayerTypes)iI).getTeam()).isHasMet(getID()) && GET_TEAM(GET_PLAYER((PlayerTypes)iI).getTeam()).isHasMet(eTeam)) {
						szBuffer = gDLL->getText("TXT_KEY_MISC_SOMEONE_DECLARED_WAR", getName().GetCString(), GET_TEAM(eTeam).getName().GetCString());
						gDLL->getInterfaceIFace()->addHumanMessage(((PlayerTypes)iI), false, GC.getEVENT_MESSAGE_TIME(), szBuffer, "AS2D_THEIRDECLAREWAR", MESSAGE_TYPE_MAJOR_EVENT, NULL, (ColorTypes)GC.getInfoTypeForString("COLOR_WARNING_TEXT"));
					}
				}
			}

			szBuffer = gDLL->getText("TXT_KEY_MISC_SOMEONE_DECLARES_WAR", getReplayName().GetCString(), GET_TEAM(eTeam).getReplayName().GetCString());
			GC.getGameINLINE().addReplayMessage(REPLAY_MESSAGE_MAJOR_EVENT, getLeaderID(), szBuffer, -1, -1, (ColorTypes)GC.getInfoTypeForString("COLOR_WARNING_TEXT"));
		}
	}

	// K-Mod / BBAI.
	// This section includes some customization options from BBAI.
	// The code has been modified for K-Mod, so that it uses "bPrimaryDoW" rather than the BBAI parameter.
	// The original BtS code has been deleted.
	CvEventReporter::getInstance().changeWar(true, getID(), eTeam);

	if (GC.getBBAI_DEFENSIVE_PACT_BEHAVIOR() <= 1) {
		cancelDefensivePacts();
	}

	for (int iI = 0; iI < MAX_TEAMS; iI++) {
		if (iI != getID() && iI != eTeam && GET_TEAM((TeamTypes)iI).isAlive()) {
			if (GET_TEAM((TeamTypes)iI).isDefensivePact(eTeam)) {
				GET_TEAM((TeamTypes)iI).declareWar(getID(), bNewDiplo, WARPLAN_DOGPILE, false);
			} else if ((GC.getBBAI_DEFENSIVE_PACT_BEHAVIOR() > 1) && GET_TEAM((TeamTypes)iI).isDefensivePact(getID())) {
				// For alliance option.  This teams pacts are canceled above if not using alliance option.
				GET_TEAM((TeamTypes)iI).declareWar(eTeam, bNewDiplo, WARPLAN_DOGPILE, false);
			}
		}
	}

	if (GC.getBBAI_DEFENSIVE_PACT_BEHAVIOR() == 0 || (GC.getBBAI_DEFENSIVE_PACT_BEHAVIOR() == 1 && bPrimaryDoW)) {
		GET_TEAM(eTeam).cancelDefensivePacts();
	}
	// K-Mod / BBAI end.

	for (int iI = 0; iI < MAX_TEAMS; iI++) {
		if (iI != getID() && iI != eTeam) {
			if (GET_TEAM((TeamTypes)iI).isAlive()) {
				if (GET_TEAM((TeamTypes)iI).isVassal(eTeam) || GET_TEAM(eTeam).isVassal((TeamTypes)iI)) {
					declareWar((TeamTypes)iI, bNewDiplo, AI_getWarPlan(eTeam), false);
				} else if (GET_TEAM((TeamTypes)iI).isVassal(getID()) || isVassal((TeamTypes)iI)) {
					GET_TEAM((TeamTypes)iI).declareWar(eTeam, bNewDiplo, WARPLAN_DOGPILE, false);
				}
			}
		}
	}
	// K-Mod. update attitude
	if (bPrimaryDoW) {
		for (PlayerTypes i = (PlayerTypes)0; i < MAX_CIV_PLAYERS; i = (PlayerTypes)(i + 1)) {
			GET_PLAYER(i).AI_updateAttitudeCache();
		}
	}
}

void CvTeam::makePeace(TeamTypes eTeam, bool bBumpUnits) {
	FAssertMsg(eTeam != NO_TEAM, "eTeam is not assigned a valid value");
	FAssertMsg(eTeam != getID(), "eTeam is not expected to be equal with getID()");

	if (isAtWar(eTeam)) {
		if (gTeamLogLevel >= 1) {
			logBBAI("      Team %d (%S) and team %d (%S) make peace", getID(), GET_PLAYER(getLeaderID()).getCivilizationDescription(0), eTeam, GET_PLAYER(GET_TEAM(eTeam).getLeaderID()).getCivilizationDescription(0));
		}

		for (int iI = 0; iI < MAX_PLAYERS; iI++) {
			if ((GET_PLAYER((PlayerTypes)iI).getTeam() == getID()) || (GET_PLAYER((PlayerTypes)iI).getTeam() == eTeam)) {
				GET_PLAYER((PlayerTypes)iI).updatePlunder(-1, false);
			}
		}

		FAssertMsg(eTeam != getID(), "eTeam is not expected to be equal with getID()");
		setAtWar(eTeam, false);
		GET_TEAM(eTeam).setAtWar(getID(), false);

		// Plot danger cache
		GC.getMapINLINE().invalidateBorderDangerCache(eTeam);
		GC.getMapINLINE().invalidateBorderDangerCache(getID());

		for (int iI = 0; iI < MAX_PLAYERS; iI++) {
			if ((GET_PLAYER((PlayerTypes)iI).getTeam() == getID()) || (GET_PLAYER((PlayerTypes)iI).getTeam() == eTeam)) {
				GET_PLAYER((PlayerTypes)iI).updatePlunder(1, false);
			}
		}

		AI_setAtWarCounter(eTeam, 0);
		GET_TEAM(eTeam).AI_setAtWarCounter(getID(), 0);

		AI_setWarSuccess(eTeam, 0);
		GET_TEAM(eTeam).AI_setWarSuccess(getID(), 0);

		AI_setWarPlan(eTeam, NO_WARPLAN);
		GET_TEAM(eTeam).AI_setWarPlan(getID(), NO_WARPLAN);

		if (bBumpUnits) {
			GC.getMapINLINE().verifyUnitValidPlot();
		}

		GC.getGameINLINE().AI_makeAssignWorkDirty();

		if ((getID() == GC.getGameINLINE().getActiveTeam()) || (eTeam == GC.getGameINLINE().getActiveTeam())) {
			gDLL->getInterfaceIFace()->setDirty(Score_DIRTY_BIT, true);
			gDLL->getInterfaceIFace()->setDirty(CityInfo_DIRTY_BIT, true);
		}

		for (int iI = 0; iI < MAX_PLAYERS; iI++) {
			if (GET_PLAYER((PlayerTypes)iI).isAlive()) {
				if ((GET_PLAYER((PlayerTypes)iI).getTeam() == getID()) || (GET_PLAYER((PlayerTypes)iI).getTeam() == eTeam)) {
					GET_PLAYER((PlayerTypes)iI).updateWarWearinessPercentAnger();
				}
			}
		}

		for (int iI = 0; iI < MAX_PLAYERS; iI++) {
			if (GET_PLAYER((PlayerTypes)iI).isAlive()) {
				if ((GET_PLAYER((PlayerTypes)iI).getTeam() == getID()) || (GET_PLAYER((PlayerTypes)iI).getTeam() == eTeam)) {
					GET_PLAYER((PlayerTypes)iI).updatePlotGroups();
				}
			}
		}

		for (int iI = 0; iI < MAX_PLAYERS; iI++) {
			if (GET_PLAYER((PlayerTypes)iI).isAlive()) {
				if ((GET_PLAYER((PlayerTypes)iI).getTeam() == getID()) || (GET_PLAYER((PlayerTypes)iI).getTeam() == eTeam)) {
					GET_PLAYER((PlayerTypes)iI).updateTradeRoutes();
				}
			}
		}

		// K-Mod. update attitude
		if (GC.getGameINLINE().isFinalInitialized()) {
			for (PlayerTypes i = (PlayerTypes)0; i < MAX_CIV_PLAYERS; i = (PlayerTypes)(i + 1)) {
				const CvTeam& kTeam_i = GET_TEAM(GET_PLAYER(i).getTeam());
				if (kTeam_i.getID() == eTeam || kTeam_i.getID() == getID() || kTeam_i.isAtWar(eTeam) || kTeam_i.isAtWar(getID())) {
					for (PlayerTypes j = (PlayerTypes)0; j < MAX_CIV_PLAYERS; j = (PlayerTypes)(j + 1)) {
						TeamTypes eTeam_j = GET_PLAYER(j).getTeam();
						if (eTeam_j == eTeam || eTeam_j == getID()) {
							GET_PLAYER(i).AI_updateAttitudeCache(j);
						}
					}
				}
			}
		}

		CvWString szBuffer;
		for (int iI = 0; iI < MAX_PLAYERS; iI++) {
			if (GET_PLAYER((PlayerTypes)iI).isAlive()) {
				if (GET_PLAYER((PlayerTypes)iI).getTeam() == getID()) {
					szBuffer = gDLL->getText("TXT_KEY_MISC_YOU_MADE_PEACE_WITH", GET_TEAM(eTeam).getName().GetCString());
					gDLL->getInterfaceIFace()->addHumanMessage(((PlayerTypes)iI), true, GC.getEVENT_MESSAGE_TIME(), szBuffer, "AS2D_MAKEPEACE", MESSAGE_TYPE_MAJOR_EVENT, NULL, (ColorTypes)GC.getInfoTypeForString("COLOR_HIGHLIGHT_TEXT"));
				} else if (GET_PLAYER((PlayerTypes)iI).getTeam() == eTeam) {
					szBuffer = gDLL->getText("TXT_KEY_MISC_YOU_MADE_PEACE_WITH", getName().GetCString());
					gDLL->getInterfaceIFace()->addHumanMessage(((PlayerTypes)iI), true, GC.getEVENT_MESSAGE_TIME(), szBuffer, "AS2D_MAKEPEACE", MESSAGE_TYPE_MAJOR_EVENT, NULL, (ColorTypes)GC.getInfoTypeForString("COLOR_HIGHLIGHT_TEXT"));
				} else if (GET_TEAM(GET_PLAYER((PlayerTypes)iI).getTeam()).isHasMet(getID()) && GET_TEAM(GET_PLAYER((PlayerTypes)iI).getTeam()).isHasMet(eTeam)) {
					szBuffer = gDLL->getText("TXT_KEY_MISC_SOMEONE_MADE_PEACE", getName().GetCString(), GET_TEAM(eTeam).getName().GetCString());
					gDLL->getInterfaceIFace()->addHumanMessage(((PlayerTypes)iI), false, GC.getEVENT_MESSAGE_TIME(), szBuffer, "AS2D_THEIRMAKEPEACE", MESSAGE_TYPE_MAJOR_EVENT, NULL, (ColorTypes)GC.getInfoTypeForString("COLOR_HIGHLIGHT_TEXT"));
				}
			}
		}

		szBuffer = gDLL->getText("TXT_KEY_MISC_SOMEONE_MADE_PEACE", getReplayName().GetCString(), GET_TEAM(eTeam).getReplayName().GetCString());
		GC.getGameINLINE().addReplayMessage(REPLAY_MESSAGE_MAJOR_EVENT, getLeaderID(), szBuffer, -1, -1, (ColorTypes)GC.getInfoTypeForString("COLOR_HIGHLIGHT_TEXT"));

		CvEventReporter::getInstance().changeWar(false, getID(), eTeam);

		for (int iI = 0; iI < MAX_TEAMS; iI++) {
			if (iI != getID() && iI != eTeam) {
				if (GET_TEAM((TeamTypes)iI).isAlive()) {
					if (GET_TEAM((TeamTypes)iI).isVassal(eTeam)) {
						GET_TEAM((TeamTypes)iI).makePeace(getID(), bBumpUnits);
					} else if (GET_TEAM((TeamTypes)iI).isVassal(getID())) {
						GET_TEAM((TeamTypes)iI).makePeace(eTeam, bBumpUnits);
					}
				}
			}
		}

	}
}

// K-Mod. I've added bCheckWillingness.
// note. I would have done this the same way in CvPlayer::canContact
// but unfortunately, changing the signiture of that function causes the game to crash - because it's a dll export.
bool CvTeam::canContact(TeamTypes eTeam, bool bCheckWillingness) const {
	for (int iI = 0; iI < MAX_PLAYERS; iI++) {
		if (GET_PLAYER((PlayerTypes)iI).isAlive()) {
			if (GET_PLAYER((PlayerTypes)iI).getTeam() == getID()) {
				for (int iJ = 0; iJ < MAX_PLAYERS; iJ++) {
					if (GET_PLAYER((PlayerTypes)iJ).isAlive()) {
						if (GET_PLAYER((PlayerTypes)iJ).getTeam() == eTeam) {
							if (bCheckWillingness
								? GET_PLAYER((PlayerTypes)iI).canContactAndTalk((PlayerTypes)iJ)
								: GET_PLAYER((PlayerTypes)iI).canContact((PlayerTypes)iJ)) {
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


void CvTeam::meet(TeamTypes eTeam, bool bNewDiplo) {
	if (!isHasMet(eTeam)) {
		makeHasMet(eTeam, bNewDiplo);
		GET_TEAM(eTeam).makeHasMet(getID(), bNewDiplo);

		if (gTeamLogLevel >= 2) {
			if (GC.getGameINLINE().isFinalInitialized()) {
				if (eTeam != getID() && isAlive() && GET_TEAM(eTeam).isAlive()) {
					logBBAI("    Team %d (%S) meets team %d (%S)", getID(), GET_PLAYER(getLeaderID()).getCivilizationDescription(0), eTeam, GET_PLAYER(GET_TEAM(eTeam).getLeaderID()).getCivilizationDescription(0));
				}
			}
		}
	}
}

void CvTeam::signPeaceTreaty(TeamTypes eTeam) {
	TradeData item;
	setTradeItem(&item, TRADE_PEACE_TREATY);

	if (GET_PLAYER(getLeaderID()).canTradeItem(GET_TEAM(eTeam).getLeaderID(), item) && GET_PLAYER(GET_TEAM(eTeam).getLeaderID()).canTradeItem(getLeaderID(), item)) {
		CLinkList<TradeData> ourList;
		CLinkList<TradeData> theirList;

		ourList.insertAtEnd(item);
		theirList.insertAtEnd(item);

		GC.getGameINLINE().implementDeal(getLeaderID(), (GET_TEAM(eTeam).getLeaderID()), &ourList, &theirList);
	}
}

void CvTeam::signOpenBorders(TeamTypes eTeam) {

	FAssert(eTeam != NO_TEAM);
	FAssert(eTeam != getID());

	if (!isAtWar(eTeam) && (getID() != eTeam)) {
		TradeData item;
		setTradeItem(&item, TRADE_OPEN_BORDERS);

		if (GET_PLAYER(getLeaderID()).canTradeItem(GET_TEAM(eTeam).getLeaderID(), item) && GET_PLAYER(GET_TEAM(eTeam).getLeaderID()).canTradeItem(getLeaderID(), item)) {
			CLinkList<TradeData> ourList;
			CLinkList<TradeData> theirList;
			ourList.clear();
			theirList.clear();

			ourList.insertAtEnd(item);
			theirList.insertAtEnd(item);

			GC.getGameINLINE().implementDeal(getLeaderID(), (GET_TEAM(eTeam).getLeaderID()), &ourList, &theirList);
		}
	}
}


void CvTeam::signDefensivePact(TeamTypes eTeam) {
	FAssert(eTeam != NO_TEAM);
	FAssert(eTeam != getID());

	if (!isAtWar(eTeam) && (getID() != eTeam)) {
		TradeData item;
		setTradeItem(&item, TRADE_DEFENSIVE_PACT);

		if (GET_PLAYER(getLeaderID()).canTradeItem(GET_TEAM(eTeam).getLeaderID(), item) && GET_PLAYER(GET_TEAM(eTeam).getLeaderID()).canTradeItem(getLeaderID(), item)) {
			CLinkList<TradeData> ourList;
			CLinkList<TradeData> theirList;
			ourList.clear();
			theirList.clear();

			ourList.insertAtEnd(item);
			theirList.insertAtEnd(item);

			GC.getGameINLINE().implementDeal(getLeaderID(), (GET_TEAM(eTeam).getLeaderID()), &ourList, &theirList);
		}
	}
}

bool CvTeam::canSignDefensivePact(TeamTypes eTeam) const {
	if (!isHasEmbassy(eTeam)) {
		return false;
	}

	for (TeamTypes eLoopTeam = (TeamTypes)0; eLoopTeam < MAX_CIV_TEAMS; eLoopTeam = (TeamTypes)(eLoopTeam + 1)) {
		if (eLoopTeam != getID() && eLoopTeam != eTeam) {
			CvTeam& kLoopTeam = GET_TEAM(eLoopTeam);
			if (kLoopTeam.isPermanentWarPeace(eTeam) != kLoopTeam.isPermanentWarPeace(getID())) {
				return false;
			}

			if (isPermanentWarPeace(eLoopTeam) != GET_TEAM(eTeam).isPermanentWarPeace(eLoopTeam)) {
				return false;
			}
		}
	}

	return true;
}


int CvTeam::getAssets() const {
	int iCount = 0;

	for (int iI = 0; iI < MAX_PLAYERS; iI++) {
		if (GET_PLAYER((PlayerTypes)iI).isAlive()) {
			if (GET_PLAYER((PlayerTypes)iI).getTeam() == getID()) {
				iCount += GET_PLAYER((PlayerTypes)iI).getAssets();
			}
		}
	}

	return iCount;
}


int CvTeam::getPower(bool bIncludeVassals) const {
	int iCount = 0;

	for (int iI = 0; iI < MAX_PLAYERS; iI++) {
		CvPlayer& kLoopPlayer = GET_PLAYER((PlayerTypes)iI);
		if (kLoopPlayer.isAlive()) {
			if (kLoopPlayer.getTeam() == getID() || (bIncludeVassals && GET_TEAM(kLoopPlayer.getTeam()).isVassal(getID()))) {
				iCount += kLoopPlayer.getPower();
			}
		}
	}

	return iCount;
}


int CvTeam::getDefensivePower(TeamTypes eExcludeTeam) const {
	int iCount = 0;

	FAssert(eExcludeTeam != getID());

	const CvTeam& kMasterTeam = GET_TEAM(getMasterTeam()); // K-Mod. only our master will have defensive pacts.

	for (int iI = 0; iI < MAX_CIV_TEAMS; iI++) {
		const CvTeam& kLoopTeam = GET_TEAM((TeamTypes)iI);
		//if (kLoopTeam.isAlive() && !kLoopTeam.isAVassal())
		if (kLoopTeam.isAlive()) // K-Mod. (vassal check unnecessary, b/c vassals can't be the master team, and they can't have a pact.)
		{
			//if (getID() == iI || isVassal((TeamTypes)iI) || isDefensivePact((TeamTypes)iI))
			if (iI != eExcludeTeam && (kMasterTeam.getID() == iI || kMasterTeam.isDefensivePact((TeamTypes)iI))) // K-Mod
			{
				iCount += kLoopTeam.getPower(true);
			}
		}
	}

	return iCount;
}


int CvTeam::getEnemyPower() const {
	int iCount = 0;

	for (int iI = 0; iI < MAX_CIV_TEAMS; iI++) {
		CvTeam& kLoopTeam = GET_TEAM((TeamTypes)iI);
		if (kLoopTeam.isAlive()) {
			if (getID() != iI && isAtWar((TeamTypes)iI)) {
				iCount += kLoopTeam.getPower(false);
			}
		}
	}

	return iCount;
}


int CvTeam::getNumNukeUnits() const {
	int iCount = 0;

	for (int iI = 0; iI < MAX_PLAYERS; iI++) {
		CvPlayer& kLoopPlayer = GET_PLAYER((PlayerTypes)iI);
		if (kLoopPlayer.isAlive()) {
			if (kLoopPlayer.getTeam() == getID() || GET_TEAM(kLoopPlayer.getTeam()).isVassal(getID())) {
				iCount += kLoopPlayer.getNumNukeUnits();
			}
		}
	}

	return iCount;
}

int CvTeam::getVotes(VoteTypes eVote, VoteSourceTypes eVoteSource) const {
	int iCount = 0;

	for (int iI = 0; iI < MAX_PLAYERS; iI++) {
		CvPlayer& kLoopPlayer = GET_PLAYER((PlayerTypes)iI);
		if (kLoopPlayer.getTeam() == getID()) {
			iCount += kLoopPlayer.getVotes(eVote, eVoteSource);
		}
	}

	return iCount;
}

bool CvTeam::isVotingMember(VoteSourceTypes eVoteSource) const {
	return (getVotes(NO_VOTE, eVoteSource) > 0);
}

bool CvTeam::isFullMember(VoteSourceTypes eVoteSource) const {
	if (isForceTeamVoteEligible(eVoteSource)) {
		return true;
	}

	for (int iI = 0; iI < MAX_PLAYERS; iI++) {
		CvPlayer& kLoopPlayer = GET_PLAYER((PlayerTypes)iI);
		if (kLoopPlayer.getTeam() == getID()) {
			if (!kLoopPlayer.isFullMember(eVoteSource)) {
				return false;
			}
		}
	}

	return true;
}

int CvTeam::getAtWarCount(bool bIgnoreMinors, bool bIgnoreVassals) const {
	int iCount = 0;

	for (int iI = 0; iI < MAX_CIV_TEAMS; iI++) {
		if (GET_TEAM((TeamTypes)iI).isAlive()) {
			if (!bIgnoreMinors || !(GET_TEAM((TeamTypes)iI).isMinorCiv())) {
				if (!bIgnoreVassals || !(GET_TEAM((TeamTypes)iI).isAVassal())) {
					if (isAtWar((TeamTypes)iI)) {
						FAssert(iI != getID());
						FAssert(!(AI_isSneakAttackPreparing((TeamTypes)iI))); // K-Mod note. This assert can fail when in the process of declaring war
						iCount++;
					}
				}
			}
		}
	}

	return iCount;
}

int CvTeam::getWarPlanCount(WarPlanTypes eWarPlan, bool bIgnoreMinors) const {
	int iCount = 0;

	for (int iI = 0; iI < MAX_CIV_TEAMS; iI++) {
		if (GET_TEAM((TeamTypes)iI).isAlive()) {
			if (!bIgnoreMinors || !(GET_TEAM((TeamTypes)iI).isMinorCiv())) {
				if (AI_getWarPlan((TeamTypes)iI) == eWarPlan) {
					FAssert(iI != getID());
					iCount++;
				}
			}
		}
	}

	return iCount;
}


int CvTeam::getAnyWarPlanCount(bool bIgnoreMinors) const {
	int iCount = 0;

	for (int iI = 0; iI < MAX_CIV_TEAMS; iI++) {
		if (GET_TEAM((TeamTypes)iI).isAlive()) {
			if (!bIgnoreMinors || !(GET_TEAM((TeamTypes)iI).isMinorCiv())) {
				if (AI_getWarPlan((TeamTypes)iI) != NO_WARPLAN) {
					FAssert(iI != getID());
					iCount++;
				}
			}
		}
	}

	FAssert(iCount >= getAtWarCount(bIgnoreMinors));

	return iCount;
}


int CvTeam::getChosenWarCount(bool bIgnoreMinors) const {
	int iCount = 0;

	for (int iI = 0; iI < MAX_CIV_TEAMS; iI++) {
		if (GET_TEAM((TeamTypes)iI).isAlive()) {
			if (!bIgnoreMinors || !(GET_TEAM((TeamTypes)iI).isMinorCiv())) {
				if (AI_isChosenWar((TeamTypes)iI)) {
					FAssert(iI != getID());
					iCount++;
				}
			}
		}
	}

	return iCount;
}


int CvTeam::getHasMetCivCount(bool bIgnoreMinors) const {
	int iCount = 0;

	for (int iI = 0; iI < MAX_CIV_TEAMS; iI++) {
		if (GET_TEAM((TeamTypes)iI).isAlive()) {
			if (iI != getID()) {
				if (!bIgnoreMinors || !(GET_TEAM((TeamTypes)iI).isMinorCiv())) {
					if (isHasMet((TeamTypes)iI)) {
						FAssert(iI != getID());
						iCount++;
					}
				}
			}
		}
	}

	return iCount;
}


bool CvTeam::hasMetHuman() const {
	for (int iI = 0; iI < MAX_CIV_TEAMS; iI++) {
		if (GET_TEAM((TeamTypes)iI).isAlive()) {
			if (iI != getID()) {
				if (GET_TEAM((TeamTypes)iI).isHuman()) {
					if (isHasMet((TeamTypes)iI)) {
						FAssert(iI != getID());
						return true;
					}
				}
			}
		}
	}

	return false;
}


int CvTeam::getDefensivePactCount(TeamTypes eTeam) const {
	int iCount = 0;

	for (int iI = 0; iI < MAX_CIV_TEAMS; iI++) {
		if (GET_TEAM((TeamTypes)iI).isAlive()) {
			if (iI != getID()) {
				if (isDefensivePact((TeamTypes)iI)) {
					if (NO_TEAM == eTeam || GET_TEAM(eTeam).isHasMet((TeamTypes)iI)) {
						iCount++;
					}
				}
			}
		}
	}

	return iCount;
}

int CvTeam::getVassalCount(TeamTypes eTeam) const {
	int iCount = 0;

	for (int iI = 0; iI < MAX_CIV_TEAMS; iI++) {
		CvTeam& kLoopTeam = GET_TEAM((TeamTypes)iI);
		if (kLoopTeam.isAlive()) {
			if (iI != getID()) {
				if (kLoopTeam.isVassal(getID())) {
					if (NO_TEAM == eTeam || GET_TEAM(eTeam).isHasMet((TeamTypes)iI)) {
						iCount++;
					}
				}
			}
		}
	}

	return iCount;
}

bool CvTeam::isAVassal() const {
	PROFILE_FUNC();

	for (int iI = 0; iI < MAX_CIV_TEAMS; iI++) {
		if (GET_TEAM((TeamTypes)iI).isAlive()) {
			if (iI != getID()) {
				if (isVassal((TeamTypes)iI)) {
					FAssert(iI != getID());
					return true;
				}
			}
		}
	}

	return false;
}

bool CvTeam::canVassalRevolt(TeamTypes eMaster) const {
	FAssert(NO_TEAM != eMaster);

	CvTeam& kMaster = GET_TEAM(eMaster);

	if (isVassal(eMaster)) {
		if (100 * getTotalLand(false) < GC.getDefineINT("VASSAL_REVOLT_OWN_LOSSES_FACTOR") * getVassalPower()) {
			return true;
		}

		if (100 * kMaster.getTotalLand() < GC.getDefineINT("VASSAL_REVOLT_MASTER_LOSSES_FACTOR") * getMasterPower()) {
			return true;
		}
	}

	if (GC.getDefineINT("FREE_VASSAL_LAND_PERCENT") < 0 ||
		100 * getTotalLand(false) < kMaster.getTotalLand(false) * GC.getDefineINT("FREE_VASSAL_LAND_PERCENT")) {
		return false;
	}

	if (GC.getDefineINT("FREE_VASSAL_POPULATION_PERCENT") < 0 ||
		100 * getTotalPopulation(false) < kMaster.getTotalPopulation(false) * GC.getDefineINT("FREE_VASSAL_POPULATION_PERCENT")) {
		return false;
	}

	return true;
}

bool CvTeam::isMasterPlanningLandWar(CvArea* pArea) {
	if (!isAVassal()) {
		return false;
	}

	if ((pArea->getAreaAIType(getID()) == AREAAI_OFFENSIVE) || (pArea->getAreaAIType(getID()) == AREAAI_DEFENSIVE) || (pArea->getAreaAIType(getID()) == AREAAI_MASSING)) {
		return true;
	}

	for (int iI = 0; iI < MAX_CIV_TEAMS; iI++) {
		if (isVassal((TeamTypes)iI)) {
			if (GET_TEAM((TeamTypes)iI).getAnyWarPlanCount(true) > 0) {
				if ((pArea->getAreaAIType((TeamTypes)iI) == AREAAI_OFFENSIVE) || (pArea->getAreaAIType((TeamTypes)iI) == AREAAI_DEFENSIVE) || (pArea->getAreaAIType((TeamTypes)iI) == AREAAI_MASSING)) {
					return true;
				} else if (pArea->getAreaAIType((TeamTypes)iI) == AREAAI_NEUTRAL) {
					// Master has no presence here
					if ((pArea->getNumCities() - countNumCitiesByArea(pArea)) > 2) {
						return (GC.getGameINLINE().getSorenRandNum((isCapitulated() ? 6 : 4), "Vassal land war") == 0);
					}
				}
			} else if (GET_TEAM((TeamTypes)iI).isHuman()) {
				if (GC.getBBAI_HUMAN_VASSAL_WAR_BUILD()) {
					if ((pArea->getNumCities() - countNumCitiesByArea(pArea) - GET_TEAM((TeamTypes)iI).countNumCitiesByArea(pArea)) > 2) {
						return (GC.getGameINLINE().getSorenRandNum(4, "Vassal land war") == 0);
					}
				}
			}

			break;
		}
	}

	return false;
}

bool CvTeam::isMasterPlanningSeaWar(CvArea* pArea) {
	if (!isAVassal()) {
		return false;
	}

	if ((pArea->getAreaAIType(getID()) == AREAAI_ASSAULT) || (pArea->getAreaAIType(getID()) == AREAAI_ASSAULT_ASSIST) || (pArea->getAreaAIType(getID()) == AREAAI_ASSAULT_MASSING)) {
		return true;
	}

	for (int iI = 0; iI < MAX_CIV_TEAMS; iI++) {
		if (isVassal((TeamTypes)iI)) {
			if (GET_TEAM((TeamTypes)iI).getAnyWarPlanCount(true) > 0) {
				if ((pArea->getAreaAIType((TeamTypes)iI) == AREAAI_ASSAULT) || (pArea->getAreaAIType((TeamTypes)iI) == AREAAI_ASSAULT_ASSIST) || (pArea->getAreaAIType((TeamTypes)iI) == AREAAI_ASSAULT_MASSING)) {
					return (GC.getGameINLINE().getSorenRandNum((isCapitulated() ? 3 : 2), "Vassal sea war") == 0);
				} else if (pArea->getAreaAIType((TeamTypes)iI) == AREAAI_NEUTRAL) {
					// Master has no presence here
					return false;
				}

			} else if (GET_TEAM((TeamTypes)iI).isHuman()) {
				return false;
			}

			break;
		}
	}

	return false;
}

int CvTeam::getUnitClassMaking(UnitClassTypes eUnitClass) const {
	int iCount = 0;

	for (int iI = 0; iI < MAX_PLAYERS; iI++) {
		if (GET_PLAYER((PlayerTypes)iI).isAlive()) {
			if (GET_PLAYER((PlayerTypes)iI).getTeam() == getID()) {
				iCount += GET_PLAYER((PlayerTypes)iI).getUnitClassMaking(eUnitClass);
			}
		}
	}

	return iCount;
}


int CvTeam::getUnitClassCountPlusMaking(UnitClassTypes eIndex) const {
	return (getUnitClassCount(eIndex) + getUnitClassMaking(eIndex));
}


int CvTeam::getBuildingClassMaking(BuildingClassTypes eBuildingClass) const {
	int iCount = 0;

	for (int iI = 0; iI < MAX_PLAYERS; iI++) {
		if (GET_PLAYER((PlayerTypes)iI).isAlive()) {
			if (GET_PLAYER((PlayerTypes)iI).getTeam() == getID()) {
				iCount += GET_PLAYER((PlayerTypes)iI).getBuildingClassMaking(eBuildingClass);
			}
		}
	}

	return iCount;
}


int CvTeam::getBuildingClassCountPlusMaking(BuildingClassTypes eIndex) const {
	return (getBuildingClassCount(eIndex) + getBuildingClassMaking(eIndex));
}


int CvTeam::getHasReligionCount(ReligionTypes eReligion) const {
	int iCount = 0;

	for (int iI = 0; iI < MAX_PLAYERS; iI++) {
		if (GET_PLAYER((PlayerTypes)iI).isAlive()) {
			if (GET_PLAYER((PlayerTypes)iI).getTeam() == getID()) {
				iCount += GET_PLAYER((PlayerTypes)iI).getHasReligionCount(eReligion);
			}
		}
	}

	return iCount;
}


int CvTeam::getHasCorporationCount(CorporationTypes eCorporation) const {
	int iCount = 0;

	for (int iI = 0; iI < MAX_PLAYERS; iI++) {
		if (GET_PLAYER((PlayerTypes)iI).isAlive()) {
			if (GET_PLAYER((PlayerTypes)iI).getTeam() == getID()) {
				iCount += GET_PLAYER((PlayerTypes)iI).getHasCorporationCount(eCorporation);
			}
		}
	}

	return iCount;
}


int CvTeam::countTotalCulture() const {
	int iCount = 0;

	for (int iI = 0; iI < MAX_PLAYERS; iI++) {
		if (GET_PLAYER((PlayerTypes)iI).isAlive()) {
			if (GET_PLAYER((PlayerTypes)iI).getTeam() == getID()) {
				iCount += GET_PLAYER((PlayerTypes)iI).countTotalCulture();
			}
		}
	}

	return iCount;
}


int CvTeam::countNumUnitsByArea(CvArea* pArea) const {
	PROFILE_FUNC();

	int iCount = 0;

	for (int iI = 0; iI < MAX_PLAYERS; iI++) {
		if (GET_PLAYER((PlayerTypes)iI).isAlive()) {
			if (GET_PLAYER((PlayerTypes)iI).getTeam() == getID()) {
				iCount += pArea->getUnitsPerPlayer((PlayerTypes)iI);
			}
		}
	}

	return iCount;
}


int CvTeam::countNumCitiesByArea(CvArea* pArea) const {
	PROFILE_FUNC();

	int iCount = 0;

	for (int iI = 0; iI < MAX_PLAYERS; iI++) {
		if (GET_PLAYER((PlayerTypes)iI).isAlive()) {
			if (GET_PLAYER((PlayerTypes)iI).getTeam() == getID()) {
				iCount += pArea->getCitiesPerPlayer((PlayerTypes)iI);
			}
		}
	}

	return iCount;
}


int CvTeam::countTotalPopulationByArea(CvArea* pArea) const {
	int iCount = 0;

	for (int iI = 0; iI < MAX_PLAYERS; iI++) {
		if (GET_PLAYER((PlayerTypes)iI).isAlive()) {
			if (GET_PLAYER((PlayerTypes)iI).getTeam() == getID()) {
				iCount += pArea->getPopulationPerPlayer((PlayerTypes)iI);
			}
		}
	}

	return iCount;
}


int CvTeam::countPowerByArea(CvArea* pArea) const {
	int iCount = 0;

	for (int iI = 0; iI < MAX_PLAYERS; iI++) {
		if (GET_PLAYER((PlayerTypes)iI).isAlive()) {
			if (GET_PLAYER((PlayerTypes)iI).getTeam() == getID()) {
				iCount += pArea->getPower((PlayerTypes)iI);
			}
		}
	}

	return iCount;
}


int CvTeam::countEnemyPowerByArea(CvArea* pArea) const {
	int iCount = 0;

	for (int iI = 0; iI < MAX_PLAYERS; iI++) {
		if (GET_PLAYER((PlayerTypes)iI).isAlive()) {
			if (GET_PLAYER((PlayerTypes)iI).getTeam() != getID()) {
				// Count planned wars as well
				if (AI_getWarPlan(GET_PLAYER((PlayerTypes)iI).getTeam()) != NO_WARPLAN) {
					iCount += pArea->getPower((PlayerTypes)iI);
				}
			}
		}
	}

	return iCount;
}

// K-Mod
// Note: this includes barbarian cities.
int CvTeam::countEnemyCitiesByArea(CvArea* pArea) const {
	int iCount = 0;
	for (PlayerTypes i = (PlayerTypes)0; i < MAX_PLAYERS; i = (PlayerTypes)(i + 1)) {
		const CvPlayer& kLoopPlayer = GET_PLAYER(i);
		if (kLoopPlayer.isAlive() && AI_getWarPlan(kLoopPlayer.getTeam()) != NO_WARPLAN)
			iCount += pArea->getCitiesPerPlayer(i);
	}
	return 0;
}

int CvTeam::countEnemyPopulationByArea(CvArea* pArea) const {
	int iCount = 0;

	for (int iI = 0; iI < MAX_PLAYERS; iI++) {
		if (GET_PLAYER((PlayerTypes)iI).isAlive()) {
			if (GET_PLAYER((PlayerTypes)iI).getTeam() != getID()) {
				if (AI_getWarPlan(GET_PLAYER((PlayerTypes)iI).getTeam()) != NO_WARPLAN) {
					iCount += pArea->getPopulationPerPlayer((PlayerTypes)iI);
				}
			}
		}
	}

	return iCount;
}


int CvTeam::countNumAIUnitsByArea(CvArea* pArea, UnitAITypes eUnitAI) const {
	PROFILE_FUNC();

	int iCount = 0;

	for (int iI = 0; iI < MAX_PLAYERS; iI++) {
		if (GET_PLAYER((PlayerTypes)iI).isAlive()) {
			if (GET_PLAYER((PlayerTypes)iI).getTeam() == getID()) {
				iCount += pArea->getNumAIUnits(((PlayerTypes)iI), eUnitAI);
			}
		}
	}

	return iCount;
}

int CvTeam::countEnemyDangerByArea(CvArea* pArea, TeamTypes eEnemyTeam) const {
	PROFILE_FUNC();

	int iCount = 0;

	for (int iI = 0; iI < GC.getMapINLINE().numPlotsINLINE(); iI++) {
		CvPlot* pLoopPlot = GC.getMapINLINE().plotByIndexINLINE(iI);

		if (pLoopPlot != NULL) {
			if (pLoopPlot->area() == pArea) {
				if (pLoopPlot->getTeam() == getID()) {
					iCount += pLoopPlot->plotCount(PUF_canDefendEnemy, getLeaderID(), false, NO_PLAYER, eEnemyTeam, PUF_isVisible, getLeaderID());
				}
			}
		}
	}

	return iCount;
}
// K-Mod
int CvTeam::getTypicalUnitValue(UnitAITypes eUnitAI, DomainTypes eDomain) const {
	int iMax = 0;
	for (int iI = 0; iI < MAX_PLAYERS; ++iI) {
		if (GET_PLAYER((PlayerTypes)iI).getTeam() == getID()) {
			iMax = std::max(iMax, GET_PLAYER((PlayerTypes)iI).getTypicalUnitValue(eUnitAI, eDomain));
		}
	}
	return iMax;
}

int CvTeam::getResearchCost(TechTypes eTech, bool bGlobalModifiers, bool bTeamSizeModifiers) const // K-Mod added bGlobalModifiers & bTeamSizeModifiers
{
	FAssertMsg(eTech != NO_TECH, "Tech is not assigned a valid value");

	int iCost = GC.getTechInfo(eTech).getResearchCost();

	iCost *= GC.getHandicapInfo(getHandicapType()).getResearchPercent();
	iCost /= 100;

	if (bGlobalModifiers) // K-Mod
	{
		iCost *= GC.getWorldInfo(GC.getMapINLINE().getWorldSize()).getResearchPercent();
		iCost /= 100;

		iCost *= GC.getGameSpeedInfo(GC.getGameINLINE().getGameSpeedType()).getResearchPercent();
		iCost /= 100;

		iCost *= GC.getEraInfo(GC.getGameINLINE().getStartEra()).getResearchPercent();
		iCost /= 100;
	}

	if (bTeamSizeModifiers) // K-Mod
	{
		iCost *= std::max(0, ((GC.getDefineINT("TECH_COST_EXTRA_TEAM_MEMBER_MODIFIER") * (getNumMembers() - 1)) + 100));
		iCost /= 100;
	}


	return std::max(1, iCost);
}


int CvTeam::getResearchLeft(TechTypes eTech) const {
	return std::max(0, (getResearchCost(eTech) - getResearchProgress(eTech)));
}


bool CvTeam::hasHolyCity(ReligionTypes eReligion) const {
	FAssertMsg(eReligion != NO_RELIGION, "Religion is not assigned a valid value");

	CvCity* pHolyCity = GC.getGameINLINE().getHolyCity(eReligion);

	if (pHolyCity != NULL) {
		return (pHolyCity->getTeam() == getID());
	}

	return false;
}


bool CvTeam::hasHeadquarters(CorporationTypes eCorporation) const {
	FAssertMsg(eCorporation != NO_CORPORATION, "Corporation is not assigned a valid value");

	CvCity* pHeadquarters = GC.getGameINLINE().getHeadquarters(eCorporation);

	if (pHeadquarters != NULL) {
		return (pHeadquarters->getTeam() == getID());
	}

	return false;
}

bool CvTeam::hasBonus(BonusTypes eBonus) const {
	for (int iI = 0; iI < MAX_PLAYERS; ++iI) {
		if (GET_PLAYER((PlayerTypes)iI).getTeam() == getID()) {
			if (GET_PLAYER((PlayerTypes)iI).hasBonus(eBonus)) {
				return true;
			}
		}
	}

	return false;
}

bool CvTeam::isBonusObsolete(BonusTypes eBonus) const {
	TechTypes eObsoleteTech = (TechTypes)GC.getBonusInfo(eBonus).getTechObsolete();
	if (eObsoleteTech != NO_TECH && isHasTech(eObsoleteTech)) {
		return true;
	}
	return false;
}


bool CvTeam::isHuman() const {
	PROFILE_FUNC();

	for (int iI = 0; iI < MAX_PLAYERS; iI++) {
		if (GET_PLAYER((PlayerTypes)iI).getTeam() == getID()) {
			if (GET_PLAYER((PlayerTypes)iI).isHuman()) {
				return true;
			}
		}
	}

	return false;
}


bool CvTeam::isBarbarian() const {
	return (getID() == BARBARIAN_TEAM);
}


bool CvTeam::isMinorCiv() const {
	bool bValid = false;

	for (int iI = 0; iI < MAX_PLAYERS; iI++) {
		if (GET_PLAYER((PlayerTypes)iI).getTeam() == getID()) {
			if (GET_PLAYER((PlayerTypes)iI).isMinorCiv()) {
				bValid = true;
			} else {
				return false;
			}
		}
	}

	return bValid;
}


PlayerTypes CvTeam::getLeaderID() const {
	for (int iI = 0; iI < MAX_PLAYERS; iI++) {
		if (GET_PLAYER((PlayerTypes)iI).isAlive()) {
			if (GET_PLAYER((PlayerTypes)iI).getTeam() == getID()) {
				return ((PlayerTypes)iI);
			}
		}
	}

	for (int iI = 0; iI < MAX_PLAYERS; iI++) {
		if (GET_PLAYER((PlayerTypes)iI).getTeam() == getID()) {
			return ((PlayerTypes)iI);
		}
	}

	return NO_PLAYER;
}


PlayerTypes CvTeam::getSecretaryID() const {
	for (int iI = 0; iI < MAX_PLAYERS; iI++) {
		if (GET_PLAYER((PlayerTypes)iI).isAlive()) {
			if (GET_PLAYER((PlayerTypes)iI).isHuman()) {
				if (GET_PLAYER((PlayerTypes)iI).getTeam() == getID()) {
					return ((PlayerTypes)iI);
				}
			}
		}
	}

	return getLeaderID();
}


HandicapTypes CvTeam::getHandicapType() const {
	int iGameHandicap = 0;
	int iCount = 0;

	for (int iI = 0; iI < MAX_PLAYERS; iI++) {
		if (GET_PLAYER((PlayerTypes)iI).isAlive()) {
			if (GET_PLAYER((PlayerTypes)iI).getTeam() == getID()) {
				iGameHandicap += GET_PLAYER((PlayerTypes)iI).getHandicapType();
				iCount++;
			}
		}
	}

	if (iCount > 0) {
		FAssertMsg((iGameHandicap / iCount) >= 0, "(iGameHandicap / iCount) is expected to be non-negative (invalid Index)");
		return ((HandicapTypes)(iGameHandicap / iCount));
	} else {
		return ((HandicapTypes)(GC.getDefineINT("STANDARD_HANDICAP")));
	}
}


CvWString CvTeam::getName() const {
	CvWString szBuffer;
	bool bFirst = true;

	for (int iI = 0; iI < MAX_PLAYERS; iI++) {
		if (GET_PLAYER((PlayerTypes)iI).isAlive() && GET_PLAYER((PlayerTypes)iI).getTeam() == getID()) {
			setListHelp(szBuffer, L"", GET_PLAYER((PlayerTypes)iI).getName(), L"/", bFirst);
			bFirst = false;
		}
	}

	return szBuffer;
}

// K-Mod. Name to be used in replay
CvWString CvTeam::getReplayName() const {
	CvWString szBuffer;

	bool bFirst = true;

	for (int iI = 0; iI < MAX_PLAYERS; iI++) {
		if (GET_PLAYER((PlayerTypes)iI).isAlive() && GET_PLAYER((PlayerTypes)iI).getTeam() == getID()) {
			setListHelp(szBuffer, L"", GET_PLAYER((PlayerTypes)iI).getReplayName(), L"/", bFirst);
			bFirst = false;
		}
	}

	return szBuffer;
}

int CvTeam::getNumMembers() const {
	return m_iNumMembers;
}


void CvTeam::changeNumMembers(int iChange) {
	m_iNumMembers += iChange;
	FAssert(getNumMembers() >= 0);
}


int CvTeam::getAliveCount() const {
	return m_iAliveCount;
}


int CvTeam::isAlive() const {
	return (getAliveCount() > 0);
}


void CvTeam::changeAliveCount(int iChange) {
	m_iAliveCount += iChange;
	FAssert(getAliveCount() >= 0);

	// free vassals
	if (0 == m_iAliveCount) {
		for (int iTeam = 0; iTeam < MAX_TEAMS; iTeam++) {
			if (iTeam != getID()) {
				CvTeam& kLoopTeam = GET_TEAM((TeamTypes)iTeam);
				if (kLoopTeam.isAlive() && kLoopTeam.isVassal(getID())) {
					kLoopTeam.setVassal(getID(), false, false);
				}
			}
		}
	}
}


int CvTeam::getEverAliveCount() const {
	return m_iEverAliveCount;
}


int CvTeam::isEverAlive() const {
	return (getEverAliveCount() > 0);
}


void CvTeam::changeEverAliveCount(int iChange) {
	m_iEverAliveCount += iChange;
	FAssert(getEverAliveCount() >= 0);
}


int CvTeam::getNumCities() const {
	return m_iNumCities;
}


void CvTeam::changeNumCities(int iChange) {
	m_iNumCities += iChange;
	FAssert(getNumCities() >= 0);
}


int CvTeam::getTotalPopulation(bool bCheckVassals) const {
	int iVassalPop = 0;

	if (bCheckVassals) {
		if (isAVassal()) {
			return m_iTotalPopulation / 2;
		}

		for (int iI = 0; iI < MAX_TEAMS; iI++) {
			if ((TeamTypes)iI != getID()) {
				CvTeam& kLoopTeam = GET_TEAM((TeamTypes)iI);
				if (kLoopTeam.isAlive() && kLoopTeam.isVassal(getID())) {
					iVassalPop += kLoopTeam.getTotalPopulation(false) / 2;
				}
			}
		}
	}

	return (m_iTotalPopulation + iVassalPop);
}


void CvTeam::changeTotalPopulation(int iChange) {
	m_iTotalPopulation += iChange;
	FAssert(getTotalPopulation() >= 0);
}


int CvTeam::getTotalLand(bool bCheckVassals) const {
	int iVassalLand = 0;

	if (bCheckVassals) {
		if (isAVassal()) {
			return m_iTotalLand / 2;
		}

		for (int iI = 0; iI < MAX_TEAMS; iI++) {
			if ((TeamTypes)iI != getID()) {
				CvTeam& kLoopTeam = GET_TEAM((TeamTypes)iI);
				if (kLoopTeam.isAlive() && kLoopTeam.isVassal(getID())) {
					iVassalLand += kLoopTeam.getTotalLand(false) / 2;
				}
			}
		}
	}

	return (m_iTotalLand + iVassalLand);
}


void CvTeam::changeTotalLand(int iChange) {
	m_iTotalLand += iChange;
	FAssert(getTotalLand() >= 0);
}


int CvTeam::getNukeInterception() const {
	return m_iNukeInterception;
}


void CvTeam::changeNukeInterception(int iChange) {
	m_iNukeInterception += iChange;
	FAssert(getNukeInterception() >= 0);
}


int CvTeam::getForceTeamVoteEligibilityCount(VoteSourceTypes eVoteSource) const {
	return m_aiForceTeamVoteEligibilityCount[eVoteSource];
}


bool CvTeam::isForceTeamVoteEligible(VoteSourceTypes eVoteSource) const {
	return ((getForceTeamVoteEligibilityCount(eVoteSource) > 0) && !isMinorCiv());
}


void CvTeam::changeForceTeamVoteEligibilityCount(VoteSourceTypes eVoteSource, int iChange) {
	m_aiForceTeamVoteEligibilityCount[eVoteSource] += iChange;
	FAssert(getForceTeamVoteEligibilityCount(eVoteSource) >= 0);
}


int CvTeam::getExtraWaterSeeFromCount() const {
	return m_iExtraWaterSeeFromCount;
}


bool CvTeam::isExtraWaterSeeFrom() const {
	return (getExtraWaterSeeFromCount() > 0);
}


void CvTeam::changeExtraWaterSeeFromCount(int iChange) {
	if (iChange != 0) {
		GC.getMapINLINE().updateSight(false);

		m_iExtraWaterSeeFromCount = (m_iExtraWaterSeeFromCount + iChange);
		FAssert(getExtraWaterSeeFromCount() >= 0);

		GC.getMapINLINE().updateSight(true);
	}
}


int CvTeam::getMapTradingCount() const {
	return m_iMapTradingCount;
}


bool CvTeam::isMapTrading()	const {
	return (getMapTradingCount() > 0);
}


void CvTeam::changeMapTradingCount(int iChange) {
	m_iMapTradingCount = (m_iMapTradingCount + iChange);
	FAssert(getMapTradingCount() >= 0);
}


int CvTeam::getTechTradingCount() const {
	return m_iTechTradingCount;
}


bool CvTeam::isTechTrading() const {
	return (getTechTradingCount() > 0);
}


void CvTeam::changeTechTradingCount(int iChange) {
	m_iTechTradingCount = (m_iTechTradingCount + iChange);
	FAssert(getTechTradingCount() >= 0);
}


int CvTeam::getGoldTradingCount() const {
	return m_iGoldTradingCount;
}


bool CvTeam::isGoldTrading() const {
	return (getGoldTradingCount() > 0);
}


void CvTeam::changeGoldTradingCount(int iChange) {
	m_iGoldTradingCount = (m_iGoldTradingCount + iChange);
	FAssert(getGoldTradingCount() >= 0);
}


int CvTeam::getOpenBordersTradingCount() const {
	return m_iOpenBordersTradingCount;
}


bool CvTeam::isOpenBordersTrading() const {
	return (getOpenBordersTradingCount() > 0);
}


void CvTeam::changeOpenBordersTradingCount(int iChange) {
	m_iOpenBordersTradingCount = (m_iOpenBordersTradingCount + iChange);
	FAssert(getOpenBordersTradingCount() >= 0);
}


int CvTeam::getDefensivePactTradingCount() const {
	return m_iDefensivePactTradingCount;
}


bool CvTeam::isDefensivePactTrading() const {
	return (getDefensivePactTradingCount() > 0);
}


void CvTeam::changeDefensivePactTradingCount(int iChange) {
	m_iDefensivePactTradingCount = (m_iDefensivePactTradingCount + iChange);
	FAssert(getDefensivePactTradingCount() >= 0);
}


int CvTeam::getPermanentAllianceTradingCount() const {
	return m_iPermanentAllianceTradingCount;
}


bool CvTeam::isPermanentAllianceTrading() const {
	if (!(GC.getGameINLINE().isOption(GAMEOPTION_PERMANENT_ALLIANCES))) {
		return false;
	}

	return (getPermanentAllianceTradingCount() > 0);
}


void CvTeam::changePermanentAllianceTradingCount(int iChange) {
	m_iPermanentAllianceTradingCount = (m_iPermanentAllianceTradingCount + iChange);
	FAssert(getPermanentAllianceTradingCount() >= 0);
}


int CvTeam::getVassalTradingCount() const {
	return m_iVassalTradingCount;
}


bool CvTeam::isVassalStateTrading() const {
	if (GC.getGameINLINE().isOption(GAMEOPTION_NO_VASSAL_STATES)) {
		return false;
	}

	return (getVassalTradingCount() > 0);
}


void CvTeam::changeVassalTradingCount(int iChange) {
	m_iVassalTradingCount += iChange;
	FAssert(getVassalTradingCount() >= 0);
}


int CvTeam::getBridgeBuildingCount() const {
	return m_iBridgeBuildingCount;
}


bool CvTeam::isBridgeBuilding()	const {
	return (getBridgeBuildingCount() > 0);
}


void CvTeam::changeBridgeBuildingCount(int iChange) {
	if (iChange != 0) {
		m_iBridgeBuildingCount = (m_iBridgeBuildingCount + iChange);
		FAssert(getBridgeBuildingCount() >= 0);

		if (GC.IsGraphicsInitialized()) {
			gDLL->getEngineIFace()->MarkBridgesDirty();
		}
	}
}


int CvTeam::getIrrigationCount() const {
	return m_iIrrigationCount;
}


bool CvTeam::isIrrigation() const {
	return (getIrrigationCount() > 0);
}


void CvTeam::changeIrrigationCount(int iChange) {
	if (iChange != 0) {
		m_iIrrigationCount = (m_iIrrigationCount + iChange);
		FAssert(getIrrigationCount() >= 0);

		GC.getMapINLINE().updateIrrigated();
	}
}


int CvTeam::getIgnoreIrrigationCount() const {
	return m_iIgnoreIrrigationCount;
}


bool CvTeam::isIgnoreIrrigation() const {
	return (getIgnoreIrrigationCount() > 0);
}


void CvTeam::changeIgnoreIrrigationCount(int iChange) {
	m_iIgnoreIrrigationCount = (m_iIgnoreIrrigationCount + iChange);
	FAssert(getIgnoreIrrigationCount() >= 0);
}


int CvTeam::getWaterWorkCount() const {
	return m_iWaterWorkCount;
}


bool CvTeam::isWaterWork() const {
	return (getWaterWorkCount() > 0);
}


void CvTeam::changeWaterWorkCount(int iChange) {
	if (iChange != 0) {
		m_iWaterWorkCount = (m_iWaterWorkCount + iChange);
		FAssert(getWaterWorkCount() >= 0);

		AI_makeAssignWorkDirty();
	}
}

int CvTeam::getVassalPower() const {
	return m_iVassalPower;
}

void CvTeam::setVassalPower(int iPower) {
	m_iVassalPower = iPower;
}

int CvTeam::getMasterPower() const {
	return m_iMasterPower;
}

void CvTeam::setMasterPower(int iPower) {
	m_iMasterPower = iPower;
}

int CvTeam::getEnemyWarWearinessModifier() const {
	return m_iEnemyWarWearinessModifier;
}

void CvTeam::changeEnemyWarWearinessModifier(int iChange) {
	m_iEnemyWarWearinessModifier += iChange;
}

bool CvTeam::isMapCentering() const {
	return m_bMapCentering;
}


void CvTeam::setMapCentering(bool bNewValue) {
	if (isMapCentering() != bNewValue) {
		m_bMapCentering = bNewValue;

		if (getID() == GC.getGameINLINE().getActiveTeam()) {
			gDLL->getInterfaceIFace()->setDirty(MinimapSection_DIRTY_BIT, true);
		}
	}
}


TeamTypes CvTeam::getID() const {
	return m_eID;
}


int CvTeam::getStolenVisibilityTimer(TeamTypes eIndex) const {
	FAssertMsg(eIndex >= 0, "iIndex is expected to be non-negative (invalid Index)");
	FAssertMsg(eIndex < MAX_TEAMS, "iIndex is expected to be within maximum bounds (invalid Index)");

	return m_aiStolenVisibilityTimer[eIndex];
}


bool CvTeam::isStolenVisibility(TeamTypes eIndex) const {
	FAssertMsg(eIndex >= 0, "iIndex is expected to be non-negative (invalid Index)");
	FAssertMsg(eIndex < MAX_TEAMS, "iIndex is expected to be within maximum bounds (invalid Index)");

	return (getStolenVisibilityTimer(eIndex) > 0);
}


void CvTeam::setStolenVisibilityTimer(TeamTypes eIndex, int iNewValue) {
	FAssertMsg(eIndex >= 0, "eIndex is expected to be non-negative (invalid Index)");
	FAssertMsg(eIndex < MAX_TEAMS, "eIndex is expected to be within maximum bounds (invalid Index)");

	if (getStolenVisibilityTimer(eIndex) != iNewValue) {
		bool bOldStolenVisibility = isStolenVisibility(eIndex);

		m_aiStolenVisibilityTimer[eIndex] = iNewValue;
		FAssert(getStolenVisibilityTimer(eIndex) >= 0);

		if (bOldStolenVisibility != isStolenVisibility(eIndex)) {
			for (int iI = 0; iI < GC.getMapINLINE().numPlotsINLINE(); iI++) {
				CvPlot* pLoopPlot = GC.getMapINLINE().plotByIndexINLINE(iI);

				if (pLoopPlot->isVisible(eIndex, false)) {
					pLoopPlot->changeStolenVisibilityCount(getID(), ((isStolenVisibility(eIndex)) ? 1 : -1));
				}
			}
		}
	}
}


void CvTeam::changeStolenVisibilityTimer(TeamTypes eIndex, int iChange) {
	setStolenVisibilityTimer(eIndex, (getStolenVisibilityTimer(eIndex) + iChange));
}


// (K-Mod note: units are unhappiness per 100,000 population. ie. 1000 * percent unhappiness.)
int CvTeam::getWarWeariness(TeamTypes eIndex, bool bUseEnemyModifer) const {
	FAssertMsg(eIndex >= 0, "eIndex is expected to be non-negative (invalid Index)");
	FAssertMsg(eIndex < MAX_TEAMS, "eIndex is expected to be within maximum bounds (invalid Index)");
	//return m_aiWarWeariness[eIndex];
	return bUseEnemyModifer ? m_aiWarWeariness[eIndex] * std::max(0, 100 + GET_TEAM(eIndex).getEnemyWarWearinessModifier()) / 100 : m_aiWarWeariness[eIndex]; // K-Mod
}


void CvTeam::setWarWeariness(TeamTypes eIndex, int iNewValue) {
	FAssertMsg(eIndex >= 0, "eIndex is expected to be non-negative (invalid Index)");
	FAssertMsg(eIndex < MAX_TEAMS, "eIndex is expected to be within maximum bounds (invalid Index)");
	m_aiWarWeariness[eIndex] = std::max(0, iNewValue);
}


void CvTeam::changeWarWeariness(TeamTypes eIndex, int iChange) {
	FAssert(eIndex >= 0 && eIndex < MAX_TEAMS);
	setWarWeariness(eIndex, getWarWeariness(eIndex) + iChange);
}

void CvTeam::changeWarWeariness(TeamTypes eOtherTeam, const CvPlot& kPlot, int iFactor) {
	int iOurCulture = kPlot.countFriendlyCulture(getID());
	int iTheirCulture = kPlot.countFriendlyCulture(eOtherTeam);

	int iRatio = 100;
	if (0 != iOurCulture + iTheirCulture) {
		iRatio = (100 * iTheirCulture) / (iOurCulture + iTheirCulture);
	}

	changeWarWeariness(eOtherTeam, iRatio * iFactor);
}


int CvTeam::getTechShareCount(int iIndex) const {
	FAssertMsg(iIndex >= 0, "iIndex is expected to be non-negative (invalid Index)");
	FAssertMsg(iIndex < MAX_TEAMS, "iIndex is expected to be within maximum bounds (invalid Index)");
	return m_aiTechShareCount[iIndex];
}


bool CvTeam::isTechShare(int iIndex) const {
	return (getTechShareCount(iIndex) > 0);
}


void CvTeam::changeTechShareCount(int iIndex, int iChange) {
	FAssertMsg(iIndex >= 0, "iIndex is expected to be non-negative (invalid Index)");
	FAssertMsg(iIndex < MAX_TEAMS, "iIndex is expected to be within maximum bounds (invalid Index)");

	if (iChange != 0) {
		m_aiTechShareCount[iIndex] = (m_aiTechShareCount[iIndex] + iChange);
		FAssert(getTechShareCount(iIndex) >= 0);

		if (isTechShare(iIndex)) {
			updateTechShare();
		}
	}
}


int CvTeam::getCommerceFlexibleCount(CommerceTypes eIndex) const {
	FAssertMsg(eIndex >= 0, "eIndex is expected to be non-negative (invalid Index)");
	FAssertMsg(eIndex < NUM_COMMERCE_TYPES, "eIndex is expected to be within maximum bounds (invalid Index)");
	return m_aiCommerceFlexibleCount[eIndex];
}


bool CvTeam::isCommerceFlexible(CommerceTypes eIndex) const {
	return (getCommerceFlexibleCount(eIndex) > 0);
}


void CvTeam::changeCommerceFlexibleCount(CommerceTypes eIndex, int iChange) {
	FAssertMsg(eIndex >= 0, "eIndex is expected to be non-negative (invalid Index)");
	FAssertMsg(eIndex < NUM_COMMERCE_TYPES, "eIndex is expected to be within maximum bounds (invalid Index)");

	if (iChange != 0) {
		m_aiCommerceFlexibleCount[eIndex] = (m_aiCommerceFlexibleCount[eIndex] + iChange);
		FAssert(getCommerceFlexibleCount(eIndex) >= 0);

		if (getID() == GC.getGameINLINE().getActiveTeam()) {
			gDLL->getInterfaceIFace()->setDirty(PercentButtons_DIRTY_BIT, true);
			gDLL->getInterfaceIFace()->setDirty(GameData_DIRTY_BIT, true);
		}
	}
}


int CvTeam::getExtraMoves(DomainTypes eIndex) const {
	FAssertMsg(eIndex >= 0, "eIndex is expected to be non-negative (invalid Index)");
	FAssertMsg(eIndex < NUM_DOMAIN_TYPES, "eIndex is expected to be within maximum bounds (invalid Index)");
	return m_aiExtraMoves[eIndex];
}


void CvTeam::changeExtraMoves(DomainTypes eIndex, int iChange) {
	FAssertMsg(eIndex >= 0, "eIndex is expected to be non-negative (invalid Index)");
	FAssertMsg(eIndex < NUM_DOMAIN_TYPES, "eIndex is expected to be within maximum bounds (invalid Index)");
	m_aiExtraMoves[eIndex] = (m_aiExtraMoves[eIndex] + iChange);
	FAssert(getExtraMoves(eIndex) >= 0);
}


bool CvTeam::isHasMet(TeamTypes eIndex)	const {
	FAssertMsg(eIndex >= 0, "eIndex is expected to be non-negative (invalid Index)");
	FAssertMsg(eIndex < MAX_TEAMS, "eIndex is expected to be within maximum bounds (invalid Index)");
	return m_abHasMet[eIndex];
}

void CvTeam::makeHasMet(TeamTypes eIndex, bool bNewDiplo) {
	FAssertMsg(eIndex >= 0, "eIndex is expected to be non-negative (invalid Index)");
	FAssertMsg(eIndex < MAX_TEAMS, "eIndex is expected to be within maximum bounds (invalid Index)");

	if (!isHasMet(eIndex)) {
		makeHasSeen(eIndex); // K-mod
		m_abHasMet[eIndex] = true;

		updateTechShare();

		for (int iI = 0; iI < MAX_CIV_TEAMS; iI++) {
			if (GET_TEAM((TeamTypes)iI).isAlive()) {
				if (GET_TEAM((TeamTypes)iI).isVassal(getID()) || isVassal((TeamTypes)iI)) {
					GET_TEAM((TeamTypes)iI).meet(eIndex, bNewDiplo);
				}
			}
		}

		// K-Mod. Initialize attitude cache for players on our team towards player's on their team.
		for (PlayerTypes i = (PlayerTypes)0; i < MAX_CIV_PLAYERS; i = (PlayerTypes)(i + 1)) {
			CvPlayerAI& kOurPlayer = GET_PLAYER(i);
			if (!kOurPlayer.isAlive() || kOurPlayer.getTeam() != getID())
				continue;

			for (PlayerTypes j = (PlayerTypes)0; j < MAX_CIV_PLAYERS; j = (PlayerTypes)(j + 1)) {
				CvPlayerAI& kTheirPlayer = GET_PLAYER(j);
				if (!kTheirPlayer.isAlive() || kTheirPlayer.getTeam() != eIndex)
					continue;

				kOurPlayer.AI_updateAttitudeCache(j);
			}
		}

		if ((getID() == GC.getGameINLINE().getActiveTeam()) || (eIndex == GC.getGameINLINE().getActiveTeam())) {
			gDLL->getInterfaceIFace()->setDirty(Score_DIRTY_BIT, true);
		}

		if (GC.getGameINLINE().isOption(GAMEOPTION_ALWAYS_WAR)) {
			if (isHuman() && getID() != eIndex) {
				declareWar(eIndex, false, NO_WARPLAN);
			}
		} else {
			if (GC.getGameINLINE().isFinalInitialized() && !(gDLL->GetWorldBuilderMode())) {
				if (bNewDiplo) {
					if (!isHuman()) {
						if (!isAtWar(eIndex)) {
							for (int iI = 0; iI < MAX_PLAYERS; iI++) {
								if (GET_PLAYER((PlayerTypes)iI).isAlive()) {
									if (GET_PLAYER((PlayerTypes)iI).getTeam() == eIndex) {
										if (GET_PLAYER(getLeaderID()).canContact((PlayerTypes)iI)) {
											if (GET_PLAYER((PlayerTypes)iI).isHuman()) {
												CvDiploParameters* pDiplo = new CvDiploParameters(getLeaderID());
												FAssertMsg(pDiplo != NULL, "pDiplo must be valid");
												pDiplo->setDiploComment((DiploCommentTypes)GC.getInfoTypeForString("AI_DIPLOCOMMENT_FIRST_CONTACT"));
												pDiplo->setAIContact(true);
												gDLL->beginDiplomacy(pDiplo, ((PlayerTypes)iI));
											}
										}
									}
								}
							}
						}
					}
				}
			}
		}

		// report event to Python, along with some other key state
		CvEventReporter::getInstance().firstContact(getID(), eIndex);
	}
}


bool CvTeam::isAtWar(TeamTypes eIndex) const {
	FAssertMsg(eIndex >= 0, "eIndex is expected to be non-negative (invalid Index)");
	FAssertMsg(eIndex < MAX_TEAMS, "eIndex is expected to be within maximum bounds (invalid Index)");
	return m_abAtWar[eIndex];
}


void CvTeam::setAtWar(TeamTypes eIndex, bool bNewValue) {
	FAssertMsg(eIndex >= 0, "eIndex is expected to be non-negative (invalid Index)");
	FAssertMsg(eIndex < MAX_TEAMS, "eIndex is expected to be within maximum bounds (invalid Index)");
	m_abAtWar[eIndex] = bNewValue;
}


bool CvTeam::isPermanentWarPeace(TeamTypes eIndex) const {
	FAssertMsg(eIndex >= 0, "eIndex is expected to be non-negative (invalid Index)");
	FAssertMsg(eIndex < MAX_TEAMS, "eIndex is expected to be within maximum bounds (invalid Index)");
	return m_abPermanentWarPeace[eIndex];
}


void CvTeam::setPermanentWarPeace(TeamTypes eIndex, bool bNewValue) {
	FAssertMsg(eIndex >= 0, "eIndex is expected to be non-negative (invalid Index)");
	FAssertMsg(eIndex < MAX_TEAMS, "eIndex is expected to be within maximum bounds (invalid Index)");
	m_abPermanentWarPeace[eIndex] = bNewValue;
}


bool CvTeam::isFreeTrade(TeamTypes eIndex) const {
	if (isAtWar(eIndex)) {
		return false;
	}

	if (!isHasMet(eIndex)) {
		return false;
	}

	return (isOpenBorders(eIndex) || isLimitedBorders(eIndex) || GC.getGameINLINE().isFreeTrade() || isFreeTradeAgreement(eIndex));
}


bool CvTeam::isOpenBorders(TeamTypes eIndex) const {
	FAssertMsg(eIndex >= 0, "eIndex is expected to be non-negative (invalid Index)");
	FAssertMsg(eIndex < MAX_TEAMS, "eIndex is expected to be within maximum bounds (invalid Index)");
	return m_abOpenBorders[eIndex];
}


void CvTeam::setOpenBorders(TeamTypes eIndex, bool bNewValue) {
	FAssertMsg(eIndex >= 0, "eIndex is expected to be non-negative (invalid Index)");
	FAssertMsg(eIndex < MAX_TEAMS, "eIndex is expected to be within maximum bounds (invalid Index)");

	if (isOpenBorders(eIndex) != bNewValue) {
		bool bOldFreeTrade = isFreeTrade(eIndex);

		m_abOpenBorders[eIndex] = bNewValue;

		AI_setOpenBordersCounter(eIndex, 0);

		GC.getMapINLINE().verifyUnitValidPlot();

		if ((getID() == GC.getGameINLINE().getActiveTeam()) || (eIndex == GC.getGameINLINE().getActiveTeam())) {
			gDLL->getInterfaceIFace()->setDirty(Score_DIRTY_BIT, true);
		}

		if (bOldFreeTrade != isFreeTrade(eIndex)) {
			for (int iI = 0; iI < MAX_PLAYERS; iI++) {
				if (GET_PLAYER((PlayerTypes)iI).isAlive()) {
					if (GET_PLAYER((PlayerTypes)iI).getTeam() == getID()) {
						GET_PLAYER((PlayerTypes)iI).updateTradeRoutes();
					}
				}
			}
		}
	}
}


bool CvTeam::isDefensivePact(TeamTypes eIndex) const {
	FAssertMsg(eIndex >= 0, "eIndex is expected to be non-negative (invalid Index)");
	FAssertMsg(eIndex < MAX_TEAMS, "eIndex is expected to be within maximum bounds (invalid Index)");
	return m_abDefensivePact[eIndex];
}


void CvTeam::setDefensivePact(TeamTypes eIndex, bool bNewValue) {
	FAssertMsg(eIndex >= 0, "eIndex is expected to be non-negative (invalid Index)");
	FAssertMsg(eIndex < MAX_TEAMS, "eIndex is expected to be within maximum bounds (invalid Index)");

	if (isDefensivePact(eIndex) != bNewValue) {
		m_abDefensivePact[eIndex] = bNewValue;

		if ((getID() == GC.getGameINLINE().getActiveTeam()) || (eIndex == GC.getGameINLINE().getActiveTeam())) {
			gDLL->getInterfaceIFace()->setDirty(Score_DIRTY_BIT, true);
		}

		if (bNewValue && !GET_TEAM(eIndex).isDefensivePact(getID())) {
			CvWString szBuffer = gDLL->getText("TXT_KEY_MISC_PLAYERS_SIGN_DEFENSIVE_PACT", getReplayName().GetCString(), GET_TEAM(eIndex).getReplayName().GetCString());

			GC.getGameINLINE().addReplayMessage(REPLAY_MESSAGE_MAJOR_EVENT, getLeaderID(), szBuffer, -1, -1, (ColorTypes)GC.getInfoTypeForString("COLOR_HIGHLIGHT_TEXT"));


			for (int iI = 0; iI < MAX_PLAYERS; iI++) {
				CvPlayer& kPlayer = GET_PLAYER((PlayerTypes)iI);
				if (kPlayer.isAlive()) {
					if (isHasMet(kPlayer.getTeam()) && GET_TEAM(eIndex).isHasMet(kPlayer.getTeam())) {
						gDLL->getInterfaceIFace()->addHumanMessage(((PlayerTypes)iI), false, GC.getEVENT_MESSAGE_TIME(), szBuffer, "AS2D_THEIRALLIANCE", MESSAGE_TYPE_MAJOR_EVENT, NULL, (ColorTypes)GC.getInfoTypeForString("COLOR_HIGHLIGHT_TEXT"));
					}
				}
			}
		}

		// K-Mod. update attitude
		if (GC.getGameINLINE().isFinalInitialized()) {
			for (PlayerTypes i = (PlayerTypes)0; i < MAX_CIV_PLAYERS; i = (PlayerTypes)(i + 1)) {
				GET_PLAYER(i).AI_updateAttitudeCache();
			}
		}
	}
}


bool CvTeam::isForcePeace(TeamTypes eIndex) const {
	FAssertMsg(eIndex >= 0, "eIndex is expected to be non-negative (invalid Index)");
	FAssertMsg(eIndex < MAX_TEAMS, "eIndex is expected to be within maximum bounds (invalid Index)");
	return m_abForcePeace[eIndex];
}


void CvTeam::setForcePeace(TeamTypes eIndex, bool bNewValue) {
	FAssertMsg(eIndex >= 0, "eIndex is expected to be non-negative (invalid Index)");
	FAssertMsg(eIndex < MAX_TEAMS, "eIndex is expected to be within maximum bounds (invalid Index)");
	m_abForcePeace[eIndex] = bNewValue;

	if (isForcePeace(eIndex)) {
		if (AI_isSneakAttackPreparing(eIndex)) {
			AI_setWarPlan(eIndex, NO_WARPLAN);
		}

		for (int iTeam = 0; iTeam < MAX_TEAMS; ++iTeam) {
			if (GET_TEAM((TeamTypes)iTeam).isVassal(eIndex)) {
				if (AI_isSneakAttackPreparing((TeamTypes)iTeam)) {
					AI_setWarPlan((TeamTypes)iTeam, NO_WARPLAN);
				}
			}
		}
	}
}

bool CvTeam::isVassal(TeamTypes eIndex) const {
	FAssertMsg(eIndex >= 0, "eIndex is expected to be non-negative (invalid Index)");
	FAssertMsg(eIndex < MAX_TEAMS, "eIndex is expected to be within maximum bounds (invalid Index)");
	return m_abVassal[eIndex];
}

void CvTeam::setVassal(TeamTypes eIndex, bool bNewValue, bool bCapitulated) {
	FAssertMsg(eIndex >= 0, "eIndex is expected to be non-negative (invalid Index)");
	FAssertMsg(eIndex < MAX_TEAMS, "eIndex is expected to be within maximum bounds (invalid Index)");
	FAssertMsg(!bNewValue || !GET_TEAM(eIndex).isAVassal(), "can't become a vassal of a vassal")

		if (isVassal(eIndex) != bNewValue) {
			for (int i = 0; i < MAX_PLAYERS; i++) {
				if (GET_PLAYER((PlayerTypes)i).getTeam() == getID()) {
					GET_PLAYER((PlayerTypes)i).updateCitySight(false, false);
				}
			}

			for (int iPlayer = 0; iPlayer < MAX_PLAYERS; ++iPlayer) {
				CvPlayer& kLoopPlayer = GET_PLAYER((PlayerTypes)iPlayer);

				if (kLoopPlayer.isAlive() && kLoopPlayer.getTeam() == eIndex) {
					int iLoop;
					for (CvUnit* pLoopUnit = kLoopPlayer.firstUnit(&iLoop); NULL != pLoopUnit; pLoopUnit = kLoopPlayer.nextUnit(&iLoop)) {
						CvPlot* pPlot = pLoopUnit->plot();
						if (pLoopUnit->getTeam() != pPlot->getTeam() && (pPlot->getTeam() == NO_TEAM || !GET_TEAM(pPlot->getTeam()).isVassal(pLoopUnit->getTeam()))) {
							kLoopPlayer.changeNumOutsideUnits(-1);
						}
					}
				}
			}

			m_abVassal[eIndex] = bNewValue;

			for (int iPlayer = 0; iPlayer < MAX_PLAYERS; ++iPlayer) {
				CvPlayer& kLoopPlayer = GET_PLAYER((PlayerTypes)iPlayer);

				if (kLoopPlayer.isAlive() && kLoopPlayer.getTeam() == eIndex) {
					int iLoop;
					for (CvUnit* pLoopUnit = kLoopPlayer.firstUnit(&iLoop); NULL != pLoopUnit; pLoopUnit = kLoopPlayer.nextUnit(&iLoop)) {
						CvPlot* pPlot = pLoopUnit->plot();
						if (pLoopUnit->getTeam() != pPlot->getTeam() && (pPlot->getTeam() == NO_TEAM || !GET_TEAM(pPlot->getTeam()).isVassal(pLoopUnit->getTeam()))) {
							kLoopPlayer.changeNumOutsideUnits(1);
						}
					}
				}
			}

			for (int i = 0; i < MAX_PLAYERS; i++) {
				if (GET_PLAYER((PlayerTypes)i).getTeam() == getID()) {
					GET_PLAYER((PlayerTypes)i).updateCitySight(true, false);
				}
			}

			for (int i = 0; i < GC.getMapINLINE().numPlotsINLINE(); ++i) {
				CvPlot* pLoopPlot = GC.getMapINLINE().plotByIndexINLINE(i);
				if (pLoopPlot && (pLoopPlot->getTeam() == getID() || pLoopPlot->getTeam() == eIndex)) {
					pLoopPlot->updateCulture(true, false);
				}
			}

			GC.getGameINLINE().updatePlotGroups();

			if (isVassal(eIndex)) {
				m_bCapitulated = bCapitulated;

				int iLoop;
				for (CvDeal* pLoopDeal = GC.getGameINLINE().firstDeal(&iLoop); pLoopDeal != NULL; pLoopDeal = GC.getGameINLINE().nextDeal(&iLoop)) {
					if ((GET_PLAYER(pLoopDeal->getFirstPlayer()).getTeam() == getID()) || (GET_PLAYER(pLoopDeal->getSecondPlayer()).getTeam() == getID())) {
						bool bValid = true;

						if (pLoopDeal->getFirstTrades() != NULL) {
							for (CLLNode<TradeData>* pNode = pLoopDeal->getFirstTrades()->head(); pNode; pNode = pLoopDeal->getFirstTrades()->next(pNode)) {
								if ((pNode->m_data.m_eItemType == TRADE_DEFENSIVE_PACT) ||
									(pNode->m_data.m_eItemType == TRADE_PEACE_TREATY)) {
									bValid = false;
									break;
								}
							}
						}

						if (bValid && pLoopDeal->getSecondTrades() != NULL) {
							for (CLLNode<TradeData>* pNode = pLoopDeal->getSecondTrades()->head(); pNode; pNode = pLoopDeal->getSecondTrades()->next(pNode)) {
								if ((pNode->m_data.m_eItemType == TRADE_DEFENSIVE_PACT) ||
									(pNode->m_data.m_eItemType == TRADE_PEACE_TREATY)) {
									bValid = false;
									break;
								}
							}
						}

						if (!bValid) {
							pLoopDeal->kill();
						}
					}
				}

				setForcePeace(eIndex, false);
				GET_TEAM(eIndex).setForcePeace(getID(), false);

				// Declare war on teams you should be at war with
				for (int iI = 0; iI < MAX_TEAMS; iI++) {
					if ((iI != getID()) && (iI != eIndex)) {
						if (GET_TEAM((TeamTypes)iI).isAlive()) {
							if (GET_TEAM(eIndex).isHasMet((TeamTypes)iI)) {
								meet((TeamTypes)iI, true);
							}

							if (isHasMet((TeamTypes)iI)) {
								GET_TEAM(eIndex).meet((TeamTypes)iI, true);
							}

							if (GET_TEAM(eIndex).isAtWar((TeamTypes)iI)) {
								declareWar(((TeamTypes)iI), false, WARPLAN_DOGPILE);
							} else if (isAtWar((TeamTypes)iI)) {
								if (bCapitulated) {
									makePeace((TeamTypes)iI);
								} else {
									GET_TEAM(eIndex).declareWar((TeamTypes)iI, false, WARPLAN_DOGPILE);
								}
							}
						}
					}
				}

				for (int iI = 0; iI < MAX_TEAMS; iI++) {
					CvTeam& kLoopTeam = GET_TEAM((TeamTypes)iI);
					if (kLoopTeam.isAlive()) {
						if (!kLoopTeam.isAtWar(getID())) {
							kLoopTeam.AI_setWarPlan(getID(), NO_WARPLAN);
							AI_setWarPlan((TeamTypes)iI, NO_WARPLAN);
						}

						if (!kLoopTeam.isAtWar(eIndex)) {
							kLoopTeam.AI_setWarPlan(eIndex, NO_WARPLAN);
						}

					}
				}

				// All our vassals now become free
				for (int iI = 0; iI < MAX_TEAMS; iI++) {
					if ((iI != getID()) && (iI != eIndex)) {
						CvTeam& kLoopTeam = GET_TEAM((TeamTypes)iI);
						if (kLoopTeam.isAlive() && kLoopTeam.isVassal(getID())) {
							freeVassal((TeamTypes)iI);
						}
					}
				}

				setMasterPower(GET_TEAM(eIndex).getTotalLand());
				setVassalPower(getTotalLand(false));

				if (GC.getGameINLINE().isFinalInitialized() && !(gDLL->GetWorldBuilderMode())) {
					CvWString szReplayMessage;

					if (bCapitulated) {
						szReplayMessage = gDLL->getText("TXT_KEY_MISC_CAPITULATE_AGREEMENT", getReplayName().GetCString(), GET_TEAM(eIndex).getReplayName().GetCString());
					} else {
						szReplayMessage = gDLL->getText("TXT_KEY_MISC_VASSAL_AGREEMENT", getReplayName().GetCString(), GET_TEAM(eIndex).getReplayName().GetCString());
					}
					GC.getGameINLINE().addReplayMessage(REPLAY_MESSAGE_MAJOR_EVENT, getLeaderID(), szReplayMessage, -1, -1, (ColorTypes)GC.getInfoTypeForString("COLOR_HIGHLIGHT_TEXT"));

					for (int iI = 0; iI < MAX_PLAYERS; iI++) {
						CvPlayer& kPlayer = GET_PLAYER((PlayerTypes)iI);
						if (kPlayer.isAlive()) {
							if (isHasMet(kPlayer.getTeam()) && GET_TEAM(eIndex).isHasMet(kPlayer.getTeam())) {
								gDLL->getInterfaceIFace()->addHumanMessage(((PlayerTypes)iI), false, GC.getEVENT_MESSAGE_TIME(), szReplayMessage, "AS2D_WELOVEKING", MESSAGE_TYPE_MAJOR_EVENT, NULL, (ColorTypes)GC.getInfoTypeForString("COLOR_HIGHLIGHT_TEXT"));
							}
						}
					}
				}
			} else {
				setMasterPower(0);
				setVassalPower(0);

				if (GC.getGameINLINE().isFinalInitialized() && !(gDLL->GetWorldBuilderMode()) && isAlive() && GET_TEAM(eIndex).isAlive()) {
					CvWString szReplayMessage;

					if (m_bCapitulated) {
						szReplayMessage = gDLL->getText("TXT_KEY_MISC_SURRENDER_REVOLT", getReplayName().GetCString(), GET_TEAM(eIndex).getReplayName().GetCString());
					} else {
						szReplayMessage = gDLL->getText("TXT_KEY_MISC_VASSAL_REVOLT", getReplayName().GetCString(), GET_TEAM(eIndex).getReplayName().GetCString());
					}

					GC.getGameINLINE().addReplayMessage(REPLAY_MESSAGE_MAJOR_EVENT, getLeaderID(), szReplayMessage, -1, -1, (ColorTypes)GC.getInfoTypeForString("COLOR_HIGHLIGHT_TEXT"));

					for (int iI = 0; iI < MAX_PLAYERS; iI++) {
						CvPlayer& kPlayer = GET_PLAYER((PlayerTypes)iI);
						if (kPlayer.isAlive()) {
							CvWString szBuffer;
							if (getID() == kPlayer.getTeam() || eIndex == kPlayer.getTeam() || isHasMet(kPlayer.getTeam()) && GET_TEAM(eIndex).isHasMet(kPlayer.getTeam())) {
								szBuffer = szReplayMessage;
							}

							if (!szBuffer.empty()) {
								gDLL->getInterfaceIFace()->addHumanMessage(((PlayerTypes)iI), false, GC.getEVENT_MESSAGE_TIME(), szBuffer, "AS2D_REVOLTSTART", MESSAGE_TYPE_MAJOR_EVENT, NULL, (ColorTypes)GC.getInfoTypeForString("COLOR_HIGHLIGHT_TEXT"));
							}
						}
					}
				}

				m_bCapitulated = false;
			}

			for (int iPlayer = 0; iPlayer < MAX_PLAYERS; iPlayer++) {
				CvPlayer& kLoopPlayer = GET_PLAYER((PlayerTypes)iPlayer);
				if (kLoopPlayer.getTeam() == eIndex) {
					kLoopPlayer.updateMaintenance();
				}
			}

			if (GC.getGameINLINE().isFinalInitialized() && !(gDLL->GetWorldBuilderMode())) {
				CvEventReporter::getInstance().vassalState(eIndex, getID(), bNewValue);
			}

			// K-Mod. update attitude
			for (PlayerTypes i = (PlayerTypes)0; i < MAX_CIV_PLAYERS; i = (PlayerTypes)(i + 1)) {
				if (GET_PLAYER(i).getTeam() == getID() || GET_PLAYER(i).getTeam() == eIndex) {
					for (PlayerTypes j = (PlayerTypes)0; j < MAX_CIV_PLAYERS; j = (PlayerTypes)(j + 1)) {
						if (GET_PLAYER(j).getTeam() != GET_PLAYER(i).getTeam()) {
							if (GET_PLAYER(j).getTeam() == getID() || GET_PLAYER(j).getTeam() == eIndex) {
								GET_PLAYER(i).AI_updateAttitudeCache(j);
								GET_PLAYER(j).AI_updateAttitudeCache(i);
							}
						}
					}
				}
			}
		}
}

// K-Mod. Return the team which is the master of this team. (if this team is free, return getID())
TeamTypes CvTeam::getMasterTeam() const {
	for (TeamTypes i = (TeamTypes)0; i < MAX_CIV_TEAMS; i = (TeamTypes)(i + 1)) {
		if (isVassal(i) && GET_TEAM(i).isAlive())
			return i;
	}
	return getID();
}

void CvTeam::assignVassal(TeamTypes eVassal, bool bSurrender) const {
	GET_TEAM(eVassal).setVassal(getID(), true, bSurrender);

	TradeData item;
	setTradeItem(&item, bSurrender ? TRADE_SURRENDER : TRADE_VASSAL);
	item.m_iData = 1;

	for (int iPlayer = 0; iPlayer < MAX_PLAYERS; iPlayer++) {
		CvPlayer& kLoopPlayer = GET_PLAYER((PlayerTypes)iPlayer);
		if (kLoopPlayer.getTeam() == eVassal && kLoopPlayer.isAlive()) {
			CLinkList<TradeData> ourList;
			CLinkList<TradeData> theirList;
			ourList.clear();
			theirList.clear();

			theirList.insertAtEnd(item);

			for (int jPlayer = 0; jPlayer < MAX_PLAYERS; jPlayer++) {
				if (GET_PLAYER((PlayerTypes)jPlayer).getTeam() == getID()) {
					GC.getGameINLINE().implementDeal(((PlayerTypes)jPlayer), ((PlayerTypes)iPlayer), &ourList, &theirList, true);
				}
			}
		}
	}
}


void CvTeam::freeVassal(TeamTypes eVassal) const {
	int iLoop;
	for (CvDeal* pLoopDeal = GC.getGameINLINE().firstDeal(&iLoop); pLoopDeal != NULL; pLoopDeal = GC.getGameINLINE().nextDeal(&iLoop)) {
		bool bValid = true;

		if ((GET_PLAYER(pLoopDeal->getFirstPlayer()).getTeam() == eVassal) && (GET_PLAYER(pLoopDeal->getSecondPlayer()).getTeam() == getID())) {

			if (pLoopDeal->getFirstTrades() != NULL) {
				for (CLLNode<TradeData>* pNode = pLoopDeal->getFirstTrades()->head(); pNode; pNode = pLoopDeal->getFirstTrades()->next(pNode)) {
					if ((pNode->m_data.m_eItemType == TRADE_VASSAL) ||
						(pNode->m_data.m_eItemType == TRADE_SURRENDER)) {
						bValid = false;
					}
				}
			}
		}

		if (bValid) {
			if ((GET_PLAYER(pLoopDeal->getFirstPlayer()).getTeam() == getID()) && (GET_PLAYER(pLoopDeal->getSecondPlayer()).getTeam() == eVassal)) {
				if (pLoopDeal->getSecondTrades() != NULL) {
					for (CLLNode<TradeData>* pNode = pLoopDeal->getSecondTrades()->head(); pNode; pNode = pLoopDeal->getSecondTrades()->next(pNode)) {
						if ((pNode->m_data.m_eItemType == TRADE_VASSAL) ||
							(pNode->m_data.m_eItemType == TRADE_SURRENDER)) {
							bValid = false;
						}
					}
				}
			}
		}

		if (!bValid) {
			pLoopDeal->kill();
		}
	}
}

bool CvTeam::isCapitulated() const {
	FAssert(!m_bCapitulated || isAVassal()); // K-Mod

	return m_bCapitulated;
}


int CvTeam::getRouteChange(RouteTypes eIndex) const {
	FAssertMsg(eIndex >= 0, "eIndex is expected to be non-negative (invalid Index)");
	FAssertMsg(eIndex < GC.getNumRouteInfos(), "eIndex is expected to be within maximum bounds (invalid Index)");
	return m_paiRouteChange[eIndex];
}


void CvTeam::changeRouteChange(RouteTypes eIndex, int iChange) {
	FAssertMsg(eIndex >= 0, "eIndex is expected to be non-negative (invalid Index)");
	FAssertMsg(eIndex < GC.getNumRouteInfos(), "eIndex is expected to be within maximum bounds (invalid Index)");
	m_paiRouteChange[eIndex] = (m_paiRouteChange[eIndex] + iChange);
}


int CvTeam::getProjectCount(ProjectTypes eIndex) const {
	FAssertMsg(eIndex >= 0, "eIndex is expected to be non-negative (invalid Index)");
	FAssertMsg(eIndex < GC.getNumProjectInfos(), "eIndex is expected to be within maximum bounds (invalid Index)");
	return m_paiProjectCount[eIndex];
}

int CvTeam::getProjectDefaultArtType(ProjectTypes eIndex) const {
	FAssertMsg(eIndex >= 0, "eIndex is expected to be non-negative (invalid Index)");
	FAssertMsg(eIndex < GC.getNumProjectInfos(), "eIndex is expected to be within maximum bounds (invalid Index)");
	return m_paiProjectDefaultArtTypes[eIndex];
}

void CvTeam::setProjectDefaultArtType(ProjectTypes eIndex, int value) {
	FAssertMsg(eIndex >= 0, "eIndex is expected to be non-negative (invalid Index)");
	FAssertMsg(eIndex < GC.getNumProjectInfos(), "eIndex is expected to be within maximum bounds (invalid Index)");
	m_paiProjectDefaultArtTypes[eIndex] = value;
}

int CvTeam::getProjectArtType(ProjectTypes eIndex, int number) const {
	FAssertMsg(eIndex >= 0, "eIndex is expected to be non-negative (invalid Index)");
	FAssertMsg(eIndex < GC.getNumProjectInfos(), "eIndex is expected to be within maximum bounds (invalid Index)");
	FAssertMsg(number >= 0, "number is expected to be non-negative (invalid Index)");
	FAssertMsg(number < getProjectCount(eIndex), "number is expected to be within maximum bounds (invalid Index)");
	return m_pavProjectArtTypes[eIndex][number];
}

void CvTeam::setProjectArtType(ProjectTypes eIndex, int number, int value) {
	FAssertMsg(eIndex >= 0, "eIndex is expected to be non-negative (invalid Index)");
	FAssertMsg(eIndex < GC.getNumProjectInfos(), "eIndex is expected to be within maximum bounds (invalid Index)");
	FAssertMsg(number >= 0, "number is expected to be non-negative (invalid Index)");
	FAssertMsg(number < getProjectCount(eIndex), "number is expected to be within maximum bounds (invalid Index)");
	m_pavProjectArtTypes[eIndex][number] = value;
}

bool CvTeam::isProjectMaxedOut(ProjectTypes eIndex, int iExtra) const {
	FAssertMsg(eIndex >= 0, "eIndex is expected to be non-negative (invalid Index)");
	FAssertMsg(eIndex < GC.getNumProjectInfos(), "eIndex is expected to be within maximum bounds (invalid Index)");

	if (!isTeamProject(eIndex)) {
		return false;
	}

	FAssertMsg(getProjectCount(eIndex) <= GC.getProjectInfo(eIndex).getMaxTeamInstances(), "Current Project count is expected to not exceed the maximum number of instances for this project");

	return ((getProjectCount(eIndex) + iExtra) >= GC.getProjectInfo(eIndex).getMaxTeamInstances());
}

bool CvTeam::isProjectAndArtMaxedOut(ProjectTypes eIndex) const {
	FAssertMsg(eIndex >= 0, "eIndex is expected to be non-negative (invalid Index)");
	FAssertMsg(eIndex < GC.getNumProjectInfos(), "eIndex is expected to be within maximum bounds (invalid Index)");

	if (getProjectCount(eIndex) >= GC.getProjectInfo(eIndex).getMaxTeamInstances()) {
		int count = getProjectCount(eIndex);
		for (int i = 0; i < count; i++) {
			if (getProjectArtType(eIndex, i) == -1) //undefined
				return false;
		}

		return true;
	} else {
		return false;
	}
}

void CvTeam::finalizeProjectArtTypes() {
	//loop through each project and fill in default art values
	for (int i = 0; i < GC.getNumProjectInfos(); i++) {
		ProjectTypes eProject = (ProjectTypes)i;
		int projectCount = getProjectCount(eProject);
		for (int j = 0; j < projectCount; j++) {
			int projectArtType = getProjectArtType(eProject, j);
			if (projectArtType == -1) //undefined
			{
				int defaultArtType = getProjectDefaultArtType(eProject);
				setProjectArtType(eProject, j, defaultArtType);
			}
		}
	}
}


void CvTeam::changeProjectCount(ProjectTypes eIndex, int iChange) {
	FAssertMsg(eIndex >= 0, "eIndex is expected to be non-negative (invalid Index)");
	FAssertMsg(eIndex < GC.getNumProjectInfos(), "eIndex is expected to be within maximum bounds (invalid Index)");

	if (iChange != 0) {
		GC.getGameINLINE().incrementProjectCreatedCount(eIndex, iChange);

		int iOldProjectCount = getProjectCount(eIndex);

		m_paiProjectCount[eIndex] = (m_paiProjectCount[eIndex] + iChange);
		FAssert(getProjectCount(eIndex) >= 0);

		//adjust default art types
		if (iChange >= 0) {
			int defaultType = -1;
			for (int i = 0; i < iChange; i++)
				m_pavProjectArtTypes[eIndex].push_back(defaultType);
		} else {
			for (int i = 0; i < -iChange; i++)
				m_pavProjectArtTypes[eIndex].pop_back();
		}
		FAssertMsg(getProjectCount(eIndex) == (int)m_pavProjectArtTypes[eIndex].size(), "[Jason] Unbalanced project art types.");

		CvProjectInfo& kProject = GC.getProjectInfo(eIndex);

		changeNukeInterception(kProject.getNukeInterception() * iChange);

		if ((kProject.getTechShare() > 0) && (kProject.getTechShare() <= MAX_TEAMS)) {
			changeTechShareCount((kProject.getTechShare() - 1), iChange);
		}

		for (VictoryTypes eVictory = (VictoryTypes)0; eVictory < GC.getNumVictoryInfos(); eVictory = (VictoryTypes)(eVictory + 1)) {
			if (kProject.getVictoryThreshold(eVictory) > 0) {
				setCanLaunch(eVictory, GC.getGameINLINE().testVictory(eVictory, getID()));
			}
		}

		if (iChange > 0) {
			if (kProject.getEveryoneSpecialUnit() != NO_SPECIALUNIT) {
				GC.getGameINLINE().makeSpecialUnitValid((SpecialUnitTypes)(kProject.getEveryoneSpecialUnit()));
			}

			if (kProject.getEveryoneSpecialBuilding() != NO_SPECIALBUILDING) {
				GC.getGameINLINE().makeSpecialBuildingValid((SpecialBuildingTypes)(kProject.getEveryoneSpecialBuilding()));
			}

			if (kProject.isAllowsNukes()) {
				GC.getGameINLINE().makeNukesValid(true);
			}

			for (int iI = 0; iI < MAX_PLAYERS; iI++) {
				if (GET_PLAYER((PlayerTypes)iI).isAlive()) {
					if (GET_PLAYER((PlayerTypes)iI).getTeam() == getID()) {
						if (!(GET_PLAYER((PlayerTypes)iI).isHuman())) {
							bool bChangeProduction = false;

							for (int iJ = 0; iJ < GC.getNumProjectInfos(); iJ++) {
								if ((getProjectCount(eIndex) >= GC.getProjectInfo((ProjectTypes)iJ).getProjectsNeeded(eIndex)) &&
									(iOldProjectCount < GC.getProjectInfo((ProjectTypes)iJ).getProjectsNeeded(eIndex))) {
									bChangeProduction = true;
									break;
								}
							}

							if (bChangeProduction) {
								GET_PLAYER((PlayerTypes)iI).AI_makeProductionDirty();
							}
						}
					}
				}
			}

			if (GC.getGameINLINE().isFinalInitialized() && !(gDLL->GetWorldBuilderMode())) {
				CvWString szBuffer;
				szBuffer = gDLL->getText("TXT_KEY_MISC_COMPLETES_PROJECT", getReplayName().GetCString(), kProject.getTextKeyWide());
				GC.getGameINLINE().addReplayMessage(REPLAY_MESSAGE_MAJOR_EVENT, getLeaderID(), szBuffer, -1, -1, (ColorTypes)GC.getInfoTypeForString("COLOR_HIGHLIGHT_TEXT"));

				for (int iI = 0; iI < MAX_PLAYERS; iI++) {
					if (GET_PLAYER((PlayerTypes)iI).isAlive()) {
						szBuffer = gDLL->getText("TXT_KEY_MISC_SOMEONE_HAS_COMPLETED", getName().GetCString(), kProject.getTextKeyWide());
						gDLL->getInterfaceIFace()->addHumanMessage(((PlayerTypes)iI), false, GC.getEVENT_MESSAGE_TIME(), szBuffer, "AS2D_PROJECT_COMPLETED", MESSAGE_TYPE_MAJOR_EVENT, NULL, (ColorTypes)GC.getInfoTypeForString("COLOR_HIGHLIGHT_TEXT"));
					}
				}
			}
		}
	}
}


int CvTeam::getProjectMaking(ProjectTypes eIndex) const {
	FAssertMsg(eIndex >= 0, "eIndex is expected to be non-negative (invalid Index)");
	FAssertMsg(eIndex < GC.getNumProjectInfos(), "eIndex is expected to be within maximum bounds (invalid Index)");
	return m_paiProjectMaking[eIndex];
}


void CvTeam::changeProjectMaking(ProjectTypes eIndex, int iChange) {
	FAssertMsg(eIndex >= 0, "eIndex is expected to be non-negative (invalid Index)");
	FAssertMsg(eIndex < GC.getNumProjectInfos(), "eIndex is expected to be within maximum bounds (invalid Index)");
	m_paiProjectMaking[eIndex] = (m_paiProjectMaking[eIndex] + iChange);
	FAssert(getProjectMaking(eIndex) >= 0);
}


int CvTeam::getUnitClassCount(UnitClassTypes eIndex) const {
	FAssertMsg(eIndex >= 0, "eIndex is expected to be non-negative (invalid Index)");
	FAssertMsg(eIndex < GC.getNumUnitClassInfos(), "eIndex is expected to be within maximum bounds (invalid Index)");
	return m_paiUnitClassCount[eIndex];
}


bool CvTeam::isUnitClassMaxedOut(UnitClassTypes eIndex, int iExtra) const {
	FAssertMsg(eIndex >= 0, "eIndex is expected to be non-negative (invalid Index)");
	FAssertMsg(eIndex < GC.getNumUnitClassInfos(), "eIndex is expected to be within maximum bounds (invalid Index)");

	if (!isTeamUnitClass(eIndex)) {
		return false;
	}

	FAssertMsg(getUnitClassCount(eIndex) <= GC.getUnitClassInfo(eIndex).getMaxTeamInstances(), "The current unit class count is expected not to exceed the maximum number of instances allowed for this team");

	return ((getUnitClassCount(eIndex) + iExtra) >= GC.getUnitClassInfo(eIndex).getMaxTeamInstances());
}


void CvTeam::changeUnitClassCount(UnitClassTypes eIndex, int iChange) {
	FAssertMsg(eIndex >= 0, "eIndex is expected to be non-negative (invalid Index)");
	FAssertMsg(eIndex < GC.getNumUnitClassInfos(), "eIndex is expected to be within maximum bounds (invalid Index)");
	m_paiUnitClassCount[eIndex] = (m_paiUnitClassCount[eIndex] + iChange);
	FAssert(getUnitClassCount(eIndex) >= 0);
}


int CvTeam::getBuildingClassCount(BuildingClassTypes eIndex) const {
	FAssertMsg(eIndex >= 0, "eIndex is expected to be non-negative (invalid Index)");
	FAssertMsg(eIndex < GC.getNumBuildingClassInfos(), "eIndex is expected to be within maximum bounds (invalid Index)");
	return m_paiBuildingClassCount[eIndex];
}


bool CvTeam::isBuildingClassMaxedOut(BuildingClassTypes eIndex, int iExtra) const {
	FAssertMsg(eIndex >= 0, "eIndex is expected to be non-negative (invalid Index)");
	FAssertMsg(eIndex < GC.getNumBuildingClassInfos(), "eIndex is expected to be within maximum bounds (invalid Index)");

	if (!isTeamWonderClass(eIndex)) {
		return false;
	}

	FAssertMsg(getBuildingClassCount(eIndex) <= GC.getBuildingClassInfo(eIndex).getMaxTeamInstances(), "The current building class count is expected not to exceed the maximum number of instances allowed for this team");

	return ((getBuildingClassCount(eIndex) + iExtra) >= GC.getBuildingClassInfo(eIndex).getMaxTeamInstances());
}


void CvTeam::changeBuildingClassCount(BuildingClassTypes eIndex, int iChange) {
	FAssertMsg(eIndex >= 0, "eIndex is expected to be non-negative (invalid Index)");
	FAssertMsg(eIndex < GC.getNumBuildingClassInfos(), "eIndex is expected to be within maximum bounds (invalid Index)");
	m_paiBuildingClassCount[eIndex] = (m_paiBuildingClassCount[eIndex] + iChange);
	FAssert(getBuildingClassCount(eIndex) >= 0);
}


int CvTeam::getObsoleteBuildingCount(BuildingTypes eIndex) const {
	FAssertMsg(eIndex >= 0, "eIndex is expected to be non-negative (invalid Index)");
	FAssertMsg(eIndex < GC.getNumBuildingInfos(), "eIndex is expected to be within maximum bounds (invalid Index)");
	return m_paiObsoleteBuildingCount[eIndex];
}


bool CvTeam::isObsoleteBuilding(BuildingTypes eIndex) const {
	return (getObsoleteBuildingCount(eIndex) > 0);
}


void CvTeam::changeObsoleteBuildingCount(BuildingTypes eIndex, int iChange) {
	FAssertMsg(eIndex >= 0, "eIndex is expected to be non-negative (invalid Index)");
	FAssertMsg(eIndex < GC.getNumBuildingInfos(), "eIndex is expected to be within maximum bounds (invalid Index)");

	if (iChange != 0) {
		// Continue to track the team obsolecence count for existing team based decisions, but the functional 
		//  changes are at the player level so we pass on the change to all team members
		m_paiObsoleteBuildingCount[eIndex] = (m_paiObsoleteBuildingCount[eIndex] + iChange);
		FAssert(getObsoleteBuildingCount(eIndex) >= 0);

		for (PlayerTypes ePlayer = (PlayerTypes)0; ePlayer < MAX_PLAYERS; ePlayer = (PlayerTypes)(ePlayer + 1)) {
			CvPlayer& kPlayer = GET_PLAYER(ePlayer);
			if (kPlayer.isAlive()) {
				if (kPlayer.getTeam() == getID()) {
					kPlayer.changeObsoleteBuildingCount(eIndex, iChange);
				}
			}
		}
	}
}


int CvTeam::getResearchProgress(TechTypes eIndex) const {
	if (eIndex != NO_TECH) {
		return m_paiResearchProgress[eIndex];
	} else {
		return 0;
	}
}


void CvTeam::setResearchProgress(TechTypes eIndex, int iNewValue, PlayerTypes ePlayer) {
	FAssertMsg(eIndex >= 0, "eIndex is expected to be non-negative (invalid Index)");
	FAssertMsg(eIndex < GC.getNumTechInfos(), "eIndex is expected to be within maximum bounds (invalid Index)");
	FAssertMsg(ePlayer >= 0, "eIndex is expected to be non-negative (invalid Index)");
	FAssertMsg(ePlayer < MAX_PLAYERS, "ePlayer is expected to be within maximum bounds (invalid Index)");

	if (getResearchProgress(eIndex) != iNewValue) {
		m_paiResearchProgress[eIndex] = iNewValue;
		FAssert(getResearchProgress(eIndex) >= 0);

		if (getID() == GC.getGameINLINE().getActiveTeam()) {
			gDLL->getInterfaceIFace()->setDirty(GameData_DIRTY_BIT, true);
			gDLL->getInterfaceIFace()->setDirty(Score_DIRTY_BIT, true);
		}

		if (getResearchProgress(eIndex) >= getResearchCost(eIndex)) {
			int iOverflow = (100 * (getResearchProgress(eIndex) - getResearchCost(eIndex))) / std::max(1, GET_PLAYER(ePlayer).calculateResearchModifier(eIndex));
			GET_PLAYER(ePlayer).changeOverflowResearch(iOverflow);
			setHasTech(eIndex, true, ePlayer, true, true);
		}
	}
}


void CvTeam::changeResearchProgress(TechTypes eIndex, int iChange, PlayerTypes ePlayer) {
	setResearchProgress(eIndex, (getResearchProgress(eIndex) + iChange), ePlayer);
}

int CvTeam::changeResearchProgressPercent(TechTypes eIndex, int iPercent, PlayerTypes ePlayer) {
	int iBeakers = 0;

	if (0 != iPercent && !isHasTech(eIndex)) {
		if (iPercent > 0) {
			iBeakers = std::min(getResearchLeft(eIndex), (getResearchCost(eIndex) * iPercent) / 100);
		} else {
			iBeakers = std::max(getResearchLeft(eIndex) - getResearchCost(eIndex), (getResearchCost(eIndex) * iPercent) / 100);
		}

		changeResearchProgress(eIndex, iBeakers, ePlayer);
	}

	return iBeakers;
}


int CvTeam::getTechCount(TechTypes eIndex)		 const {
	FAssertMsg(eIndex >= 0, "eIndex is expected to be non-negative (invalid Index)");
	FAssertMsg(eIndex < GC.getNumTechInfos(), "eIndex is expected to be within maximum bounds (invalid Index)");
	return m_paiTechCount[eIndex];
}

int CvTeam::getBestKnownTechScorePercent() const {
	int iOurTechScore = 0;
	int iBestKnownTechScore = 0;

	for (int iI = 0; iI < MAX_CIV_PLAYERS; iI++) {
		if (GET_PLAYER((PlayerTypes)iI).isAlive()) {
			if (GET_PLAYER((PlayerTypes)iI).getTeam() == getID()) {
				iOurTechScore = std::max(iOurTechScore, GET_PLAYER((PlayerTypes)iI).getTechScore());
			} else if (isHasMet(GET_PLAYER((PlayerTypes)iI).getTeam())) {
				iBestKnownTechScore = std::max(iBestKnownTechScore, GET_PLAYER((PlayerTypes)iI).getTechScore());
			}
		}
	}

	iBestKnownTechScore = std::max(iBestKnownTechScore, iOurTechScore);

	return ((100 * iOurTechScore) / std::max(iBestKnownTechScore, 1));
}

int CvTeam::getTerrainTradeCount(TerrainTypes eIndex) const {
	FAssertMsg(eIndex >= 0, "eIndex is expected to be non-negative (invalid Index)");
	FAssertMsg(eIndex < GC.getNumTerrainInfos(), "eIndex is expected to be within maximum bounds (invalid Index)");
	return m_paiTerrainTradeCount[eIndex];
}


bool CvTeam::isTerrainTrade(TerrainTypes eIndex) const {
	return (getTerrainTradeCount(eIndex) > 0);
}


void CvTeam::changeTerrainTradeCount(TerrainTypes eIndex, int iChange) {
	FAssertMsg(eIndex >= 0, "eIndex is expected to be non-negative (invalid Index)");
	FAssertMsg(eIndex < GC.getNumTerrainInfos(), "eIndex is expected to be within maximum bounds (invalid Index)");

	if (iChange != 0) {
		m_paiTerrainTradeCount[eIndex] = (m_paiTerrainTradeCount[eIndex] + iChange);
		FAssert(getTerrainTradeCount(eIndex) >= 0);

		for (int iI = 0; iI < MAX_PLAYERS; iI++) {
			if (GET_PLAYER((PlayerTypes)iI).isAlive()) {
				if (GET_PLAYER((PlayerTypes)iI).getTeam() == getID()) {
					GET_PLAYER((PlayerTypes)iI).updatePlotGroups();
				}
			}
		}
	}
}


int CvTeam::getRiverTradeCount() const {
	return m_iRiverTradeCount;
}


bool CvTeam::isRiverTrade() const {
	return (getRiverTradeCount() > 0);
}


void CvTeam::changeRiverTradeCount(int iChange) {
	if (iChange != 0) {
		m_iRiverTradeCount += iChange;
		FAssert(getRiverTradeCount() >= 0);

		for (int iI = 0; iI < MAX_PLAYERS; iI++) {
			if (GET_PLAYER((PlayerTypes)iI).isAlive()) {
				if (GET_PLAYER((PlayerTypes)iI).getTeam() == getID()) {
					GET_PLAYER((PlayerTypes)iI).updatePlotGroups();
				}
			}
		}
	}
}


int CvTeam::getVictoryCountdown(VictoryTypes eIndex) const {
	FAssertMsg(eIndex >= 0, "eIndex is expected to be non-negative (invalid Index)");
	FAssertMsg(eIndex < GC.getNumVictoryInfos(), "eIndex is expected to be within maximum bounds (invalid Index)");
	return m_aiVictoryCountdown[eIndex];
}

void CvTeam::setVictoryCountdown(VictoryTypes eIndex, int iTurnsLeft) {
	FAssertMsg(eIndex >= 0, "eIndex is expected to be non-negative (invalid Index)");
	FAssertMsg(eIndex < GC.getNumVictoryInfos(), "eIndex is expected to be within maximum bounds (invalid Index)");
	m_aiVictoryCountdown[eIndex] = iTurnsLeft;
}


void CvTeam::changeVictoryCountdown(VictoryTypes eIndex, int iChange) {
	FAssertMsg(eIndex >= 0, "eIndex is expected to be non-negative (invalid Index)");
	FAssertMsg(eIndex < GC.getNumVictoryInfos(), "eIndex is expected to be within maximum bounds (invalid Index)");

	if (iChange != 0) {
		m_aiVictoryCountdown[eIndex] += iChange;
		FAssert(m_aiVictoryCountdown[eIndex] >= 0);
	}
}

int CvTeam::getVictoryDelay(VictoryTypes eVictory) const {
	int iExtraDelayPercent = 0;
	for (int iProject = 0; iProject < GC.getNumProjectInfos(); ++iProject) {
		CvProjectInfo& kProject = GC.getProjectInfo((ProjectTypes)iProject);
		int iCount = getProjectCount((ProjectTypes)iProject);

		if (iCount < kProject.getVictoryMinThreshold(eVictory)) {
			FAssert(false);
			return -1;
		}

		if (iCount < kProject.getVictoryThreshold(eVictory)) {
			iExtraDelayPercent += ((kProject.getVictoryThreshold(eVictory) - iCount) * kProject.getVictoryDelayPercent()) / kProject.getVictoryThreshold(eVictory);
		}
	}

	return (GC.getGameINLINE().victoryDelay(eVictory) * (100 + iExtraDelayPercent)) / 100;
}

void CvTeam::setCanLaunch(VictoryTypes eVictory, bool bCan) {
	m_abCanLaunch[eVictory] = bCan;
}

bool CvTeam::canLaunch(VictoryTypes eVictory) const {
	return m_abCanLaunch[eVictory];
}

int CvTeam::getLaunchSuccessRate(VictoryTypes eVictory) const {
	int iSuccessRate = 100;
	for (int iProject = 0; iProject < GC.getNumProjectInfos(); ++iProject) {
		CvProjectInfo& kProject = GC.getProjectInfo((ProjectTypes)iProject);
		int iCount = getProjectCount((ProjectTypes)iProject);

		if (iCount < kProject.getVictoryMinThreshold(eVictory)) {
			return 0;
		}

		if (iCount < kProject.getVictoryThreshold(eVictory)) {
			if (kProject.getSuccessRate() > 0) {
				iSuccessRate -= (kProject.getSuccessRate() * (kProject.getVictoryThreshold(eVictory) - iCount));
			}
		}
	}

	return iSuccessRate;
}

void CvTeam::resetVictoryProgress() {
	for (int iI = 0; iI < GC.getNumVictoryInfos(); ++iI) {
		if (getVictoryCountdown((VictoryTypes)iI) >= 0 && GC.getGameINLINE().getGameState() == GAMESTATE_ON) {
			setVictoryCountdown((VictoryTypes)iI, -1);

			for (int iK = 0; iK < GC.getNumProjectInfos(); iK++) {
				if (GC.getProjectInfo((ProjectTypes)iK).getVictoryMinThreshold((VictoryTypes)iI) > 0) {
					changeProjectCount((ProjectTypes)iK, -getProjectCount((ProjectTypes)iK));
				}
			}

			CvWString szBuffer = gDLL->getText("TXT_KEY_VICTORY_RESET", getReplayName().GetCString(), GC.getVictoryInfo((VictoryTypes)iI).getTextKeyWide());

			for (int iJ = 0; iJ < MAX_PLAYERS; ++iJ) {
				CvPlayer& kPlayer = GET_PLAYER((PlayerTypes)iJ);
				if (kPlayer.isAlive()) {
					gDLL->getInterfaceIFace()->addHumanMessage(((PlayerTypes)iJ), false, GC.getEVENT_MESSAGE_TIME(), szBuffer, "AS2D_MELTDOWN", MESSAGE_TYPE_MAJOR_EVENT);

					if (kPlayer.getTeam() == getID()) {
						CvPopupInfo* pInfo = new CvPopupInfo();
						pInfo->setText(szBuffer);
						gDLL->getInterfaceIFace()->addPopup(pInfo, (PlayerTypes)iJ);
					}
				}
			}

			GC.getGameINLINE().addReplayMessage(REPLAY_MESSAGE_MAJOR_EVENT, getLeaderID(), szBuffer, -1, -1, (ColorTypes)GC.getInfoTypeForString("COLOR_HIGHLIGHT_TEXT"));
		}
	}
}

// K-Mod, code mvoed from CvPlayer::hasSpaceshipArrived. (it makes more sense to be here)
bool CvTeam::hasSpaceshipArrived() const {
	VictoryTypes eSpaceVictory = GC.getGameINLINE().getSpaceVictory();
	if (eSpaceVictory != NO_VICTORY) {
		int iVictoryCountdown = getVictoryCountdown(eSpaceVictory);
		if (iVictoryCountdown == 0 || (iVictoryCountdown > 0 && GC.getGameINLINE().getGameState() == GAMESTATE_EXTENDED)) {
			return true;
		}
	}

	return false;
}

bool CvTeam::isParent(TeamTypes eTeam) const {
	FAssert(eTeam != NO_TEAM);

	if (GET_TEAM(eTeam).isVassal(getID())) {
		for (int i = 0; i < MAX_PLAYERS; ++i) {
			CvPlayer& kLoopPlayer = GET_PLAYER((PlayerTypes)i);
			if (kLoopPlayer.isAlive() && kLoopPlayer.getTeam() == eTeam) {
				if (kLoopPlayer.getParent() != NO_PLAYER) {
					if (GET_PLAYER(kLoopPlayer.getParent()).getTeam() == getID()) {
						return true;
					}
				}
			}
		}
	}

	return false;
}

bool CvTeam::isHasTech(TechTypes eIndex) const {
	if (eIndex == NO_TECH) {
		return true;
	}

	FAssertMsg(eIndex >= 0, "eIndex is expected to be non-negative (invalid Index)");
	FAssertMsg(eIndex < GC.getNumTechInfos(), "eIndex is expected to be within maximum bounds (invalid Index)");
	FAssertMsg(m_pabHasTech != NULL, "m_pabHasTech is not expected to be equal with NULL");
	return m_pabHasTech[eIndex];
}

void CvTeam::announceTechToPlayers(TechTypes eIndex, bool bPartial) {
	bool bSound = ((GC.getGameINLINE().isNetworkMultiPlayer() || gDLL->getInterfaceIFace()->noTechSplash()) && !bPartial);

	for (int iI = 0; iI < MAX_PLAYERS; iI++) {
		if (GET_PLAYER((PlayerTypes)iI).isAlive()) {
			if (GET_PLAYER((PlayerTypes)iI).getTeam() == getID()) {
				CvWString szBuffer = gDLL->getText((bPartial ? "TXT_KEY_MISC_PROGRESS_TOWARDS_TECH" : "TXT_KEY_MISC_YOU_DISCOVERED_TECH"), GC.getTechInfo(eIndex).getTextKeyWide());

				//gDLL->getInterfaceIFace()->addHumanMessage(((PlayerTypes)iI), false, (bSound ? GC.getEVENT_MESSAGE_TIME() : -1), szBuffer, (bSound ? GC.getTechInfo(eIndex).getSoundMP() : NULL), MESSAGE_TYPE_MAJOR_EVENT, NULL, (ColorTypes)GC.getInfoTypeForString("COLOR_TECH_TEXT"));
				// K-Mod. Play the quote sound always, the "MP" sound is boring.
				gDLL->getInterfaceIFace()->addHumanMessage((PlayerTypes)iI, false, (bSound ? GC.getEVENT_MESSAGE_TIME() : -1), szBuffer, (bSound ? GC.getTechInfo(eIndex).getSound() : NULL), MESSAGE_TYPE_MAJOR_EVENT, NULL, (ColorTypes)GC.getInfoTypeForString("COLOR_TECH_TEXT"));
			}
		}
	}
}

void CvTeam::setHasTech(TechTypes eIndex, bool bNewValue, PlayerTypes ePlayer, bool bFirst, bool bAnnounce) {
	PROFILE_FUNC();

	CvWString szBuffer;

	if (eIndex == NO_TECH) {
		return;
	}

	if (ePlayer == NO_PLAYER) {
		ePlayer = getLeaderID();
	}

	FAssertMsg(eIndex >= 0, "eIndex is expected to be non-negative (invalid Index)");
	FAssertMsg(eIndex < GC.getNumTechInfos(), "eIndex is expected to be within maximum bounds (invalid Index)");
	FAssertMsg(ePlayer >= 0, "eIndex is expected to be non-negative (invalid Index)");
	FAssertMsg(ePlayer < MAX_PLAYERS, "ePlayer is expected to be within maximum bounds (invalid Index)");

	if (isHasTech(eIndex) != bNewValue) {
		if (GC.getTechInfo(eIndex).isRepeat()) {
			m_paiTechCount[eIndex]++;

			setResearchProgress(eIndex, 0, ePlayer);

			// report event to Python
			CvEventReporter::getInstance().techAcquired(eIndex, getID(), ePlayer, bAnnounce && 1 == m_paiTechCount[eIndex]);

			if (1 == m_paiTechCount[eIndex]) {
				if (bAnnounce) {
					if (GC.getGameINLINE().isFinalInitialized() && !(gDLL->GetWorldBuilderMode())) {
						announceTechToPlayers(eIndex);
					}
				}
			}
		} else {
			for (int iI = 0; iI < GC.getMapINLINE().numPlotsINLINE(); iI++) {
				CvPlot* pLoopPlot = GC.getMapINLINE().plotByIndexINLINE(iI);

				if (pLoopPlot->getBonusType() != NO_BONUS) {
					if (pLoopPlot->getTeam() == getID()) {
						if ((GC.getBonusInfo(pLoopPlot->getBonusType()).getTechReveal() == eIndex) ||
							(GC.getBonusInfo(pLoopPlot->getBonusType()).getTechCityTrade() == eIndex) ||
							(GC.getBonusInfo(pLoopPlot->getBonusType()).getTechObsolete() == eIndex)) {
							pLoopPlot->updatePlotGroupBonus(false);
						}
					}
				}
			}

			m_pabHasTech[eIndex] = bNewValue;

			for (int iI = 0; iI < GC.getMapINLINE().numPlotsINLINE(); iI++) {
				CvPlot* pLoopPlot = GC.getMapINLINE().plotByIndexINLINE(iI);

				if (pLoopPlot->getBonusType() != NO_BONUS) {
					if (pLoopPlot->getTeam() == getID()) {
						if ((GC.getBonusInfo(pLoopPlot->getBonusType()).getTechReveal() == eIndex) ||
							(GC.getBonusInfo(pLoopPlot->getBonusType()).getTechCityTrade() == eIndex) ||
							(GC.getBonusInfo(pLoopPlot->getBonusType()).getTechObsolete() == eIndex)) {
							pLoopPlot->updatePlotGroupBonus(true);
						}
					}
				}
			}
		}

		processTech(eIndex, ((bNewValue) ? 1 : -1));

		if (isHasTech(eIndex)) {
			if (gTeamLogLevel >= 2) {
				logBBAI("    Team %d (%S) acquires tech %S", getID(), getName().GetCString(), GC.getTechInfo(eIndex).getDescription());
			}

			for (int iI = 0; iI < MAX_PLAYERS; iI++) {
				if (GET_PLAYER((PlayerTypes)iI).getTeam() == getID()) {
					if (GET_PLAYER((PlayerTypes)iI).getCurrentEra() < GC.getTechInfo(eIndex).getEra()) {
						GET_PLAYER((PlayerTypes)iI).setCurrentEra((EraTypes)(GC.getTechInfo(eIndex).getEra()));
					}
				}
			}

			if (GC.getTechInfo(eIndex).isMapVisible()) {
				GC.getMapINLINE().setRevealedPlots(getID(), true, true);
			}

			for (int iI = 0; iI < GC.getNumSpecialBuildingInfos(); ++iI) {
				if (eIndex == GC.getSpecialBuildingInfo((SpecialBuildingTypes)iI).getTechPrereqAnyone()) {
					GC.getGameINLINE().makeSpecialBuildingValid((SpecialBuildingTypes)iI, bAnnounce);
				}
			}

			// report event to Python, along with some other key state
			CvEventReporter::getInstance().techAcquired(eIndex, getID(), ePlayer, bAnnounce);

			bool bReligionFounded = false;
			bool bFirstBonus = false;

			if (bFirst) {
				if (GC.getGameINLINE().countKnownTechNumTeams(eIndex) == 1) {
					CyArgsList argsList;
					argsList.add(getID());
					argsList.add(ePlayer);
					argsList.add(eIndex);
					argsList.add(bFirst);
					long lResult = 0;
					gDLL->getPythonIFace()->callFunction(PYGameModule, "doHolyCityTech", argsList.makeFunctionArgs(), &lResult);
					if (lResult != 1) {
						for (int iI = 0; iI < GC.getNumReligionInfos(); iI++) {
							if (GC.getReligionInfo((ReligionTypes)iI).getTechPrereq() == eIndex) {
								int iBestValue = MAX_INT;
								PlayerTypes eBestPlayer = NO_PLAYER;

								for (int iJ = 0; iJ < MAX_PLAYERS; iJ++) {
									if (GET_PLAYER((PlayerTypes)iJ).isAlive()) {
										if (GET_PLAYER((PlayerTypes)iJ).getTeam() == getID()) {
											int iValue = 10;

											iValue += GC.getGameINLINE().getSorenRandNum(10, "Found Religion (Player)");

											for (int iK = 0; iK < GC.getNumReligionInfos(); iK++) {
												iValue += (GET_PLAYER((PlayerTypes)iJ).getHasReligionCount((ReligionTypes)iK) * 10);
											}

											if (GET_PLAYER((PlayerTypes)iJ).getCurrentResearch() != eIndex) {
												iValue *= 10;
											}

											if (iValue < iBestValue) {
												iBestValue = iValue;
												eBestPlayer = ((PlayerTypes)iJ);
											}
										}
									}
								}

								if (eBestPlayer != NO_PLAYER) {
									GC.getGameINLINE().setReligionSlotTaken((ReligionTypes)iI, true);

									if (GC.getGameINLINE().isOption(GAMEOPTION_PICK_RELIGION)) {
										if (GET_PLAYER(eBestPlayer).isHuman()) {
											CvPopupInfo* pInfo = new CvPopupInfo(BUTTONPOPUP_FOUND_RELIGION, iI);
											if (NULL != pInfo) {
												gDLL->getInterfaceIFace()->addPopup(pInfo, eBestPlayer);
											}
										} else {
											ReligionTypes eReligion = GET_PLAYER(eBestPlayer).AI_chooseReligion();
											if (NO_RELIGION != eReligion) {
												GET_PLAYER(eBestPlayer).foundReligion(eReligion, (ReligionTypes)iI, true);
											}
										}
									} else {
										GET_PLAYER(eBestPlayer).foundReligion((ReligionTypes)iI, (ReligionTypes)iI, true);
									}

									bReligionFounded = true;
									bFirstBonus = true;
								}
							}
						}

						for (int iI = 0; iI < GC.getNumCorporationInfos(); ++iI) {
							if (GC.getCorporationInfo((CorporationTypes)iI).getTechPrereq() == eIndex) {
								if (!(GC.getGameINLINE().isCorporationFounded((CorporationTypes)iI))) {
									int iBestValue = MAX_INT;
									PlayerTypes eBestPlayer = NO_PLAYER;

									for (int iJ = 0; iJ < MAX_PLAYERS; iJ++) {
										if (GET_PLAYER((PlayerTypes)iJ).isAlive()) {
											if (GET_PLAYER((PlayerTypes)iJ).getTeam() == getID()) {
												int iValue = 10;

												iValue += GC.getGameINLINE().getSorenRandNum(10, "Found Corporation (Player)");

												if (GET_PLAYER((PlayerTypes)iJ).getCurrentResearch() != eIndex) {
													iValue *= 10;
												}

												if (iValue < iBestValue) {
													iBestValue = iValue;
													eBestPlayer = ((PlayerTypes)iJ);
												}
											}
										}
									}

									if (eBestPlayer != NO_PLAYER) {
										GET_PLAYER(eBestPlayer).foundCorporation((CorporationTypes)iI);
										bFirstBonus = true;
									}
								}
							}
						}
					}
				}
			}

			for (int iI = 0; iI < MAX_PLAYERS; iI++) {
				if (GET_PLAYER((PlayerTypes)iI).getTeam() == getID()) {
					if (GET_PLAYER((PlayerTypes)iI).isResearchingTech(eIndex)) {
						GET_PLAYER((PlayerTypes)iI).popResearch(eIndex);
					}

					// notify the player they now have the tech, if they want to make immediate changes
					GET_PLAYER((PlayerTypes)iI).AI_nowHasTech(eIndex);

					GET_PLAYER((PlayerTypes)iI).invalidateYieldRankCache();
				}
			}

			if (bFirst) {
				if (GC.getGameINLINE().countKnownTechNumTeams(eIndex) == 1) {
					UnitTypes eFreeUnit = GET_PLAYER(ePlayer).getTechFreeUnit(eIndex, true);
					if (eFreeUnit != NO_UNIT) {
						bFirstBonus = true;
						CvCity* pCapitalCity = GET_PLAYER(ePlayer).getCapitalCity();
						if (pCapitalCity != NULL) {
							pCapitalCity->createGreatPeople(eFreeUnit, false, false);
						}
					}

					if (GC.getTechInfo(eIndex).getFirstFreeTechs() > 0) {
						bFirstBonus = true;

						if (!isHuman()) {
							for (int iI = 0; iI < GC.getTechInfo(eIndex).getFirstFreeTechs(); iI++) {
								GET_PLAYER(ePlayer).AI_chooseFreeTech();
							}
						} else {
							szBuffer = gDLL->getText("TXT_KEY_MISC_FIRST_TECH_CHOOSE_FREE", GC.getTechInfo(eIndex).getTextKeyWide());
							GET_PLAYER(ePlayer).chooseTech(GC.getTechInfo(eIndex).getFirstFreeTechs(), szBuffer.GetCString());
						}

						for (int iI = 0; iI < MAX_PLAYERS; iI++) {
							if (GET_PLAYER((PlayerTypes)iI).isAlive()) {
								if (isHasMet(GET_PLAYER((PlayerTypes)iI).getTeam())) {
									szBuffer = gDLL->getText("TXT_KEY_MISC_SOMEONE_FIRST_TO_TECH", GET_PLAYER(ePlayer).getNameKey(), GC.getTechInfo(eIndex).getTextKeyWide());
								} else {
									szBuffer = gDLL->getText("TXT_KEY_MISC_UNKNOWN_FIRST_TO_TECH", GC.getTechInfo(eIndex).getTextKeyWide());
								}
								gDLL->getInterfaceIFace()->addHumanMessage(((PlayerTypes)iI), false, GC.getEVENT_MESSAGE_TIME(), szBuffer, "AS2D_FIRSTTOTECH", MESSAGE_TYPE_MAJOR_EVENT, NULL, (ColorTypes)GC.getInfoTypeForString("COLOR_HIGHLIGHT_TEXT"));
							}
						}

						szBuffer = gDLL->getText("TXT_KEY_MISC_SOMEONE_FIRST_TO_TECH", GET_PLAYER(ePlayer).getReplayName(), GC.getTechInfo(eIndex).getTextKeyWide());
						GC.getGameINLINE().addReplayMessage(REPLAY_MESSAGE_MAJOR_EVENT, ePlayer, szBuffer, -1, -1, (ColorTypes)GC.getInfoTypeForString("COLOR_HIGHLIGHT_TEXT"));
					}

					if (bFirstBonus) {
						for (int iI = 0; iI < MAX_PLAYERS; iI++) {
							if (GET_PLAYER((PlayerTypes)iI).isAlive()) {
								if (!(GET_PLAYER((PlayerTypes)iI).isHuman())) {
									if (GET_PLAYER((PlayerTypes)iI).isResearchingTech(eIndex)) {
										GET_PLAYER((PlayerTypes)iI).clearResearchQueue(); // K-Mod note: we just want to flag it for re-evaluation. Clearing the queue is currently the only way to do that.
									}
								}
							}
						}
					}
				}
			}

			if (bAnnounce) {
				if (GC.getGameINLINE().isFinalInitialized() && !(gDLL->GetWorldBuilderMode())) {
					announceTechToPlayers(eIndex);

					for (int iI = 0; iI < GC.getMapINLINE().numPlotsINLINE(); iI++) {
						CvPlot* pLoopPlot = GC.getMapINLINE().plotByIndexINLINE(iI);

						if (pLoopPlot->getTeam() == getID()) {
							BonusTypes eBonus = pLoopPlot->getBonusType();

							if (eBonus != NO_BONUS) {
								if (GC.getBonusInfo(eBonus).getTechReveal() == eIndex && !isForceRevealedBonus(eBonus)) {
									CvCity* pCity = GC.getMapINLINE().findCity(pLoopPlot->getX_INLINE(), pLoopPlot->getY_INLINE(), NO_PLAYER, getID(), false);

									if (pCity != NULL) {
										szBuffer = gDLL->getText("TXT_KEY_MISC_YOU_DISCOVERED_BONUS", GC.getBonusInfo(eBonus).getTextKeyWide(), pCity->getNameKey());
										gDLL->getInterfaceIFace()->addHumanMessage(pLoopPlot->getOwnerINLINE(), false, GC.getEVENT_MESSAGE_TIME(), szBuffer, "AS2D_DISCOVERBONUS", MESSAGE_TYPE_INFO, GC.getBonusInfo(eBonus).getButton(), (ColorTypes)GC.getInfoTypeForString("COLOR_WHITE"), pLoopPlot->getX_INLINE(), pLoopPlot->getY_INLINE(), true, true);
									}
								}
							}
						}
					}

					for (int iI = 0; iI < MAX_PLAYERS; iI++) {
						if (GET_PLAYER((PlayerTypes)iI).isAlive()) {
							if (GET_PLAYER((PlayerTypes)iI).getTeam() == getID()) {
								if (GET_PLAYER((PlayerTypes)iI).isHuman()) {
									if (!bReligionFounded || (GET_PLAYER((PlayerTypes)iI).getLastStateReligion() != NO_RELIGION) || (iI != ePlayer)) {
										if (GET_PLAYER((PlayerTypes)iI).canRevolution(NULL)) {
											CivicOptionTypes eCivicOptionType = NO_CIVICOPTION;
											CivicTypes eCivicType = NO_CIVIC;

											for (int iJ = 0; iJ < GC.getNumCivicOptionInfos(); iJ++) {
												if (!(GET_PLAYER((PlayerTypes)iI).isHasCivicOption((CivicOptionTypes)iJ))) {
													for (int iK = 0; iK < GC.getNumCivicInfos(); iK++) {
														if (GC.getCivicInfo((CivicTypes)iK).getCivicOptionType() == iJ) {
															if (GC.getCivicInfo((CivicTypes)iK).getTechPrereq() == eIndex) {
																eCivicOptionType = ((CivicOptionTypes)iJ);
																eCivicType = ((CivicTypes)iK);
															}
														}
													}
												}
											}

											if ((eCivicOptionType != NO_CIVICOPTION) && (eCivicType != NO_CIVIC)) {
												CvPopupInfo* pInfo = new CvPopupInfo(BUTTONPOPUP_CHANGECIVIC);
												if (NULL != pInfo) {
													pInfo->setData1(eCivicOptionType);
													pInfo->setData2(eCivicType);
													gDLL->getInterfaceIFace()->addPopup(pInfo, (PlayerTypes)iI);
												}
											}
										}
									}
								}
							}
						}
					}
				}
			}

			for (int iI = 0; iI < MAX_TEAMS; iI++) {
				if (GET_TEAM((TeamTypes)iI).isAlive()) {
					if (iI != getID()) {
						GET_TEAM((TeamTypes)iI).updateTechShare(eIndex);
					}
				}
			}
		}

		if (bNewValue) {
			if (bAnnounce) {
				if (GC.getGameINLINE().isFinalInitialized() && !(gDLL->GetWorldBuilderMode())) {
					FAssert(ePlayer != NO_PLAYER);
					if (GET_PLAYER(ePlayer).isResearch() && GET_PLAYER(ePlayer).getCurrentResearch() == NO_TECH && GET_PLAYER(ePlayer).isHuman()) // K-Mod
					{
						szBuffer = gDLL->getText("TXT_KEY_MISC_WHAT_TO_RESEARCH_NEXT");
						GET_PLAYER(ePlayer).chooseTech(0, szBuffer);
					}
				}
			}
		}

		if (getID() == GC.getGameINLINE().getActiveTeam()) {
			gDLL->getInterfaceIFace()->setDirty(MiscButtons_DIRTY_BIT, true);
			gDLL->getInterfaceIFace()->setDirty(SelectionButtons_DIRTY_BIT, true);
			gDLL->getInterfaceIFace()->setDirty(ResearchButtons_DIRTY_BIT, true);
			gDLL->getInterfaceIFace()->setDirty(GlobeLayer_DIRTY_BIT, true);
		}
	}
}


bool CvTeam::isNoTradeTech(TechTypes eIndex) const {
	FAssertMsg(eIndex >= 0, "eIndex is expected to be non-negative (invalid Index)");
	FAssertMsg(eIndex < GC.getNumTechInfos(), "eIndex is expected to be within maximum bounds (invalid Index)");
	return m_pabNoTradeTech[eIndex];
}


void CvTeam::setNoTradeTech(TechTypes eIndex, bool bNewValue) {
	FAssertMsg(eIndex >= 0, "eIndex is expected to be non-negative (invalid Index)");
	FAssertMsg(eIndex < GC.getNumTechInfos(), "eIndex is expected to be within maximum bounds (invalid Index)");
	m_pabNoTradeTech[eIndex] = bNewValue;
}


int CvTeam::getImprovementYieldChange(ImprovementTypes eIndex1, YieldTypes eIndex2) const {
	FAssertMsg(eIndex1 >= 0, "eIndex1 is expected to be non-negative (invalid Index)");
	FAssertMsg(eIndex1 < GC.getNumImprovementInfos(), "eIndex1 is expected to be within maximum bounds (invalid Index)");
	FAssertMsg(eIndex2 >= 0, "eIndex2 is expected to be non-negative (invalid Index)");
	FAssertMsg(eIndex2 < NUM_YIELD_TYPES, "eIndex2 is expected to be within maximum bounds (invalid Index)");
	return m_ppaaiImprovementYieldChange[eIndex1][eIndex2];
}


void CvTeam::changeImprovementYieldChange(ImprovementTypes eIndex1, YieldTypes eIndex2, int iChange) {
	FAssertMsg(eIndex1 >= 0, "eIndex1 is expected to be non-negative (invalid Index)");
	FAssertMsg(eIndex1 < GC.getNumImprovementInfos(), "eIndex1 is expected to be within maximum bounds (invalid Index)");
	FAssertMsg(eIndex2 >= 0, "eIndex2 is expected to be non-negative (invalid Index)");
	FAssertMsg(eIndex2 < NUM_YIELD_TYPES, "eIndex2 is expected to be within maximum bounds (invalid Index)");

	if (iChange != 0) {
		m_ppaaiImprovementYieldChange[eIndex1][eIndex2] = (m_ppaaiImprovementYieldChange[eIndex1][eIndex2] + iChange);
		FAssert(getImprovementYieldChange(eIndex1, eIndex2) >= 0);

		updateYield();
	}
}

// K-Mod. In the original code, there seems to be a lot of confusion about what the exact conditions are for a bonus being connected.
// There were heaps of bugs where CvImprovementInfo::isImprovementBonusTrade was mistakenly used as the sole condition for a bonus being connected or not.
// I created this function to make the situation a bit more clear...
bool CvTeam::doesImprovementConnectBonus(ImprovementTypes eImprovement, BonusTypes eBonus) const {
	FAssert(eImprovement < GC.getNumImprovementInfos());
	FAssert(eBonus < GC.getNumBonusInfos());

	if (eImprovement == NO_IMPROVEMENT || eBonus == NO_BONUS)
		return false;

	const CvImprovementInfo& kImprovementInfo = GC.getImprovementInfo(eImprovement);
	const CvBonusInfo& kBonusInfo = GC.getBonusInfo(eBonus);

	if (!isHasTech((TechTypes)kBonusInfo.getTechCityTrade()) || (kBonusInfo.getTechObsolete() != NO_TECH && isHasTech((TechTypes)kBonusInfo.getTechObsolete())))
		return false;

	return kImprovementInfo.isImprovementBonusTrade(eBonus) || kImprovementInfo.isActsAsCity();
}

bool CvTeam::isFriendlyTerritory(TeamTypes eTeam) const {
	if (eTeam == NO_TEAM) {
		return false;
	}

	if (eTeam == getID()) {
		return true;
	}

	if (GET_TEAM(eTeam).isVassal(getID())) {
		return true;
	}

	if (isVassal(eTeam) && isOpenBorders(eTeam)) {
		return true;
	}

	return false;
}

int CvTeam::getEspionagePointsAgainstTeam(TeamTypes eIndex) const {
	FAssertMsg(eIndex >= 0, "eIndex is expected to be non-negative (invalid Index)");
	FAssertMsg(eIndex < MAX_TEAMS, "eIndex is expected to be within maximum bounds (invalid Index)");
	return m_aiEspionagePointsAgainstTeam[eIndex];
}

void CvTeam::setEspionagePointsAgainstTeam(TeamTypes eIndex, int iValue) {
	FAssertMsg(eIndex >= 0, "eIndex is expected to be non-negative (invalid Index)");
	FAssertMsg(eIndex < MAX_TEAMS, "eIndex is expected to be within maximum bounds (invalid Index)");

	if (iValue != getEspionagePointsAgainstTeam(eIndex)) {
		m_aiEspionagePointsAgainstTeam[eIndex] = iValue;

		verifySpyUnitsValidPlot();
		GET_TEAM(eIndex).verifySpyUnitsValidPlot();
	}
}

void CvTeam::changeEspionagePointsAgainstTeam(TeamTypes eIndex, int iChange) {
	FAssertMsg(eIndex >= 0, "eIndex is expected to be non-negative (invalid Index)");
	FAssertMsg(eIndex < MAX_TEAMS, "eIndex is expected to be within maximum bounds (invalid Index)");

	setEspionagePointsAgainstTeam(eIndex, getEspionagePointsAgainstTeam(eIndex) + iChange);
}

// K-Mod
int CvTeam::getTotalUnspentEspionage() const {
	int iTotal = 0;
	for (int i = 0; i < MAX_CIV_TEAMS; i++) {
		iTotal += getEspionagePointsAgainstTeam((TeamTypes)i);
	}
	return iTotal;
}

int CvTeam::getEspionagePointsEver() const {
	return m_iEspionagePointsEver;
}

void CvTeam::setEspionagePointsEver(int iValue) {
	if (iValue != getEspionagePointsEver()) {
		m_iEspionagePointsEver = iValue;
	}
}

void CvTeam::changeEspionagePointsEver(int iChange) {
	setEspionagePointsEver(getEspionagePointsEver() + iChange);
}

int CvTeam::getCounterespionageTurnsLeftAgainstTeam(TeamTypes eIndex) const {
	FAssertMsg(eIndex >= 0, "eIndex is expected to be non-negative (invalid Index)");
	FAssertMsg(eIndex < MAX_TEAMS, "eIndex is expected to be within maximum bounds (invalid Index)");
	return m_aiCounterespionageTurnsLeftAgainstTeam[eIndex];
}

void CvTeam::setCounterespionageTurnsLeftAgainstTeam(TeamTypes eIndex, int iValue) {
	FAssertMsg(eIndex >= 0, "eIndex is expected to be non-negative (invalid Index)");
	FAssertMsg(eIndex < MAX_TEAMS, "eIndex is expected to be within maximum bounds (invalid Index)");

	if (iValue != getCounterespionageTurnsLeftAgainstTeam(eIndex)) {
		m_aiCounterespionageTurnsLeftAgainstTeam[eIndex] = iValue;

		gDLL->getInterfaceIFace()->setDirty(Espionage_Advisor_DIRTY_BIT, true);
	}
}

void CvTeam::changeCounterespionageTurnsLeftAgainstTeam(TeamTypes eIndex, int iChange) {
	FAssertMsg(eIndex >= 0, "eIndex is expected to be non-negative (invalid Index)");
	FAssertMsg(eIndex < MAX_TEAMS, "eIndex is expected to be within maximum bounds (invalid Index)");

	setCounterespionageTurnsLeftAgainstTeam(eIndex, getCounterespionageTurnsLeftAgainstTeam(eIndex) + iChange);
}

int CvTeam::getCounterespionageModAgainstTeam(TeamTypes eIndex) const {
	FAssertMsg(eIndex >= 0, "eIndex is expected to be non-negative (invalid Index)");
	FAssertMsg(eIndex < MAX_TEAMS, "eIndex is expected to be within maximum bounds (invalid Index)");
	return m_aiCounterespionageModAgainstTeam[eIndex];
}

void CvTeam::setCounterespionageModAgainstTeam(TeamTypes eIndex, int iValue) {
	FAssertMsg(eIndex >= 0, "eIndex is expected to be non-negative (invalid Index)");
	FAssertMsg(eIndex < MAX_TEAMS, "eIndex is expected to be within maximum bounds (invalid Index)");

	if (iValue != getCounterespionageModAgainstTeam(eIndex)) {
		m_aiCounterespionageModAgainstTeam[eIndex] = iValue;

		gDLL->getInterfaceIFace()->setDirty(Espionage_Advisor_DIRTY_BIT, true);
	}
}

void CvTeam::changeCounterespionageModAgainstTeam(TeamTypes eIndex, int iChange) {
	FAssertMsg(eIndex >= 0, "eIndex is expected to be non-negative (invalid Index)");
	FAssertMsg(eIndex < MAX_TEAMS, "eIndex is expected to be within maximum bounds (invalid Index)");

	setCounterespionageModAgainstTeam(eIndex, getCounterespionageModAgainstTeam(eIndex) + iChange);
}

void CvTeam::verifySpyUnitsValidPlot() {
	std::vector<CvUnit*> aUnits;

	for (int iPlayer = 0; iPlayer < MAX_PLAYERS; ++iPlayer) {
		CvPlayer& kPlayer = GET_PLAYER((PlayerTypes)iPlayer);

		if (kPlayer.isAlive() && kPlayer.getTeam() == getID()) {
			int iLoop;
			for (CvUnit* pUnit = kPlayer.firstUnit(&iLoop); pUnit != NULL; pUnit = kPlayer.nextUnit(&iLoop)) {
				PlayerTypes eOwner = pUnit->plot()->getOwnerINLINE();
				if (NO_PLAYER != eOwner) {
					if (pUnit->isSpy()) {
						if (!kPlayer.canSpiesEnterBorders(eOwner)) {
							aUnits.push_back(pUnit);
						}
					}
				}
			}
		}
	}

	for (uint i = 0; i < aUnits.size(); ++i) {
		aUnits[i]->jumpToNearestValidPlot();
	}
}

void CvTeam::setForceRevealedBonus(BonusTypes eBonus, bool bRevealed) {
	if (isForceRevealedBonus(eBonus) == bRevealed) {
		return;
	}

	for (int iI = 0; iI < GC.getMapINLINE().numPlotsINLINE(); ++iI) {
		CvPlot* pLoopPlot = GC.getMapINLINE().plotByIndexINLINE(iI);

		if (pLoopPlot->getBonusType() == eBonus) {
			if (pLoopPlot->getTeam() == getID()) {
				pLoopPlot->updatePlotGroupBonus(false);
			}
		}
	}

	if (bRevealed) {
		m_aeRevealedBonuses.push_back(eBonus);
	} else {
		std::vector<BonusTypes>::iterator it;

		for (it = m_aeRevealedBonuses.begin(); it != m_aeRevealedBonuses.end(); ++it) {
			if (*it == eBonus) {
				m_aeRevealedBonuses.erase(it);
				break;
			}
		}
	}

	for (int iI = 0; iI < GC.getMapINLINE().numPlotsINLINE(); ++iI) {
		CvPlot* pLoopPlot = GC.getMapINLINE().plotByIndexINLINE(iI);

		if (pLoopPlot->getBonusType() == eBonus) {
			if (pLoopPlot->getTeam() == getID()) {
				pLoopPlot->updatePlotGroupBonus(true);
			}
		}
	}

	for (int iI = 0; iI < GC.getMapINLINE().numPlotsINLINE(); ++iI) {
		CvPlot* pLoopPlot = GC.getMapINLINE().plotByIndexINLINE(iI);

		if (pLoopPlot->getBonusType() == eBonus) {
			pLoopPlot->updateYield();
			pLoopPlot->setLayoutDirty(true);
		}
	}
}

bool CvTeam::isForceRevealedBonus(BonusTypes eBonus) const {
	std::vector<BonusTypes>::const_iterator it;

	for (it = m_aeRevealedBonuses.begin(); it != m_aeRevealedBonuses.end(); ++it) {
		if (*it == eBonus) {
			return true;
		}
	}

	return false;
}

// K-Mod
bool CvTeam::isBonusRevealed(BonusTypes eBonus) const {
	FAssert(eBonus >= 0 && eBonus < GC.getNumBonusInfos());
	return isHasTech((TechTypes)GC.getBonusInfo(eBonus).getTechReveal()) || isForceRevealedBonus(eBonus);
}

int CvTeam::countNumHumanGameTurnActive() const {
	int iCount = 0;

	for (int iI = 0; iI < MAX_CIV_PLAYERS; iI++) {
		CvPlayer& kLoopPlayer = GET_PLAYER((PlayerTypes)iI);

		if (kLoopPlayer.isHuman() && kLoopPlayer.getTeam() == getID()) {
			if (kLoopPlayer.isTurnActive()) {
				++iCount;
			}
		}
	}

	return iCount;
}

void CvTeam::setTurnActive(bool bNewValue, bool bDoTurn) {
	FAssert(GC.getGameINLINE().isSimultaneousTeamTurns());

	for (int iPlayer = 0; iPlayer < MAX_PLAYERS; ++iPlayer) {
		CvPlayer& kPlayer = GET_PLAYER((PlayerTypes)iPlayer);
		if (kPlayer.isAlive() && kPlayer.getTeam() == getID()) {
			kPlayer.setTurnActive(bNewValue, bDoTurn);
		}
	}
}

bool CvTeam::isTurnActive() const {
	FAssert(GC.getGameINLINE().isSimultaneousTeamTurns());

	for (int iPlayer = 0; iPlayer < MAX_PLAYERS; ++iPlayer) {
		CvPlayer& kPlayer = GET_PLAYER((PlayerTypes)iPlayer);
		if (kPlayer.getTeam() == getID()) {
			if (kPlayer.isTurnActive()) {
				return true;
			}
		}
	}

	return false;
}

// Protected Functions...

void CvTeam::doWarWeariness() {
	for (int iI = 0; iI < MAX_TEAMS; iI++) {
		if (getWarWeariness((TeamTypes)iI) > 0) {
			changeWarWeariness(((TeamTypes)iI), 100 * GC.getDefineINT("WW_DECAY_RATE"));

			if (!(GET_TEAM((TeamTypes)iI).isAlive()) || !isAtWar((TeamTypes)iI) || GC.getGameINLINE().isOption(GAMEOPTION_ALWAYS_WAR) || GC.getGameINLINE().isOption(GAMEOPTION_NO_CHANGING_WAR_PEACE)) {
				setWarWeariness(((TeamTypes)iI), ((getWarWeariness((TeamTypes)iI) * GC.getDefineINT("WW_DECAY_PEACE_PERCENT")) / 100));
			}
		}
	}
}

void CvTeam::updateTechShare(TechTypes eTech) {
	if (isHasTech(eTech)) {
		return;
	}

	int iBestShare = MAX_INT;

	for (int iI = 0; iI < MAX_TEAMS; iI++) {
		if (isTechShare(iI)) {
			iBestShare = std::min(iBestShare, (iI + 1));
		}
	}

	if (iBestShare != MAX_INT) {
		int iCount = 0;

		for (int iI = 0; iI < MAX_CIV_TEAMS; iI++) {
			if (GET_TEAM((TeamTypes)iI).isAlive()) {
				if (GET_TEAM((TeamTypes)iI).isHasTech(eTech)) {
					if (isHasMet((TeamTypes)iI)) {
						FAssertMsg(iI != getID(), "iI is not expected to be equal with getID()");
						iCount++;
					}
				}
			}
		}

		if (iCount >= iBestShare) {
			setHasTech(eTech, true, NO_PLAYER, true, true);
		}
	}
}


void CvTeam::updateTechShare() {
	for (int iI = 0; iI < GC.getNumTechInfos(); iI++) {
		updateTechShare((TechTypes)iI);
	}
}


void CvTeam::testCircumnavigated() {
	if (isBarbarian()) {
		return;
	}

	if (!GC.getGameINLINE().circumnavigationAvailable()) {
		return;
	}

	if (GC.getMapINLINE().isWrapXINLINE()) {
		for (int iX = 0; iX < GC.getMapINLINE().getGridWidthINLINE(); iX++) {
			bool bFoundVisible = false;

			for (int iY = 0; iY < GC.getMapINLINE().getGridHeightINLINE(); iY++) {
				CvPlot* pPlot = GC.getMapINLINE().plotSorenINLINE(iX, iY);

				if (pPlot->isRevealed(getID(), false)) {
					bFoundVisible = true;
					break;
				}
			}

			if (!bFoundVisible) {
				return;
			}
		}
	}

	if (GC.getMapINLINE().isWrapYINLINE()) {
		for (int iY = 0; iY < GC.getMapINLINE().getGridHeightINLINE(); iY++) {
			bool bFoundVisible = false;

			for (int iX = 0; iX < GC.getMapINLINE().getGridWidthINLINE(); iX++) {
				CvPlot* pPlot = GC.getMapINLINE().plotSorenINLINE(iX, iY);

				if (pPlot->isRevealed(getID(), false)) {
					bFoundVisible = true;
					break;
				}
			}

			if (!bFoundVisible) {
				return;
			}
		}
	}

	GC.getGameINLINE().makeCircumnavigated();

	if (GC.getGameINLINE().getElapsedGameTurns() > 1) // K-Mod (due to changes in when CvTeam::doTurn is called)
	{
		if (GC.getDefineINT("CIRCUMNAVIGATE_FREE_MOVES") != 0) {
			CvWString szBuffer;
			changeExtraMoves(DOMAIN_SEA, GC.getDefineINT("CIRCUMNAVIGATE_FREE_MOVES"));

			for (int iI = 0; iI < MAX_PLAYERS; iI++) {
				if (GET_PLAYER((PlayerTypes)iI).isAlive()) {
					if (getID() == GET_PLAYER((PlayerTypes)iI).getTeam()) {
						szBuffer = gDLL->getText("TXT_KEY_MISC_YOU_CIRC_GLOBE", GC.getDefineINT("CIRCUMNAVIGATE_FREE_MOVES"));
					} else if (isHasMet(GET_PLAYER((PlayerTypes)iI).getTeam())) {
						szBuffer = gDLL->getText("TXT_KEY_MISC_SOMEONE_CIRC_GLOBE", getName().GetCString());
					} else {
						szBuffer = gDLL->getText("TXT_KEY_MISC_UNKNOWN_CIRC_GLOBE");
					}
					gDLL->getInterfaceIFace()->addHumanMessage(((PlayerTypes)iI), false, GC.getEVENT_MESSAGE_TIME(), szBuffer, "AS2D_GLOBECIRCUMNAVIGATED", MESSAGE_TYPE_MAJOR_EVENT, NULL, (ColorTypes)GC.getInfoTypeForString("COLOR_HIGHLIGHT_TEXT"));
				}
			}

			szBuffer = gDLL->getText("TXT_KEY_MISC_SOMEONE_CIRC_GLOBE", getReplayName().GetCString());
			GC.getGameINLINE().addReplayMessage(REPLAY_MESSAGE_MAJOR_EVENT, getLeaderID(), szBuffer, -1, -1, (ColorTypes)GC.getInfoTypeForString("COLOR_HIGHLIGHT_TEXT"));
		}
	}
}

void CvTeam::processTech(TechTypes eTech, int iChange) {
	PROFILE_FUNC();

	const CvTechInfo& kTech = GC.getTechInfo(eTech);

	if (kTech.isCanPassPeaks()) {
		changeCanPassPeaksCount(iChange);
	}

	if (kTech.isMoveFastPeaks()) {
		changeMoveFastPeaksCount(iChange);
	}

	if (kTech.isCanFoundOnPeaks()) {
		changeCanFoundOnPeaksCount(iChange);
		//	Koshling - makes peaks workable which changes the yield calculation
		updateYield();
	}

	if (kTech.isExtraWaterSeeFrom()) {
		changeExtraWaterSeeFromCount(iChange);
	}

	if (kTech.isMapCentering()) {
		if (iChange > 0) {
			setMapCentering(true);
		}
	}

	if (kTech.isMapTrading()) {
		changeMapTradingCount(iChange);
	}

	if (kTech.isTechTrading()) {
		changeTechTradingCount(iChange);
	}

	if (kTech.isGoldTrading()) {
		changeGoldTradingCount(iChange);
	}

	if (kTech.isOpenBordersTrading()) {
		changeOpenBordersTradingCount(iChange);
	}

	if (kTech.isDefensivePactTrading()) {
		changeDefensivePactTradingCount(iChange);
	}

	if (kTech.isPermanentAllianceTrading()) {
		changePermanentAllianceTradingCount(iChange);
	}

	if (kTech.isVassalStateTrading()) {
		changeVassalTradingCount(iChange);
	}

	if (kTech.isBridgeBuilding()) {
		changeBridgeBuildingCount(iChange);
	}

	if (kTech.isIrrigation()) {
		changeIrrigationCount(iChange);
	}

	if (kTech.isIgnoreIrrigation()) {
		changeIgnoreIrrigationCount(iChange);
	}

	if (kTech.isWaterWork()) {
		changeWaterWorkCount(iChange);
	}

	if (kTech.isEmbassyTrading()) {
		changeEmbassyTradingCount(iChange);
	}

	if (kTech.isLimitedBordersTrading()) {
		changeLimitedBordersTradingCount(iChange);
	}

	if (kTech.isFreeTradeAgreementTrading()) {
		changeFreeTradeAgreementTradingCount(iChange);
	}

	if (kTech.isNonAggressionTrading()) {
		changeNonAggressionTradingCount(iChange);
	}

	for (RouteTypes eRoute = (RouteTypes)0; eRoute < GC.getNumRouteInfos(); eRoute = (RouteTypes)(eRoute + 1)) {
		changeRouteChange(eRoute, (GC.getRouteInfo(eRoute).getTechMovementChange(eTech) * iChange));
	}

	for (DomainTypes eDomain = (DomainTypes)0; eDomain < NUM_DOMAIN_TYPES; eDomain = (DomainTypes)(eDomain + 1)) {
		changeExtraMoves(eDomain, (kTech.getDomainExtraMoves(eDomain) * iChange));
	}

	for (CommerceTypes eCommerce = (CommerceTypes)0; eCommerce < NUM_COMMERCE_TYPES; eCommerce = (CommerceTypes)(eCommerce + 1)) {
		if (kTech.isCommerceFlexible(eCommerce)) {
			changeCommerceFlexibleCount(eCommerce, iChange);
		}
	}

	for (TerrainTypes eTerrain = (TerrainTypes)0; eTerrain < GC.getNumTerrainInfos(); eTerrain = (TerrainTypes)(eTerrain + 1)) {
		if (kTech.isTerrainTrade(eTerrain)) {
			changeTerrainTradeCount(eTerrain, iChange);
		}
	}

	if (kTech.isRiverTrade()) {
		changeRiverTradeCount(iChange);
	}

	for (BuildingTypes eBuilding = (BuildingTypes)0; eBuilding < GC.getNumBuildingInfos(); eBuilding = (BuildingTypes)(eBuilding + 1)) {
		const CvBuildingInfo& kBuilding = GC.getBuildingInfo(eBuilding);
		if (kBuilding.getObsoleteTech() == eTech) {
			changeObsoleteBuildingCount(eBuilding, iChange);
		}

		if (kBuilding.getSpecialBuildingType() != NO_SPECIALBUILDING) {
			if (GC.getSpecialBuildingInfo((SpecialBuildingTypes)kBuilding.getSpecialBuildingType()).getObsoleteTech() == eTech) {
				changeObsoleteBuildingCount(eBuilding, iChange);
			}
		}

		if (kBuilding.isAnyTechCommerceChange()) {
			for (CommerceTypes eCommerce = (CommerceTypes)0; eCommerce < NUM_COMMERCE_TYPES; eCommerce = (CommerceTypes)(eCommerce + 1)) {
				changeBuildingCommerceChange(eBuilding, eCommerce, (kBuilding.getTechCommerceChange(eTech, eCommerce) * iChange));
			}
		}

		if (kBuilding.isAnyTechYieldChange()) {
			for (YieldTypes eYield = (YieldTypes)0; eYield < NUM_YIELD_TYPES; eYield = (YieldTypes)(eYield + 1)) {
				changeBuildingYieldChange(eBuilding, eYield, (kBuilding.getTechYieldChange(eTech, eYield) * iChange));
			}
		}
	}

	for (ImprovementTypes eImprovement = (ImprovementTypes)0; eImprovement < GC.getNumImprovementInfos(); eImprovement = (ImprovementTypes)(eImprovement + 1)) {
		for (YieldTypes eYield = (YieldTypes)0; eYield < NUM_YIELD_TYPES; eYield = (YieldTypes)(eYield + 1)) {
			changeImprovementYieldChange(eImprovement, eYield, (GC.getImprovementInfo(eImprovement).getTechYieldChanges(eTech, eYield) * iChange));
		}
	}

	for (PlayerTypes ePlayer = (PlayerTypes)0; ePlayer < MAX_PLAYERS; ePlayer = (PlayerTypes)(ePlayer + 1)) {
		CvPlayer& kPlayer = GET_PLAYER(ePlayer);
		if (kPlayer.getTeam() == getID()) {
			kPlayer.changeFeatureProductionModifier(kTech.getFeatureProductionModifier() * iChange);
			kPlayer.changeWorkerSpeedModifier(kTech.getWorkerSpeedModifier() * iChange);
			kPlayer.changeTradeRoutes(kTech.getTradeRoutes() * iChange);
			kPlayer.changeExtraHealth(kTech.getHealth() * iChange);
			kPlayer.changeExtraHappiness(kTech.getHappiness() * iChange);
			if (kTech.isUnitRangeUnbound())
				kPlayer.changeUnitRangeUnboundCount(iChange);
			if (kTech.isUnitTerritoryUnbound())
				kPlayer.changeUnitTerritoryUnboundCount(iChange);
			kPlayer.changeExtraRange(kTech.getUnitRangeChange() * iChange);
			kPlayer.changeExtraRangeModifier(kTech.getUnitRangeModifier() * iChange);
			kPlayer.changeAssets(kTech.getAssetValue() * iChange);
			kPlayer.changePower(kTech.getPowerValue() * iChange);
			kPlayer.changeTechScore(getTechScore(eTech) * iChange);
			kPlayer.changeCultureDefenceModifier(kTech.getCultureDefenceModifier() * iChange);

			UnitTypes eFreeUnit = kPlayer.getTechFreeUnit(eTech, false);
			if (eFreeUnit != NO_UNIT) {
				CvCity* pCapitalCity = kPlayer.getCapitalCity();
				if (pCapitalCity != NULL) {
					if (GC.getUnitInfo(eFreeUnit).isGoldenAge())
						pCapitalCity->createGreatPeople(eFreeUnit, false, false);
					else
						kPlayer.initUnit(eFreeUnit, pCapitalCity->getX_INLINE(), pCapitalCity->getY_INLINE());
				}
			}

			for (CommerceTypes eCommerce = (CommerceTypes)0; eCommerce < NUM_COMMERCE_TYPES; eCommerce = (CommerceTypes)(eCommerce + 1)) {
				kPlayer.changeCommerceRateModifier(eCommerce, kTech.getCommerceModifier(eCommerce)* iChange);
				kPlayer.changeSpecialistExtraCommerce(eCommerce, kTech.getSpecialistExtraCommerce(eCommerce) * iChange);
			}

			bool bYieldUpdated = false;
			for (YieldTypes eYield = (YieldTypes)0; eYield < NUM_YIELD_TYPES; eYield = (YieldTypes)(eYield + 1)) {
				kPlayer.changeForestPlotYield(eYield, kTech.getForestPlotYieldChange(eYield) * iChange);
				kPlayer.changeRiverPlotYield(eYield, kTech.getRiverPlotYieldChange(eYield) * iChange);
				kPlayer.changeSeaPlotYield(eYield, kTech.getSeaPlotYieldChange(eYield) * iChange);
				if (!bYieldUpdated && (kTech.getForestPlotYieldChange(eYield) != 0 || kTech.getRiverPlotYieldChange(eYield) != 0 || kTech.getSeaPlotYieldChange(eYield) != 0))
					bYieldUpdated = true;
			}

			if (bYieldUpdated) {
				int iLoop;
				for (CvCity* pLoopCity = kPlayer.firstCity(&iLoop); pLoopCity != NULL; pLoopCity = kPlayer.nextCity(&iLoop)) {
					pLoopCity->updateYield();
				}
			}

			for (WorldViewTypes eWorldView = (WorldViewTypes)0; eWorldView < NUM_WORLD_VIEWS; eWorldView = (WorldViewTypes)(eWorldView + 1)) {
				if (kTech.isEnableWorldView(eWorldView)) {
					kPlayer.changeWorldViewEnabledCount(eWorldView, iChange);
					kPlayer.changeWorldViewRevoltTurnChange(eWorldView, kTech.getWorldViewRevoltTurnChange(eWorldView));
				}
			}
		}
	}

	for (int iI = 0; iI < GC.getMapINLINE().numPlotsINLINE(); iI++) {
		CvPlot* pLoopPlot = GC.getMapINLINE().plotByIndexINLINE(iI);

		BonusTypes eBonus = pLoopPlot->getBonusType();

		if (eBonus != NO_BONUS) {
			if (GC.getBonusInfo(eBonus).getTechReveal() == eTech) {
				pLoopPlot->updateYield();
				pLoopPlot->setLayoutDirty(true);
			}
		}
	}

	for (BuildTypes eBuild = (BuildTypes)0; eBuild < GC.getNumBuildInfos(); eBuild = (BuildTypes)(eBuild + 1)) {
		const CvBuildInfo& kBuild = GC.getBuildInfo(eBuild);
		if (kBuild.getTechPrereq() == eTech) {
			if (kBuild.getRoute() != NO_ROUTE) {
				for (int iI = 0; iI < GC.getMapINLINE().numPlotsINLINE(); iI++) {
					CvPlot* pLoopPlot = GC.getMapINLINE().plotByIndexINLINE(iI);

					CvCity* pCity = pLoopPlot->getPlotCity();

					if (pCity != NULL) {
						if (pCity->getTeam() == getID()) {
							pLoopPlot->updateCityRoute(true);
						}
					}
				}
			}
		}
	}

	for (PlayerTypes ePlayer = (PlayerTypes)0; ePlayer < MAX_PLAYERS; ePlayer = (PlayerTypes)(ePlayer + 1)) {
		CvPlayer& kPlayer = GET_PLAYER(ePlayer);
		if (kPlayer.getTeam() == getID()) {
			kPlayer.updateCorporation();
		}
	}

	if (iChange > 0) {
		obsoletePromotions(eTech);
	}
}


void CvTeam::cancelDefensivePacts() {
	int iLoop;
	for (CvDeal* pLoopDeal = GC.getGameINLINE().firstDeal(&iLoop); pLoopDeal != NULL; pLoopDeal = GC.getGameINLINE().nextDeal(&iLoop)) {
		bool bCancelDeal = false;

		if ((GET_PLAYER(pLoopDeal->getFirstPlayer()).getTeam() == getID()) ||
			(GET_PLAYER(pLoopDeal->getSecondPlayer()).getTeam() == getID())) {
			for (CLLNode<TradeData>* pNode = pLoopDeal->headFirstTradesNode(); (pNode != NULL); pNode = pLoopDeal->nextFirstTradesNode(pNode)) {
				if (pNode->m_data.m_eItemType == TRADE_DEFENSIVE_PACT) {
					bCancelDeal = true;
					break;
				}
			}

			if (!bCancelDeal) {
				for (CLLNode<TradeData>* pNode = pLoopDeal->headSecondTradesNode(); (pNode != NULL); pNode = pLoopDeal->nextSecondTradesNode(pNode)) {
					if (pNode->m_data.m_eItemType == TRADE_DEFENSIVE_PACT) {
						bCancelDeal = true;
						break;
					}
				}
			}
		}

		if (bCancelDeal) {
			pLoopDeal->kill();
		}
	}
}


void CvTeam::read(FDataStreamBase* pStream) {
	// Init data before load
	reset();

	uint uiFlag = 0;
	pStream->Read(&uiFlag);	// flags for expansion

	pStream->Read(&m_iNumMembers);
	pStream->Read(&m_iAliveCount);
	pStream->Read(&m_iEverAliveCount);
	pStream->Read(&m_iNumCities);
	pStream->Read(&m_iTotalPopulation);
	pStream->Read(&m_iTotalLand);
	pStream->Read(&m_iNukeInterception);
	pStream->Read(&m_iExtraWaterSeeFromCount);
	pStream->Read(&m_iMapTradingCount);
	pStream->Read(&m_iTechTradingCount);
	pStream->Read(&m_iGoldTradingCount);
	pStream->Read(&m_iOpenBordersTradingCount);
	pStream->Read(&m_iDefensivePactTradingCount);
	pStream->Read(&m_iPermanentAllianceTradingCount);
	pStream->Read(&m_iVassalTradingCount);
	pStream->Read(&m_iBridgeBuildingCount);
	pStream->Read(&m_iIrrigationCount);
	pStream->Read(&m_iIgnoreIrrigationCount);
	pStream->Read(&m_iWaterWorkCount);
	pStream->Read(&m_iVassalPower);
	pStream->Read(&m_iMasterPower);
	pStream->Read(&m_iEnemyWarWearinessModifier);
	pStream->Read(&m_iRiverTradeCount);
	pStream->Read(&m_iEspionagePointsEver);
	pStream->Read(&m_iCanPassPeaksCount);
	pStream->Read(&m_iMoveFastPeaksCount);
	pStream->Read(&m_iCanFoundOnPeaksCount);
	pStream->Read(&m_iEmbassyTradingCount);
	pStream->Read(&m_iLimitedBordersTradingCount);
	pStream->Read(&m_iFreeTradeAgreementTradingCount);
	pStream->Read(&m_iNonAggressionTradingCount);
	pStream->Read(&m_iStarSignImpactedCount);

	pStream->Read(&m_bMapCentering);
	pStream->Read(&m_bCapitulated);

	pStream->Read((int*)&m_eID);

	pStream->Read(MAX_TEAMS, m_aiStolenVisibilityTimer);
	pStream->Read(MAX_TEAMS, m_aiWarWeariness);
	pStream->Read(MAX_TEAMS, m_aiTechShareCount);
	pStream->Read(MAX_TEAMS, m_aiEspionagePointsAgainstTeam);
	pStream->Read(MAX_TEAMS, m_aiCounterespionageTurnsLeftAgainstTeam);
	pStream->Read(MAX_TEAMS, m_aiCounterespionageModAgainstTeam);
	pStream->Read(NUM_COMMERCE_TYPES, m_aiCommerceFlexibleCount);
	pStream->Read(NUM_DOMAIN_TYPES, m_aiExtraMoves);
	pStream->Read(GC.getNumVoteSourceInfos(), m_aiForceTeamVoteEligibilityCount);

	pStream->Read(MAX_TEAMS, m_abHasMet);
	if (uiFlag >= 1)
		pStream->Read(MAX_TEAMS, m_abHasSeen);
	else
		memcpy(m_abHasSeen, m_abHasMet, sizeof(*m_abHasSeen) * MAX_TEAMS);
	pStream->Read(MAX_TEAMS, m_abAtWar);
	pStream->Read(MAX_TEAMS, m_abPermanentWarPeace);
	pStream->Read(MAX_TEAMS, m_abOpenBorders);
	pStream->Read(MAX_TEAMS, m_abDefensivePact);
	pStream->Read(MAX_TEAMS, m_abForcePeace);
	pStream->Read(MAX_TEAMS, m_abVassal);
	pStream->Read(MAX_TEAMS, m_abEmbassy);
	pStream->Read(MAX_TEAMS, m_abLimitedBorders);
	pStream->Read(MAX_TEAMS, m_abFreeTradeAgreement);
	pStream->Read(MAX_TEAMS, m_abHasNonAggression);
	pStream->Read(GC.getNumVictoryInfos(), m_abCanLaunch);

	pStream->Read(GC.getNumRouteInfos(), m_paiRouteChange);
	pStream->Read(GC.getNumProjectInfos(), m_paiProjectCount);
	pStream->Read(GC.getNumProjectInfos(), m_paiProjectDefaultArtTypes);

	//project art types
	for (ProjectTypes eProject = (ProjectTypes)0; eProject < GC.getNumProjectInfos(); eProject = (ProjectTypes)(eProject + 1)) {
		int temp;
		for (int j = 0; j < m_paiProjectCount[eProject]; j++) {
			pStream->Read(&temp);
			m_pavProjectArtTypes[eProject].push_back(temp);
		}
	}

	pStream->Read(GC.getNumProjectInfos(), m_paiProjectMaking);
	pStream->Read(GC.getNumUnitClassInfos(), m_paiUnitClassCount);
	pStream->Read(GC.getNumBuildingClassInfos(), m_paiBuildingClassCount);
	pStream->Read(GC.getNumBuildingInfos(), m_paiObsoleteBuildingCount);
	pStream->Read(GC.getNumTechInfos(), m_paiResearchProgress);
	pStream->Read(GC.getNumTechInfos(), m_paiTechCount);
	pStream->Read(GC.getNumTerrainInfos(), m_paiTerrainTradeCount);
	pStream->Read(GC.getNumVictoryInfos(), m_aiVictoryCountdown);

	pStream->Read(GC.getNumTechInfos(), m_pabHasTech);
	pStream->Read(GC.getNumTechInfos(), m_pabNoTradeTech);

	for (ImprovementTypes eImprovement = (ImprovementTypes)0; eImprovement < GC.getNumImprovementInfos(); eImprovement = (ImprovementTypes)(eImprovement + 1)) {
		pStream->Read(NUM_YIELD_TYPES, m_ppaaiImprovementYieldChange[eImprovement]);
	}

	int iSize;
	m_aeRevealedBonuses.clear();
	pStream->Read(&iSize);
	for (int i = 0; i < iSize; ++i) {
		BonusTypes eBonus;
		pStream->Read((int*)&eBonus);
		m_aeRevealedBonuses.push_back(eBonus);
	}
}


void CvTeam::write(FDataStreamBase* pStream) {
	uint uiFlag = 1;
	pStream->Write(uiFlag);		// flag for expansion

	pStream->Write(m_iNumMembers);
	pStream->Write(m_iAliveCount);
	pStream->Write(m_iEverAliveCount);
	pStream->Write(m_iNumCities);
	pStream->Write(m_iTotalPopulation);
	pStream->Write(m_iTotalLand);
	pStream->Write(m_iNukeInterception);
	pStream->Write(m_iExtraWaterSeeFromCount);
	pStream->Write(m_iMapTradingCount);
	pStream->Write(m_iTechTradingCount);
	pStream->Write(m_iGoldTradingCount);
	pStream->Write(m_iOpenBordersTradingCount);
	pStream->Write(m_iDefensivePactTradingCount);
	pStream->Write(m_iPermanentAllianceTradingCount);
	pStream->Write(m_iVassalTradingCount);
	pStream->Write(m_iBridgeBuildingCount);
	pStream->Write(m_iIrrigationCount);
	pStream->Write(m_iIgnoreIrrigationCount);
	pStream->Write(m_iWaterWorkCount);
	pStream->Write(m_iVassalPower);
	pStream->Write(m_iMasterPower);
	pStream->Write(m_iEnemyWarWearinessModifier);
	pStream->Write(m_iRiverTradeCount);
	pStream->Write(m_iEspionagePointsEver);
	pStream->Write(m_iCanPassPeaksCount);
	pStream->Write(m_iMoveFastPeaksCount);
	pStream->Write(m_iCanFoundOnPeaksCount);
	pStream->Write(m_iEmbassyTradingCount);
	pStream->Write(m_iLimitedBordersTradingCount);
	pStream->Write(m_iFreeTradeAgreementTradingCount);
	pStream->Write(m_iNonAggressionTradingCount);
	pStream->Write(m_iStarSignImpactedCount);

	pStream->Write(m_bMapCentering);
	pStream->Write(m_bCapitulated);

	pStream->Write(m_eID);

	pStream->Write(MAX_TEAMS, m_aiStolenVisibilityTimer);
	pStream->Write(MAX_TEAMS, m_aiWarWeariness);
	pStream->Write(MAX_TEAMS, m_aiTechShareCount);
	pStream->Write(MAX_TEAMS, m_aiEspionagePointsAgainstTeam);
	pStream->Write(MAX_TEAMS, m_aiCounterespionageTurnsLeftAgainstTeam);
	pStream->Write(MAX_TEAMS, m_aiCounterespionageModAgainstTeam);
	pStream->Write(NUM_COMMERCE_TYPES, m_aiCommerceFlexibleCount);
	pStream->Write(NUM_DOMAIN_TYPES, m_aiExtraMoves);
	pStream->Write(GC.getNumVoteSourceInfos(), m_aiForceTeamVoteEligibilityCount);

	pStream->Write(MAX_TEAMS, m_abHasMet);
	pStream->Write(MAX_TEAMS, m_abHasSeen); // K-Mod. uiFlag >= 1
	pStream->Write(MAX_TEAMS, m_abAtWar);
	pStream->Write(MAX_TEAMS, m_abPermanentWarPeace);
	pStream->Write(MAX_TEAMS, m_abOpenBorders);
	pStream->Write(MAX_TEAMS, m_abDefensivePact);
	pStream->Write(MAX_TEAMS, m_abForcePeace);
	pStream->Write(MAX_TEAMS, m_abVassal);
	pStream->Write(MAX_TEAMS, m_abEmbassy);
	pStream->Write(MAX_TEAMS, m_abLimitedBorders);
	pStream->Write(MAX_TEAMS, m_abFreeTradeAgreement);
	pStream->Write(MAX_TEAMS, m_abHasNonAggression);
	pStream->Write(GC.getNumVictoryInfos(), m_abCanLaunch);

	pStream->Write(GC.getNumRouteInfos(), m_paiRouteChange);
	pStream->Write(GC.getNumProjectInfos(), m_paiProjectCount);
	pStream->Write(GC.getNumProjectInfos(), m_paiProjectDefaultArtTypes);

	//project art types
	for (ProjectTypes eProject = (ProjectTypes)0; eProject < GC.getNumProjectInfos(); eProject = (ProjectTypes)(eProject + 1)) {
		for (int j = 0; j < m_paiProjectCount[eProject]; j++)
			pStream->Write(m_pavProjectArtTypes[eProject][j]);
	}

	pStream->Write(GC.getNumProjectInfos(), m_paiProjectMaking);
	pStream->Write(GC.getNumUnitClassInfos(), m_paiUnitClassCount);
	pStream->Write(GC.getNumBuildingClassInfos(), m_paiBuildingClassCount);
	pStream->Write(GC.getNumBuildingInfos(), m_paiObsoleteBuildingCount);
	pStream->Write(GC.getNumTechInfos(), m_paiResearchProgress);
	pStream->Write(GC.getNumTechInfos(), m_paiTechCount);
	pStream->Write(GC.getNumTerrainInfos(), m_paiTerrainTradeCount);
	pStream->Write(GC.getNumVictoryInfos(), m_aiVictoryCountdown);

	pStream->Write(GC.getNumTechInfos(), m_pabHasTech);
	pStream->Write(GC.getNumTechInfos(), m_pabNoTradeTech);

	for (ImprovementTypes eImprovement = (ImprovementTypes)0; eImprovement < GC.getNumImprovementInfos(); eImprovement = (ImprovementTypes)(eImprovement + 1)) {
		pStream->Write(NUM_YIELD_TYPES, m_ppaaiImprovementYieldChange[eImprovement]);
	}

	pStream->Write(m_aeRevealedBonuses.size());
	for (std::vector<BonusTypes>::iterator it = m_aeRevealedBonuses.begin(); it != m_aeRevealedBonuses.end(); ++it) {
		pStream->Write(*it);
	}
}

// CACHE: cache frequently used values
///////////////////////////////////////
bool CvTeam::hasShrine(ReligionTypes eReligion) {
	bool bHasShrine = false;

	if (eReligion != NO_RELIGION) {
		CvCity* pHolyCity = GC.getGameINLINE().getHolyCity(eReligion);

		// if the holy city exists, and we own it
		if (pHolyCity != NULL && GET_PLAYER(pHolyCity->getOwnerINLINE()).getTeam() == getID())
			bHasShrine = pHolyCity->hasShrine(eReligion);
	}

	return bHasShrine;
}

void CvTeam::getCompletedSpaceshipProjects(std::map<ProjectTypes, int>& mapProjects) const {
	for (int i = 0; i < GC.getNumProjectInfos(); i++) {
		ProjectTypes eProject = (ProjectTypes)i;
		if (GC.getProjectInfo(eProject).isSpaceship()) {
			mapProjects[eProject] = getProjectCount(eProject);
		}
	}
}

int CvTeam::getProjectPartNumber(ProjectTypes eProject, bool bAssert) const {
	int iNumBuilt = getProjectCount(eProject);
	for (int i = 0; i < iNumBuilt; i++) {
		int artType = getProjectArtType(eProject, i);
		if (artType < 0) {
			return i;
		}
	}

	//didn't find empty part number
	if (bAssert) {
		FAssertMsg(false, "Unknown part number.");
	}

	//return the last one
	return std::min(iNumBuilt, GC.getProjectInfo(eProject).getMaxTeamInstances() - 1);
}

bool CvTeam::hasLaunched() const {
	VictoryTypes spaceVictory = GC.getGameINLINE().getSpaceVictory();
	if (spaceVictory != NO_VICTORY) {
		return (getVictoryCountdown(spaceVictory) >= 0);
	}
	return false;
}

bool CvTeam::isCanPassPeaks() const {
	return (getCanPassPeaksCount() > 0);
}

int CvTeam::getCanPassPeaksCount() const {
	return m_iCanPassPeaksCount;
}

void CvTeam::changeCanPassPeaksCount(int iChange) {
	m_iCanPassPeaksCount = (m_iCanPassPeaksCount + iChange);
	FAssert(getCanPassPeaksCount() >= 0);
}

bool CvTeam::isMoveFastPeaks() const {
	return (getMoveFastPeaksCount() > 0);
}

int CvTeam::getMoveFastPeaksCount() const {
	return m_iMoveFastPeaksCount;
}

void CvTeam::changeMoveFastPeaksCount(int iChange) {
	m_iMoveFastPeaksCount = (m_iMoveFastPeaksCount + iChange);
	FAssert(getMoveFastPeaksCount() >= 0);
}

bool CvTeam::isCanFoundOnPeaks() const {
	return (getCanFoundOnPeaksCount() > 0);
}

int CvTeam::getCanFoundOnPeaksCount() const {
	return m_iCanFoundOnPeaksCount;
}

void CvTeam::changeCanFoundOnPeaksCount(int iChange) {
	m_iCanFoundOnPeaksCount = (m_iCanFoundOnPeaksCount + iChange);
	FAssert(getCanFoundOnPeaksCount() >= 0);
}

bool CvTeam::isHasEmbassy(TeamTypes eIndex) const {
	FAssertMsg(eIndex >= 0, "eIndex is expected to be non-negative (invalid Index)");
	FAssertMsg(eIndex < MAX_TEAMS, "eIndex is expected to be within maximum bounds (invalid Index)");
	return m_abEmbassy[eIndex];
}

void CvTeam::setHasEmbassy(TeamTypes eIndex, bool bNewValue) {
	FAssertMsg(eIndex >= 0, "eIndex is expected to be non-negative (invalid Index)");
	FAssertMsg(eIndex < MAX_TEAMS, "eIndex is expected to be within maximum bounds (invalid Index)");
	if (isHasEmbassy(eIndex) != bNewValue) {
		CvCity* pCapital;
		for (PlayerTypes ePlayer = (PlayerTypes)0; ePlayer < MAX_PLAYERS; ePlayer = (PlayerTypes)(ePlayer + 1)) {
			const CvPlayer& kPlayer = GET_PLAYER(ePlayer);
			if (kPlayer.getTeam() == getID()) {
				pCapital = kPlayer.getCapitalCity();
				if (pCapital != NULL) {
					pCapital->plot()->updateSight(false, true);
				}
			}
		}
		m_abEmbassy[eIndex] = bNewValue;
		for (PlayerTypes ePlayer = (PlayerTypes)0; ePlayer < MAX_PLAYERS; ePlayer = (PlayerTypes)(ePlayer + 1)) {
			const CvPlayer& kPlayer = GET_PLAYER(ePlayer);
			if (kPlayer.getTeam() == getID()) {
				pCapital = kPlayer.getCapitalCity();
				if (pCapital != NULL) {
					pCapital->plot()->updateSight(true, true);
				}
			}
		}

		GC.getMapINLINE().verifyUnitValidPlot();

		if ((getID() == GC.getGameINLINE().getActiveTeam()) || (eIndex == GC.getGameINLINE().getActiveTeam())) {
			gDLL->getInterfaceIFace()->setDirty(Score_DIRTY_BIT, true);
		}
	}
}

int CvTeam::getEmbassyTradingCount() const {
	return m_iEmbassyTradingCount;
}

bool CvTeam::isEmbassyTrading() const {
	return (getEmbassyTradingCount() > 0);
}

void CvTeam::changeEmbassyTradingCount(int iChange) {
	if (iChange != 0) {
		m_iEmbassyTradingCount = (m_iEmbassyTradingCount + iChange);
	}
}

void CvTeam::sendAmbassador(TeamTypes eTeam) {
	FAssert(eTeam != NO_TEAM);
	FAssert(eTeam != getID());

	if (!isAtWar(eTeam) && (getID() != eTeam)) {
		CLinkList<TradeData> ourList;
		CLinkList<TradeData> theirList;
		TradeData item;
		setTradeItem(&item, TRADE_EMBASSY);

		if (GET_PLAYER(getLeaderID()).canTradeItem(GET_TEAM(eTeam).getLeaderID(), item) && GET_PLAYER(GET_TEAM(eTeam).getLeaderID()).canTradeItem(getLeaderID(), item)) {
			ourList.insertAtEnd(item);
			theirList.insertAtEnd(item);

			GC.getGameINLINE().implementDeal(getLeaderID(), (GET_TEAM(eTeam).getLeaderID()), &ourList, &theirList);
		}
	}
}

int CvTeam::getLimitedBordersTradingCount() const {
	return m_iLimitedBordersTradingCount;
}

bool CvTeam::isLimitedBordersTrading() const {
	return (getLimitedBordersTradingCount() > 0);
}

void CvTeam::changeLimitedBordersTradingCount(int iChange) {
	m_iLimitedBordersTradingCount = (m_iLimitedBordersTradingCount + iChange);
	FAssert(getLimitedBordersTradingCount() >= 0);
}

void CvTeam::signLimitedBorders(TeamTypes eTeam) {

	FAssert(eTeam != NO_TEAM);
	FAssert(eTeam != getID());

	if (!isAtWar(eTeam) && (getID() != eTeam) && (!isOpenBorders(eTeam))) {
		CLinkList<TradeData> ourList;
		CLinkList<TradeData> theirList;
		TradeData item;
		setTradeItem(&item, TRADE_LIMITED_BORDERS);

		if (GET_PLAYER(getLeaderID()).canTradeItem(GET_TEAM(eTeam).getLeaderID(), item) && GET_PLAYER(GET_TEAM(eTeam).getLeaderID()).canTradeItem(getLeaderID(), item)) {
			ourList.insertAtEnd(item);
			theirList.insertAtEnd(item);

			GC.getGameINLINE().implementDeal(getLeaderID(), (GET_TEAM(eTeam).getLeaderID()), &ourList, &theirList);
		}
	}
}

bool CvTeam::canSignOpenBorders(TeamTypes eTeam) {
	if (!isHasEmbassy(eTeam)) {
		return false;
	}

	if (!isLimitedBorders(eTeam)) {
		return false;
	}

	return true;
}

bool CvTeam::isLimitedBorders(TeamTypes eIndex) const {
	FAssertMsg(eIndex >= 0, "eIndex is expected to be non-negative (invalid Index)");
	FAssertMsg(eIndex < MAX_TEAMS, "eIndex is expected to be within maximum bounds (invalid Index)");
	return m_abLimitedBorders[eIndex];
}


void CvTeam::setLimitedBorders(TeamTypes eIndex, bool bNewValue) {
	FAssertMsg(eIndex >= 0, "eIndex is expected to be non-negative (invalid Index)");
	FAssertMsg(eIndex < MAX_TEAMS, "eIndex is expected to be within maximum bounds (invalid Index)");

	if (isLimitedBorders(eIndex) != bNewValue) {
		bool bOldFreeTrade = isFreeTrade(eIndex);

		m_abLimitedBorders[eIndex] = bNewValue;

		GC.getMapINLINE().verifyUnitValidPlot();

		if ((getID() == GC.getGameINLINE().getActiveTeam()) || (eIndex == GC.getGameINLINE().getActiveTeam())) {
			gDLL->getInterfaceIFace()->setDirty(Score_DIRTY_BIT, true);
		}

		if (bOldFreeTrade != isFreeTrade(eIndex)) {
			for (PlayerTypes ePlayer = (PlayerTypes)0; ePlayer < MAX_PLAYERS; ePlayer = (PlayerTypes)(ePlayer + 1)) {
				CvPlayer& kPlayer = GET_PLAYER(ePlayer);
				if (kPlayer.isAlive()) {
					if (kPlayer.getTeam() == getID()) {
						kPlayer.updateTradeRoutes();
					}
				}
			}
		}
	}
}

bool CvTeam::canSignFreeTradeAgreement(TeamTypes eTeam) {
	if (!isHasEmbassy(eTeam)) {
		return false;
	}

	if (!isOpenBorders(eTeam) || !isLimitedBorders(eTeam)) {
		return false;
	}

	return true;
}

int CvTeam::getFreeTradeAgreementTradingCount() const {
	return m_iFreeTradeAgreementTradingCount;
}

bool CvTeam::isFreeTradeAgreementTrading() const {
	return (getFreeTradeAgreementTradingCount() > 0);
}

void CvTeam::changeFreeTradeAgreementTradingCount(int iChange) {
	if (iChange != 0) {
		m_iFreeTradeAgreementTradingCount = (m_iFreeTradeAgreementTradingCount + iChange);
	}
}

bool CvTeam::isFreeTradeAgreement(TeamTypes eIndex) const

{
	FAssertMsg(eIndex >= 0, "eIndex is expected to be non-negative (invalid Index)");
	FAssertMsg(eIndex < MAX_TEAMS, "eIndex is expected to be within maximum bounds (invalid Index)");
	return m_abFreeTradeAgreement[eIndex];
}

void CvTeam::setFreeTradeAgreement(TeamTypes eIndex, bool bNewValue)

{
	FAssertMsg(eIndex >= 0, "eIndex is expected to be non-negative (invalid Index)");
	FAssertMsg(eIndex < MAX_TEAMS, "eIndex is expected to be within maximum bounds (invalid Index)");

	if (isFreeTradeAgreement(eIndex) != bNewValue) {
		bool bOldFreeTrade = isFreeTradeAgreement(eIndex);

		m_abFreeTradeAgreement[eIndex] = bNewValue;

		GC.getMapINLINE().verifyUnitValidPlot();

		if ((getID() == GC.getGameINLINE().getActiveTeam()) || (eIndex == GC.getGameINLINE().getActiveTeam())) {
			gDLL->getInterfaceIFace()->setDirty(Score_DIRTY_BIT, true);
		}

		if (bOldFreeTrade != isFreeTradeAgreement(eIndex)) {
			for (PlayerTypes ePlayer = (PlayerTypes)0; ePlayer < MAX_PLAYERS; ePlayer = (PlayerTypes)(ePlayer + 1)) {
				CvPlayer& kPlayer = GET_PLAYER(ePlayer);
				if (kPlayer.isAlive()) {
					if (kPlayer.getTeam() == getID()) {
						kPlayer.updateTradeRoutes();
					}
				}
			}
		}
	}
}

bool CvTeam::isHasNonAggression(TeamTypes eIndex) const {
	FAssertMsg(eIndex >= 0, "eIndex is expected to be non-negative (invalid Index)");
	FAssertMsg(eIndex < MAX_TEAMS, "eIndex is expected to be within maximum bounds (invalid Index)");
	return m_abHasNonAggression[eIndex];
}

void CvTeam::setHasNonAggression(TeamTypes eIndex, bool bNewValue) {
	FAssertMsg(eIndex >= 0, "eIndex is expected to be non-negative (invalid Index)");
	FAssertMsg(eIndex < MAX_TEAMS, "eIndex is expected to be within maximum bounds (invalid Index)");
	if (m_abHasNonAggression[eIndex] != bNewValue) {
		m_abHasNonAggression[eIndex] = bNewValue;

		GC.getMapINLINE().verifyUnitValidPlot();

		if ((getID() == GC.getGameINLINE().getActiveTeam()) || (eIndex == GC.getGameINLINE().getActiveTeam())) {
			gDLL->getInterfaceIFace()->setDirty(Score_DIRTY_BIT, true);
		}
		if (bNewValue && !GET_TEAM(eIndex).isHasNonAggression(getID())) {
			CvWString szBuffer = gDLL->getText("TXT_KEY_MISC_NON_AGGRESSION_AGREEMENT", getName().GetCString(), GET_TEAM(eIndex).getName().GetCString());
			for (PlayerTypes ePlayer = (PlayerTypes)0; ePlayer < MAX_PLAYERS; ePlayer = (PlayerTypes)(ePlayer + 1)) {
				CvPlayer& kPlayer = GET_PLAYER(ePlayer);
				if (kPlayer.isAlive()) {
					if (isHasMet(kPlayer.getTeam()) && GET_TEAM(eIndex).isHasMet(kPlayer.getTeam())) {
						gDLL->getInterfaceIFace()->addMessage(ePlayer, false, GC.getEVENT_MESSAGE_TIME(), szBuffer, "AS2D_WELOVEKING", MESSAGE_TYPE_MAJOR_EVENT, NULL, (ColorTypes)GC.getInfoTypeForString("COLOR_HIGHLIGHT_TEXT"));
					}
				}
			}
		}
		m_abHasNonAggression[eIndex] = bNewValue;
	}
}

bool CvTeam::canSignNonAggression(TeamTypes eTeam) const {
	for (TeamTypes eLoopTeam = (TeamTypes)0; eLoopTeam < MAX_CIV_TEAMS; eLoopTeam = (TeamTypes)(eLoopTeam + 1)) {
		if (eLoopTeam != getID() && eLoopTeam != eTeam) {
			CvTeam& kLoopTeam = GET_TEAM(eLoopTeam);
			if (kLoopTeam.isPermanentWarPeace(eTeam) != kLoopTeam.isPermanentWarPeace(getID())) {
				return false;
			}

			if (isPermanentWarPeace(eLoopTeam) != GET_TEAM(eTeam).isPermanentWarPeace(eLoopTeam)) {
				return false;
			}
		}
	}

	return true;
}

int CvTeam::getNonAggressionTradingCount() const {
	return m_iNonAggressionTradingCount;
}

bool CvTeam::isNonAggressionTrading() const {
	return (getNonAggressionTradingCount() > 0);
}

void CvTeam::changeNonAggressionTradingCount(int iChange) {
	m_iNonAggressionTradingCount = (m_iNonAggressionTradingCount + iChange);
	FAssert(getNonAggressionTradingCount() >= 0);
}

// Check if the team is affected by start sign changes
bool CvTeam::isStarSignImpacted() const {
	return m_iStarSignImpactedCount > 0;
}

// Update influences on whether the team is impacted by star sign changes
void CvTeam::changeStarSignImpactedCount(int iChange) {
	m_iStarSignImpactedCount += iChange;
}

// Apply any star sign change effects to the team players if applicable
void CvTeam::doStarSignChange() {
	for (PlayerTypes ePlayer = (PlayerTypes)0; ePlayer < MAX_PLAYERS; ePlayer = (PlayerTypes)(ePlayer + 1)) {
		CvPlayer& kPlayer = GET_PLAYER(ePlayer);
		if (kPlayer.isAlive() && kPlayer.isCanProcessStarSign()) {
			if (kPlayer.getTeam() == getID()) {
				kPlayer.doStarSignChange();
			}
		}
	}
}

void CvTeam::changeBuildingCommerceChange(BuildingTypes eBuilding, CommerceTypes eCommerce, int iChange) {
	FAssertMsg(eBuilding >= 0, "eIndex1 is expected to be non-negative (invalid Index)");
	FAssertMsg(eBuilding < GC.getNumBuildingInfos(), "eIndex1 is expected to be within maximum bounds (invalid Index)");
	FAssertMsg(eCommerce >= 0, "eIndex2 is expected to be non-negative (invalid Index)");
	FAssertMsg(eCommerce < NUM_COMMERCE_TYPES, "eIndex2 is expected to be within maximum bounds (invalid Index)");

	for (PlayerTypes ePlayer = (PlayerTypes)0; ePlayer < MAX_PLAYERS; ePlayer = (PlayerTypes)(ePlayer + 1)) {
		CvPlayer& kPlayer = GET_PLAYER(ePlayer);
		if (kPlayer.isAlive() && kPlayer.getTeam() == getID()) {
			int iLoop;
			for (CvCity* pLoopCity = kPlayer.firstCity(&iLoop); pLoopCity != NULL; pLoopCity = kPlayer.nextCity(&iLoop)) {
				pLoopCity->changeBuildingCommerceChange((BuildingClassTypes)GC.getBuildingInfo(eBuilding).getBuildingClassType(), eCommerce, iChange);
			}
			updateCommerce();
		}
	}
}

void CvTeam::changeBuildingYieldChange(BuildingTypes eBuilding, YieldTypes eYield, int iChange) {
	FAssertMsg(eBuilding >= 0, "eIndex1 is expected to be non-negative (invalid Index)");
	FAssertMsg(eBuilding < GC.getNumBuildingInfos(), "eIndex1 is expected to be within maximum bounds (invalid Index)");
	FAssertMsg(eYield >= 0, "eIndex2 is expected to be non-negative (invalid Index)");
	FAssertMsg(eYield < NUM_YIELD_TYPES, "eIndex2 is expected to be within maximum bounds (invalid Index)");

	for (PlayerTypes ePlayer = (PlayerTypes)0; ePlayer < MAX_PLAYERS; ePlayer = (PlayerTypes)(ePlayer + 1)) {
		CvPlayer& kPlayer = GET_PLAYER(ePlayer);
		if (kPlayer.isAlive() && kPlayer.getTeam() == getID()) {
			int iLoop;
			for (CvCity* pLoopCity = kPlayer.firstCity(&iLoop); pLoopCity != NULL; pLoopCity = kPlayer.nextCity(&iLoop)) {
				pLoopCity->changeBuildingYieldChange((BuildingClassTypes)GC.getBuildingInfo(eBuilding).getBuildingClassType(), eYield, iChange);
			}
			updateYield();
		}
	}
}

void CvTeam::obsoletePromotions(TechTypes eObsoleteTech) {
	if (eObsoleteTech == NO_TECH) return;

	std::vector<PromotionTypes> vObsoletePromotions;
	for (PromotionTypes ePromotion = (PromotionTypes)0; ePromotion < GC.getNumBonusInfos(); ePromotion = (PromotionTypes)(ePromotion + 1)) {
		if (GC.getPromotionInfo(ePromotion).getObsoleteTech() == eObsoleteTech) {
			vObsoletePromotions.push_back(ePromotion);
		}
	}

	for (std::vector<PromotionTypes>::iterator it = vObsoletePromotions.begin(); it != vObsoletePromotions.end(); ++it) {
		PromotionTypes eObsoletePromotion = (*it);
		for (PlayerTypes ePlayer = (PlayerTypes)0; ePlayer < MAX_PLAYERS; ePlayer = (PlayerTypes)(ePlayer + 1)) {
			const CvPlayer& kPlayer = GET_PLAYER(ePlayer);
			if (kPlayer.isAlive() && kPlayer.getTeam() == getID()) {
				int iLoop;
				for (CvUnit* pLoopUnit = kPlayer.firstUnit(&iLoop); NULL != pLoopUnit; pLoopUnit = kPlayer.nextUnit(&iLoop)) {
					if (pLoopUnit->isHasPromotion(eObsoletePromotion)) {
						pLoopUnit->setHasPromotion(eObsoletePromotion, false);
					}
				}
			}
		}
	}

}
