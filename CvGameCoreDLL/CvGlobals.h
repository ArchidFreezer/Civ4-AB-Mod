#pragma once

// CvGlobals.h

#ifndef CIV4_GLOBALS_H
#define CIV4_GLOBALS_H

// K-Mod. Created the following function for rounded integer division
static inline int ROUND_DIVIDE(int a, int b) {
	return (a + ((a / b > 0) ? 1 : -1) * (b / 2)) / b;
}

//
// 'global' vars for Civ IV.  singleton class.
// All globals and global types should be contained in this class
//

class FProfiler;
class CvDLLUtilityIFaceBase;
class CvRandom;
class CvGameAI;
class CMessageControl;
class CvDropMgr;
class CMessageQueue;
class CvSetupData;
class CvInitCore;
class CvMessageCodeTranslator;
class CvPortal;
class CvStatsReporter;
class CvDLLInterfaceIFaceBase;
class CvPlayerAI;
class CvDiplomacyScreen;
class CvCivicsScreen;
class CvWBUnitEditScreen;
class CvWBCityEditScreen;
class CMPDiplomacyScreen;
class FMPIManager;
class FAStar;
class CvInterface;
class CMainMenu;
class CvEngine;
class CvArtFileMgr;
class FVariableSystem;
class CvMap;
class CvPlayerAI;
class CvTeamAI;
class CvInterfaceModeInfo;
class CvWorldInfo;
class CvClimateInfo;
class CvSeaLevelInfo;
class CvColorInfo;
class CvPlayerColorInfo;
class CvAdvisorInfo;
class CvRouteModelInfo;
class CvRiverInfo;
class CvRiverModelInfo;
class CvWaterPlaneInfo;
class CvTerrainPlaneInfo;
class CvCameraOverlayInfo;
class CvAnimationPathInfo;
class CvAnimationCategoryInfo;
class CvEntityEventInfo;
class CvEffectInfo;
class CvAttachableInfo;
class CvCameraInfo;
class CvUnitFormationInfo;
class CvGameText;
class CvLandscapeInfo;
class CvTerrainInfo;
class CvBonusClassInfo;
class CvBonusInfo;
class CvFeatureInfo;
class CvCivilizationInfo;
class CvLeaderHeadInfo;
class CvTraitInfo;
class CvCursorInfo;
class CvThroneRoomCamera;
class CvThroneRoomInfo;
class CvThroneRoomStyleInfo;
class CvSlideShowInfo;
class CvSlideShowRandomInfo;
class CvWorldPickerInfo;
class CvSpaceShipInfo;
class CvUnitInfo;
class CvSpecialUnitInfo;
class CvInfoBase;
class CvYieldInfo;
class CvCommerceInfo;
class CvRouteInfo;
class CvImprovementInfo;
class CvGoodyInfo;
class CvBuildInfo;
class CvHandicapInfo;
class CvGameSpeedInfo;
class CvTurnTimerInfo;
class CvProcessInfo;
class CvVoteInfo;
class CvProjectInfo;
class CvBuildingClassInfo;
class CvBuildingInfo;
class CvSpecialBuildingInfo;
class CvUnitClassInfo;
class CvActionInfo;
class CvMissionInfo;
class CvControlInfo;
class CvCommandInfo;
class CvAutomateInfo;
class CvPromotionInfo;
class CvTechInfo;
class CvReligionInfo;
class CvCorporationInfo;
class CvSpecialistInfo;
class CvCivicOptionInfo;
class CvCivicInfo;
class CvDiplomacyInfo;
class CvEraInfo;
class CvHurryInfo;
class CvEmphasizeInfo;
class CvUpkeepInfo;
class CvCultureLevelInfo;
class CvVictoryInfo;
class CvQuestInfo;
class CvGameOptionInfo;
class CvMPOptionInfo;
class CvForceControlInfo;
class CvPlayerOptionInfo;
class CvGraphicOptionInfo;
class CvTutorialInfo;
class CvEventTriggerInfo;
class CvEventInfo;
class CvEspionageMissionInfo;
class CvUnitArtStyleTypeInfo;
class CvVoteSourceInfo;
class CvMainMenuInfo;


class CvGlobals {
	//	friend class CvDLLUtilityIFace;
	friend class CvXMLLoadUtility;
public:

	// singleton accessor
	DllExport inline static CvGlobals& getInstance();

	DllExport CvGlobals();
	DllExport virtual ~CvGlobals();

	DllExport void init();
	DllExport void uninit();
	DllExport void clearTypesMap();

	DllExport CvDiplomacyScreen* getDiplomacyScreen();
	DllExport CMPDiplomacyScreen* getMPDiplomacyScreen();

	DllExport FMPIManager*& getFMPMgrPtr();
	DllExport CvPortal& getPortal();
	DllExport CvSetupData& getSetupData();
	DllExport CvInitCore& getInitCore();
	DllExport CvInitCore& getLoadedInitCore();
	DllExport CvInitCore& getIniInitCore();
	DllExport CvMessageCodeTranslator& getMessageCodes();
	DllExport CvStatsReporter& getStatsReporter();
	DllExport CvStatsReporter* getStatsReporterPtr();
	DllExport CvInterface& getInterface();
	DllExport CvInterface* getInterfacePtr();
	DllExport int getMaxCivPlayers() const;
#ifdef _USRDLL
	CvMap& getMapINLINE() { return *m_map; }				// inlined for perf reasons, do not use outside of dll
	CvGameAI& getGameINLINE() { return *m_game; }			// inlined for perf reasons, do not use outside of dll
#endif
	DllExport CvMap& getMap();
	DllExport CvGameAI& getGame();
	DllExport CvGameAI* getGamePointer();
	DllExport CvRandom& getASyncRand();
	DllExport CMessageQueue& getMessageQueue();
	DllExport CMessageQueue& getHotMessageQueue();
	DllExport CMessageControl& getMessageControl();
	DllExport CvDropMgr& getDropMgr();
	DllExport FAStar& getPathFinder();
	DllExport FAStar& getInterfacePathFinder();
	DllExport FAStar& getStepFinder();
	DllExport FAStar& getRouteFinder();
	DllExport FAStar& getBorderFinder();
	DllExport FAStar& getAreaFinder();
	DllExport FAStar& getPlotGroupFinder();
	DllExport NiPoint3& getPt3Origin();

	DllExport std::vector<CvInterfaceModeInfo*>& getInterfaceModeInfo();
	DllExport CvInterfaceModeInfo& getInterfaceModeInfo(InterfaceModeTypes e);

	DllExport NiPoint3& getPt3CameraDir();

	DllExport bool& getLogging();
	DllExport bool& getRandLogging();
	DllExport bool& getSynchLogging();
	DllExport bool& overwriteLogs();

	DllExport int* getPlotDirectionX();
	DllExport int* getPlotDirectionY();
	DllExport int* getPlotCardinalDirectionX();
	DllExport int* getPlotCardinalDirectionY();
	DllExport int* getCityPlotX();
	DllExport int* getCityPlotY();
	DllExport int* getCityPlotPriority();
	DllExport int getXYCityPlot(int i, int j);
	DirectionTypes* getTurnLeftDirection();
	DirectionTypes getTurnLeftDirection(int i);
	DirectionTypes* getTurnRightDirection();
	DirectionTypes getTurnRightDirection(int i);
	DllExport DirectionTypes getXYDirection(int i, int j);

	//
	// Global Infos
	// All info type strings are upper case and are kept in this hash map for fast lookup
	//
	DllExport int getInfoTypeForString(const char* szType, bool hideAssert = false) const;			// returns the infos index, use this when searching for an info type string
	DllExport void setInfoTypeFromString(const char* szType, int idx);
	DllExport void infoTypeFromStringReset();
	DllExport void addToInfosVectors(void* infoVector);
	DllExport void infosReset();

	DllExport int getNumWorldInfos();
	std::vector<CvWorldInfo*>& getWorldInfo();
	DllExport CvWorldInfo& getWorldInfo(WorldSizeTypes e);

	DllExport int getNumClimateInfos();
	std::vector<CvClimateInfo*>& getClimateInfo();
	DllExport CvClimateInfo& getClimateInfo(ClimateTypes e);

	DllExport int getNumSeaLevelInfos();
	std::vector<CvSeaLevelInfo*>& getSeaLevelInfo();
	DllExport CvSeaLevelInfo& getSeaLevelInfo(SeaLevelTypes e);

	DllExport int getNumColorInfos();
	std::vector<CvColorInfo*>& getColorInfo();
	DllExport CvColorInfo& getColorInfo(ColorTypes e);

	DllExport int getNumPlayerColorInfos();
	std::vector<CvPlayerColorInfo*>& getPlayerColorInfo();
	DllExport CvPlayerColorInfo& getPlayerColorInfo(PlayerColorTypes e);

	int getNumAdvisorInfos();
	std::vector<CvAdvisorInfo*>& getAdvisorInfo();
	CvAdvisorInfo& getAdvisorInfo(AdvisorTypes e);

	DllExport  int getNumHints();
	std::vector<CvInfoBase*>& getHints();
	DllExport CvInfoBase& getHints(int i);

	DllExport int getNumMainMenus();
	std::vector<CvMainMenuInfo*>& getMainMenus();
	DllExport CvMainMenuInfo& getMainMenus(int i);

	DllExport int getNumRouteModelInfos();
	std::vector<CvRouteModelInfo*>& getRouteModelInfo();
	DllExport CvRouteModelInfo& getRouteModelInfo(int i);

	DllExport int getNumRiverInfos();
	std::vector<CvRiverInfo*>& getRiverInfo();
	DllExport CvRiverInfo& getRiverInfo(RiverTypes e);

	DllExport int getNumRiverModelInfos();
	std::vector<CvRiverModelInfo*>& getRiverModelInfo();
	DllExport CvRiverModelInfo& getRiverModelInfo(int i);

	DllExport int getNumWaterPlaneInfos();
	std::vector<CvWaterPlaneInfo*>& getWaterPlaneInfo();
	DllExport CvWaterPlaneInfo& getWaterPlaneInfo(int i);

	DllExport int getNumTerrainPlaneInfos();
	std::vector<CvTerrainPlaneInfo*>& getTerrainPlaneInfo();
	DllExport CvTerrainPlaneInfo& getTerrainPlaneInfo(int i);

	DllExport int getNumCameraOverlayInfos();
	std::vector<CvCameraOverlayInfo*>& getCameraOverlayInfo();
	DllExport CvCameraOverlayInfo& getCameraOverlayInfo(int i);

	DllExport int getNumAnimationPathInfos();
	std::vector<CvAnimationPathInfo*>& getAnimationPathInfo();
	DllExport CvAnimationPathInfo& getAnimationPathInfo(AnimationPathTypes e);

	DllExport int getNumAnimationCategoryInfos();
	std::vector<CvAnimationCategoryInfo*>& getAnimationCategoryInfo();
	DllExport CvAnimationCategoryInfo& getAnimationCategoryInfo(AnimationCategoryTypes e);

	DllExport int getNumEntityEventInfos();
	std::vector<CvEntityEventInfo*>& getEntityEventInfo();
	DllExport CvEntityEventInfo& getEntityEventInfo(EntityEventTypes e);

	DllExport int getNumEffectInfos();
	std::vector<CvEffectInfo*>& getEffectInfo();
	DllExport CvEffectInfo& getEffectInfo(int i);

	DllExport int getNumAttachableInfos();
	std::vector<CvAttachableInfo*>& getAttachableInfo();
	DllExport CvAttachableInfo& getAttachableInfo(int i);

	DllExport int getNumCameraInfos();
	std::vector<CvCameraInfo*>& getCameraInfo();
	DllExport	CvCameraInfo& getCameraInfo(CameraAnimationTypes eCameraAnimationNum);

	DllExport int getNumUnitFormationInfos();
	std::vector<CvUnitFormationInfo*>& getUnitFormationInfo();
	DllExport CvUnitFormationInfo& getUnitFormationInfo(int i);

	int getNumGameTextXML();
	std::vector<CvGameText*>& getGameTextXML();

	DllExport int getNumLandscapeInfos();
	std::vector<CvLandscapeInfo*>& getLandscapeInfo();
	DllExport CvLandscapeInfo& getLandscapeInfo(int iIndex);
	DllExport int getActiveLandscapeID();
	DllExport void setActiveLandscapeID(int iLandscapeID);

	DllExport int getNumTerrainInfos();
	std::vector<CvTerrainInfo*>& getTerrainInfo();
	DllExport CvTerrainInfo& getTerrainInfo(TerrainTypes eTerrainNum);

	int getNumBonusClassInfos();
	std::vector<CvBonusClassInfo*>& getBonusClassInfo();
	CvBonusClassInfo& getBonusClassInfo(BonusClassTypes eBonusNum);

	DllExport int getNumBonusInfos();
	std::vector<CvBonusInfo*>& getBonusInfo();
	DllExport CvBonusInfo& getBonusInfo(BonusTypes eBonusNum);

	DllExport int getNumFeatureInfos();
	std::vector<CvFeatureInfo*>& getFeatureInfo();
	DllExport CvFeatureInfo& getFeatureInfo(FeatureTypes eFeatureNum);

	DllExport int& getNumPlayableCivilizationInfos();
	DllExport int& getNumAIPlayableCivilizationInfos();
	DllExport int getNumCivilizationInfos();
	std::vector<CvCivilizationInfo*>& getCivilizationInfo();
	DllExport CvCivilizationInfo& getCivilizationInfo(CivilizationTypes eCivilizationNum);

	DllExport int getNumLeaderHeadInfos();
	std::vector<CvLeaderHeadInfo*>& getLeaderHeadInfo();
	DllExport CvLeaderHeadInfo& getLeaderHeadInfo(LeaderHeadTypes eLeaderHeadNum);

	int getNumTraitInfos();
	std::vector<CvTraitInfo*>& getTraitInfo();
	CvTraitInfo& getTraitInfo(TraitTypes eTraitNum);

	DllExport int getNumCursorInfos();
	std::vector<CvCursorInfo*>& getCursorInfo();
	DllExport	CvCursorInfo& getCursorInfo(CursorTypes eCursorNum);

	DllExport int getNumThroneRoomCameras();
	std::vector<CvThroneRoomCamera*>& getThroneRoomCamera();
	DllExport	CvThroneRoomCamera& getThroneRoomCamera(int iIndex);

	DllExport int getNumThroneRoomInfos();
	std::vector<CvThroneRoomInfo*>& getThroneRoomInfo();
	DllExport	CvThroneRoomInfo& getThroneRoomInfo(int iIndex);

	DllExport int getNumThroneRoomStyleInfos();
	std::vector<CvThroneRoomStyleInfo*>& getThroneRoomStyleInfo();
	DllExport	CvThroneRoomStyleInfo& getThroneRoomStyleInfo(int iIndex);

	DllExport int getNumSlideShowInfos();
	std::vector<CvSlideShowInfo*>& getSlideShowInfo();
	DllExport	CvSlideShowInfo& getSlideShowInfo(int iIndex);

	DllExport int getNumSlideShowRandomInfos();
	std::vector<CvSlideShowRandomInfo*>& getSlideShowRandomInfo();
	DllExport	CvSlideShowRandomInfo& getSlideShowRandomInfo(int iIndex);

	DllExport int getNumWorldPickerInfos();
	std::vector<CvWorldPickerInfo*>& getWorldPickerInfo();
	DllExport	CvWorldPickerInfo& getWorldPickerInfo(int iIndex);

	DllExport int getNumSpaceShipInfos();
	std::vector<CvSpaceShipInfo*>& getSpaceShipInfo();
	DllExport	CvSpaceShipInfo& getSpaceShipInfo(int iIndex);

	int getNumUnitInfos();
	std::vector<CvUnitInfo*>& getUnitInfo();
	CvUnitInfo& getUnitInfo(UnitTypes eUnitNum);

	int getNumSpecialUnitInfos();
	std::vector<CvSpecialUnitInfo*>& getSpecialUnitInfo();
	CvSpecialUnitInfo& getSpecialUnitInfo(SpecialUnitTypes eSpecialUnitNum);

	int getNumConceptInfos();
	std::vector<CvInfoBase*>& getConceptInfo();
	CvInfoBase& getConceptInfo(ConceptTypes e);

	int getNumNewConceptInfos();
	std::vector<CvInfoBase*>& getNewConceptInfo();
	CvInfoBase& getNewConceptInfo(NewConceptTypes e);

	int getNumCityTabInfos();
	std::vector<CvInfoBase*>& getCityTabInfo();
	CvInfoBase& getCityTabInfo(CityTabTypes e);

	int getNumCalendarInfos();
	std::vector<CvInfoBase*>& getCalendarInfo();
	CvInfoBase& getCalendarInfo(CalendarTypes e);

	int getNumSeasonInfos();
	std::vector<CvInfoBase*>& getSeasonInfo();
	CvInfoBase& getSeasonInfo(SeasonTypes e);

	int getNumMonthInfos();
	std::vector<CvInfoBase*>& getMonthInfo();
	CvInfoBase& getMonthInfo(MonthTypes e);

	int getNumDenialInfos();
	std::vector<CvInfoBase*>& getDenialInfo();
	CvInfoBase& getDenialInfo(DenialTypes e);

	int getNumInvisibleInfos();
	std::vector<CvInfoBase*>& getInvisibleInfo();
	CvInfoBase& getInvisibleInfo(InvisibleTypes e);

	int getNumVoteSourceInfos();
	std::vector<CvVoteSourceInfo*>& getVoteSourceInfo();
	CvVoteSourceInfo& getVoteSourceInfo(VoteSourceTypes e);

	int getNumUnitCombatInfos();
	std::vector<CvInfoBase*>& getUnitCombatInfo();
	CvInfoBase& getUnitCombatInfo(UnitCombatTypes e);

	std::vector<CvInfoBase*>& getDomainInfo();
	CvInfoBase& getDomainInfo(DomainTypes e);

	std::vector<CvInfoBase*>& getUnitAIInfo();
	CvInfoBase& getUnitAIInfo(UnitAITypes eUnitAINum);

	std::vector<CvInfoBase*>& getAttitudeInfo();
	CvInfoBase& getAttitudeInfo(AttitudeTypes eAttitudeNum);

	std::vector<CvInfoBase*>& getMemoryInfo();
	CvInfoBase& getMemoryInfo(MemoryTypes eMemoryNum);

	DllExport int getNumGameOptionInfos();
	std::vector<CvGameOptionInfo*>& getGameOptionInfo();
	DllExport	CvGameOptionInfo& getGameOptionInfo(GameOptionTypes eGameOptionNum);

	DllExport int getNumMPOptionInfos();
	std::vector<CvMPOptionInfo*>& getMPOptionInfo();
	DllExport	CvMPOptionInfo& getMPOptionInfo(MultiplayerOptionTypes eMPOptionNum);

	DllExport int getNumForceControlInfos();
	std::vector<CvForceControlInfo*>& getForceControlInfo();
	DllExport	CvForceControlInfo& getForceControlInfo(ForceControlTypes eForceControlNum);

	std::vector<CvPlayerOptionInfo*>& getPlayerOptionInfo();
	DllExport	CvPlayerOptionInfo& getPlayerOptionInfo(PlayerOptionTypes ePlayerOptionNum);

	std::vector<CvGraphicOptionInfo*>& getGraphicOptionInfo();
	DllExport	CvGraphicOptionInfo& getGraphicOptionInfo(GraphicOptionTypes eGraphicOptionNum);

	std::vector<CvYieldInfo*>& getYieldInfo();
	CvYieldInfo& getYieldInfo(YieldTypes eYieldNum);

	std::vector<CvCommerceInfo*>& getCommerceInfo();
	CvCommerceInfo& getCommerceInfo(CommerceTypes eCommerceNum);

	DllExport int getNumRouteInfos();
	std::vector<CvRouteInfo*>& getRouteInfo();
	DllExport	CvRouteInfo& getRouteInfo(RouteTypes eRouteNum);

	DllExport int getNumImprovementInfos();
	std::vector<CvImprovementInfo*>& getImprovementInfo();
	DllExport CvImprovementInfo& getImprovementInfo(ImprovementTypes eImprovementNum);

	DllExport int getNumGoodyInfos();
	std::vector<CvGoodyInfo*>& getGoodyInfo();
	DllExport CvGoodyInfo& getGoodyInfo(GoodyTypes eGoodyNum);

	DllExport int getNumBuildInfos();
	std::vector<CvBuildInfo*>& getBuildInfo();
	DllExport CvBuildInfo& getBuildInfo(BuildTypes eBuildNum);

	DllExport int getNumHandicapInfos();
	std::vector<CvHandicapInfo*>& getHandicapInfo();
	DllExport CvHandicapInfo& getHandicapInfo(HandicapTypes eHandicapNum);

	DllExport int getNumGameSpeedInfos();
	std::vector<CvGameSpeedInfo*>& getGameSpeedInfo();
	DllExport CvGameSpeedInfo& getGameSpeedInfo(GameSpeedTypes eGameSpeedNum);

	DllExport int getNumTurnTimerInfos();
	std::vector<CvTurnTimerInfo*>& getTurnTimerInfo();
	DllExport CvTurnTimerInfo& getTurnTimerInfo(TurnTimerTypes eTurnTimerNum);

	int getNumProcessInfos();
	std::vector<CvProcessInfo*>& getProcessInfo();
	CvProcessInfo& getProcessInfo(ProcessTypes e);

	int getNumVoteInfos();
	std::vector<CvVoteInfo*>& getVoteInfo();
	CvVoteInfo& getVoteInfo(VoteTypes e);

	int getNumProjectInfos();
	std::vector<CvProjectInfo*>& getProjectInfo();
	CvProjectInfo& getProjectInfo(ProjectTypes e);

	int getNumBuildingClassInfos();
	std::vector<CvBuildingClassInfo*>& getBuildingClassInfo();
	CvBuildingClassInfo& getBuildingClassInfo(BuildingClassTypes eBuildingClassNum);

	int getNumBuildingInfos();
	std::vector<CvBuildingInfo*>& getBuildingInfo();
	CvBuildingInfo& getBuildingInfo(BuildingTypes eBuildingNum);

	int getNumSpecialBuildingInfos();
	std::vector<CvSpecialBuildingInfo*>& getSpecialBuildingInfo();
	CvSpecialBuildingInfo& getSpecialBuildingInfo(SpecialBuildingTypes eSpecialBuildingNum);

	int getNumUnitClassInfos();
	std::vector<CvUnitClassInfo*>& getUnitClassInfo();
	CvUnitClassInfo& getUnitClassInfo(UnitClassTypes eUnitClassNum);

	DllExport int getNumActionInfos();
	std::vector<CvActionInfo*>& getActionInfo();
	DllExport CvActionInfo& getActionInfo(int i);

	std::vector<CvMissionInfo*>& getMissionInfo();
	DllExport CvMissionInfo& getMissionInfo(MissionTypes eMissionNum);

	std::vector<CvControlInfo*>& getControlInfo();
	DllExport CvControlInfo& getControlInfo(ControlTypes eControlNum);

	std::vector<CvCommandInfo*>& getCommandInfo();
	DllExport CvCommandInfo& getCommandInfo(CommandTypes eCommandNum);

	DllExport int getNumAutomateInfos();
	std::vector<CvAutomateInfo*>& getAutomateInfo();
	DllExport CvAutomateInfo& getAutomateInfo(int iAutomateNum);

	int getNumPromotionInfos();
	std::vector<CvPromotionInfo*>& getPromotionInfo();
	CvPromotionInfo& getPromotionInfo(PromotionTypes ePromotionNum);

	int getNumTechInfos();
	std::vector<CvTechInfo*>& getTechInfo();
	CvTechInfo& getTechInfo(TechTypes eTechNum);

	int getNumReligionInfos();
	std::vector<CvReligionInfo*>& getReligionInfo();
	CvReligionInfo& getReligionInfo(ReligionTypes eReligionNum);

	int getNumCorporationInfos();
	std::vector<CvCorporationInfo*>& getCorporationInfo();
	CvCorporationInfo& getCorporationInfo(CorporationTypes eCorporationNum);

	int getNumSpecialistInfos();
	std::vector<CvSpecialistInfo*>& getSpecialistInfo();
	CvSpecialistInfo& getSpecialistInfo(SpecialistTypes eSpecialistNum);

	int getNumCivicOptionInfos();
	std::vector<CvCivicOptionInfo*>& getCivicOptionInfo();
	CvCivicOptionInfo& getCivicOptionInfo(CivicOptionTypes eCivicOptionNum);

	int getNumCivicInfos();
	std::vector<CvCivicInfo*>& getCivicInfo();
	CvCivicInfo& getCivicInfo(CivicTypes eCivicNum);

	int getNumDiplomacyInfos();
	std::vector<CvDiplomacyInfo*>& getDiplomacyInfo();
	CvDiplomacyInfo& getDiplomacyInfo(int iDiplomacyNum);

	DllExport int getNumEraInfos();
	std::vector<CvEraInfo*>& getEraInfo();
	DllExport CvEraInfo& getEraInfo(EraTypes eEraNum);

	int getNumHurryInfos();
	std::vector<CvHurryInfo*>& getHurryInfo();
	CvHurryInfo& getHurryInfo(HurryTypes eHurryNum);

	int getNumEmphasizeInfos();
	std::vector<CvEmphasizeInfo*>& getEmphasizeInfo();
	CvEmphasizeInfo& getEmphasizeInfo(EmphasizeTypes eEmphasizeNum);

	int getNumUpkeepInfos();
	std::vector<CvUpkeepInfo*>& getUpkeepInfo();
	CvUpkeepInfo& getUpkeepInfo(UpkeepTypes eUpkeepNum);

	int getNumCultureLevelInfos();
	std::vector<CvCultureLevelInfo*>& getCultureLevelInfo();
	CvCultureLevelInfo& getCultureLevelInfo(CultureLevelTypes eCultureLevelNum);

	DllExport int getNumVictoryInfos();
	std::vector<CvVictoryInfo*>& getVictoryInfo();
	DllExport CvVictoryInfo& getVictoryInfo(VictoryTypes eVictoryNum);

	int getNumQuestInfos();
	std::vector<CvQuestInfo*>& getQuestInfo();
	CvQuestInfo& getQuestInfo(int iIndex);

	int getNumTutorialInfos();
	std::vector<CvTutorialInfo*>& getTutorialInfo();
	CvTutorialInfo& getTutorialInfo(int i);

	int getNumEventTriggerInfos();
	std::vector<CvEventTriggerInfo*>& getEventTriggerInfo();
	CvEventTriggerInfo& getEventTriggerInfo(EventTriggerTypes eEventTrigger);

	int getNumEventInfos();
	std::vector<CvEventInfo*>& getEventInfo();
	CvEventInfo& getEventInfo(EventTypes eEvent);

	int getNumEspionageMissionInfos();
	std::vector<CvEspionageMissionInfo*>& getEspionageMissionInfo();
	CvEspionageMissionInfo& getEspionageMissionInfo(EspionageMissionTypes eEspionageMissionNum);

	int getNumUnitArtStyleTypeInfos();
	std::vector<CvUnitArtStyleTypeInfo*>& getUnitArtStyleTypeInfo();
	CvUnitArtStyleTypeInfo& getUnitArtStyleTypeInfo(UnitArtStyleTypes eUnitArtStyleTypeNum);

	//
	// Global Types
	// All type strings are upper case and are kept in this hash map for fast lookup
	// The other functions are kept for convenience when enumerating, but most are not used
	//
	DllExport int getTypesEnum(const char* szType) const;				// use this when searching for a type
	DllExport void setTypesEnum(const char* szType, int iEnum);

	DllExport int getNUM_ENGINE_DIRTY_BITS() const;
	DllExport int getNUM_INTERFACE_DIRTY_BITS() const;
	DllExport int getNUM_YIELD_TYPES() const;
	DllExport int getNUM_COMMERCE_TYPES() const;
	DllExport int getNUM_FORCECONTROL_TYPES() const;
	DllExport int getNUM_INFOBAR_TYPES() const;
	DllExport int getNUM_HEALTHBAR_TYPES() const;
	DllExport int getNUM_CONTROL_TYPES() const;
	DllExport int getNUM_LEADERANIM_TYPES() const;

	DllExport int& getNumEntityEventTypes();
	CvString*& getEntityEventTypes();
	DllExport CvString& getEntityEventTypes(EntityEventTypes e);

	DllExport int& getNumAnimationOperatorTypes();
	CvString*& getAnimationOperatorTypes();
	DllExport CvString& getAnimationOperatorTypes(AnimationOperatorTypes e);

	CvString*& getFunctionTypes();
	DllExport CvString& getFunctionTypes(FunctionTypes e);

	int& getNumFlavorTypes();
	CvString*& getFlavorTypes();
	CvString& getFlavorTypes(FlavorTypes e);

	DllExport int& getNumArtStyleTypes();
	CvString*& getArtStyleTypes();
	DllExport CvString& getArtStyleTypes(ArtStyleTypes e);

	int& getNumCitySizeTypes();
	CvString*& getCitySizeTypes();
	CvString& getCitySizeTypes(int i);

	CvString*& getContactTypes();
	CvString& getContactTypes(ContactTypes e);

	CvString*& getDiplomacyPowerTypes();
	CvString& getDiplomacyPowerTypes(DiplomacyPowerTypes e);

	CvString*& getAutomateTypes();
	CvString& getAutomateTypes(AutomateTypes e);

	CvString*& getDirectionTypes();
	DllExport CvString& getDirectionTypes(AutomateTypes e);

	DllExport int& getNumFootstepAudioTypes();
	CvString*& getFootstepAudioTypes();
	DllExport CvString& getFootstepAudioTypes(int i);
	DllExport int getFootstepAudioTypeByTag(CvString strTag);

	CvString*& getFootstepAudioTags();
	DllExport CvString& getFootstepAudioTags(int i);

	CvString& getCurrentXMLFile();
	void setCurrentXMLFile(const TCHAR* szFileName);

	//
	///////////////// BEGIN global defines
	// THESE ARE READ-ONLY
	//

	DllExport FVariableSystem* getDefinesVarSystem();
	DllExport void cacheGlobals();

	// ***** EXPOSED TO PYTHON *****
	bool getDefineBOOL(const char* szName, bool bDefault = false) const;
	DllExport int getDefineINT(const char* szName) const;
	int getDefineINT(const char* szName, int iDefault) const;
	DllExport float getDefineFLOAT(const char* szName) const;
	float getDefineFLOAT(const char* szName, float fDefault) const;
	DllExport const char* getDefineSTRING(const char* szName) const;
	const char* getDefineSTRING(const char* szName, const char* szDefault) const;
	DllExport void setDefineINT(const char* szName, int iValue);
	DllExport void setDefineFLOAT(const char* szName, float fValue);
	DllExport void setDefineSTRING(const char* szName, const char* szValue);

	inline int getEXTRA_YIELD() { return m_iEXTRA_YIELD; } // K-Mod (why aren't all these functions inline?)
	int getMOVE_DENOMINATOR();
	int getNUM_UNIT_PREREQ_OR_BONUSES();
	int getNUM_BUILDING_PREREQ_OR_BONUSES();
	int getFOOD_CONSUMPTION_PER_POPULATION();
	int getMAX_HIT_POINTS();
	int getPATH_DAMAGE_WEIGHT();
	int getHILLS_EXTRA_DEFENSE();
	int getRIVER_ATTACK_MODIFIER();
	int getAMPHIB_ATTACK_MODIFIER();
	int getHILLS_EXTRA_MOVEMENT();
	DllExport int getMAX_PLOT_LIST_ROWS();
	DllExport int getUNIT_MULTISELECT_MAX();
	int getPERCENT_ANGER_DIVISOR();
	DllExport int getEVENT_MESSAGE_TIME();
	int getROUTE_FEATURE_GROWTH_MODIFIER();
	int getFEATURE_GROWTH_MODIFIER();
	int getMIN_CITY_RANGE();
	int getCITY_MAX_NUM_BUILDINGS();
	int getNUM_UNIT_AND_TECH_PREREQS();
	int getNUM_AND_TECH_PREREQS();
	int getNUM_OR_TECH_PREREQS();
	int getLAKE_MAX_AREA_SIZE();
	int getNUM_ROUTE_PREREQ_OR_BONUSES();
	int getMIN_WATER_SIZE_FOR_OCEAN();
	int getFORTIFY_MODIFIER_PER_TURN();
	int getMAX_CITY_DEFENSE_DAMAGE();
	int getNUM_CORPORATION_PREREQ_BONUSES();
	int getPEAK_SEE_THROUGH_CHANGE();
	int getHILLS_SEE_THROUGH_CHANGE();
	int getSEAWATER_SEE_FROM_CHANGE();
	int getPEAK_SEE_FROM_CHANGE();
	int getHILLS_SEE_FROM_CHANGE();
	int getUSE_SPIES_NO_ENTER_BORDERS();

	DllExport float getCAMERA_MIN_YAW();
	DllExport float getCAMERA_MAX_YAW();
	DllExport float getCAMERA_FAR_CLIP_Z_HEIGHT();
	DllExport float getCAMERA_MAX_TRAVEL_DISTANCE();
	DllExport float getCAMERA_START_DISTANCE();
	DllExport float getAIR_BOMB_HEIGHT();
	DllExport float getPLOT_SIZE();
	DllExport float getCAMERA_SPECIAL_PITCH();
	DllExport float getCAMERA_MAX_TURN_OFFSET();
	DllExport float getCAMERA_MIN_DISTANCE();
	DllExport float getCAMERA_UPPER_PITCH();
	DllExport float getCAMERA_LOWER_PITCH();
	DllExport float getFIELD_OF_VIEW();
	DllExport float getSHADOW_SCALE();
	DllExport float getUNIT_MULTISELECT_DISTANCE();

	int getUSE_CANNOT_FOUND_CITY_CALLBACK();
	int getUSE_CAN_FOUND_CITIES_ON_WATER_CALLBACK();
	int getUSE_IS_PLAYER_RESEARCH_CALLBACK();
	int getUSE_CAN_RESEARCH_CALLBACK();
	int getUSE_CANNOT_DO_CIVIC_CALLBACK();
	int getUSE_CAN_DO_CIVIC_CALLBACK();
	int getUSE_CANNOT_CONSTRUCT_CALLBACK();
	int getUSE_CAN_CONSTRUCT_CALLBACK();
	int getUSE_CAN_DECLARE_WAR_CALLBACK();
	int getUSE_CANNOT_RESEARCH_CALLBACK();
	int getUSE_GET_UNIT_COST_MOD_CALLBACK();
	int getUSE_GET_BUILDING_COST_MOD_CALLBACK();
	int getUSE_GET_CITY_FOUND_VALUE_CALLBACK();
	int getUSE_CANNOT_HANDLE_ACTION_CALLBACK();
	int getUSE_CAN_BUILD_CALLBACK();
	int getUSE_CANNOT_TRAIN_CALLBACK();
	int getUSE_CAN_TRAIN_CALLBACK();
	int getUSE_UNIT_CANNOT_MOVE_INTO_CALLBACK();
	int getUSE_USE_CANNOT_SPREAD_RELIGION_CALLBACK();
	DllExport int getUSE_FINISH_TEXT_CALLBACK();
	int getUSE_ON_UNIT_SET_XY_CALLBACK();
	int getUSE_ON_UNIT_SELECTED_CALLBACK();
	int getUSE_ON_UPDATE_CALLBACK();
	int getUSE_ON_UNIT_CREATED_CALLBACK();
	int getUSE_ON_UNIT_LOST_CALLBACK();
	inline int getACO_SHOW_MODIFIER_LABELS() { return m_iACO_SHOW_MODIFIER_LABELS; }
	inline int getACO_SHOW_SURVIVAL_ODDS() { return m_iACO_SHOW_SURVIVAL_ODDS; }
	inline int getACO_SHOW_ATTACKER_HEALTH_BARS() { return m_iACO_SHOW_ATTACKER_HEALTH_BARS; }
	inline int getACO_SHOW_DEFENDER_HEALTH_BARS() { return m_iACO_SHOW_DEFENDER_HEALTH_BARS; }
	inline int getACO_SHOW_BASIC_INFO() { return m_iACO_SHOW_BASIC_INFO; }
	inline int getACO_SHOW_EXPERIENCE_RANGE() { return m_iACO_SHOW_EXPERIENCE_RANGE; }
	inline int getACO_SHOW_AVERAGE_HEALTH() { return m_iACO_SHOW_AVERAGE_HEALTH; }
	inline int getACO_SHOW_UNHARMED_ODDS() { return m_iACO_SHOW_UNHARMED_ODDS; }
	inline int getACO_SHOW_UNROUNDED_EXPERIENCE() { return m_iACO_SHOW_UNROUNDED_EXPERIENCE; }
	inline int getACO_SHOW_SHIFT_INSTRUCTIONS() { return m_iACO_SHOW_SHIFT_INSTRUCTIONS; }
	inline int getACO_SHOW_ATTACKER_INFO() { return m_iACO_SHOW_ATTACKER_INFO; }
	inline int getACO_SHOW_DEFENDER_INFO() { return m_iACO_SHOW_DEFENDER_INFO; }
	inline int getACO_SHOW_DEFENCE_MODIFIERS() { return m_iACO_SHOW_DEFENCE_MODIFIERS; }
	inline int getACO_SHOW_TOTAL_DEFENCE_MODIFIER() { return m_iACO_SHOW_TOTAL_DEFENCE_MODIFIER; }
	inline int getBBAI_DEFENSIVE_PACT_BEHAVIOR() { return m_iBBAI_DEFENSIVE_PACT_BEHAVIOR; }
	inline int getBBAI_ATTACK_CITY_STACK_RATIO() { return m_iBBAI_ATTACK_CITY_STACK_RATIO; }
	inline int getBBAI_SKIP_BOMBARD_BEST_ATTACK_ODDS() { return m_iBBAI_SKIP_BOMBARD_BEST_ATTACK_ODDS; }
	inline int getBBAI_SKIP_BOMBARD_BASE_STACK_RATIO() { return m_iBBAI_SKIP_BOMBARD_BASE_STACK_RATIO; }
	inline int getBBAI_SKIP_BOMBARD_MIN_STACK_RATIO() { return m_iBBAI_SKIP_BOMBARD_MIN_STACK_RATIO; }
	inline int getCOMBAT_DAMAGE() { return m_iCOMBAT_DAMAGE; }
	inline int getCOMBAT_DIE_SIDES() { return m_iCOMBAT_DIE_SIDES; }
	inline int getLFBBasedOnGeneral() { return m_iLFBBasedOnGeneral; }
	inline int getLFBBasedOnExperience() { return m_iLFBBasedOnExperience; }
	inline int getLFBBasedOnLimited() { return m_iLFBBasedOnLimited; }
	inline int getLFBBasedOnHealer() { return m_iLFBBasedOnHealer; }
	inline int getLFBDefensiveAdjustment() { return m_iLFBDefensiveAdjustment; }
	inline int getLFBAdjustNumerator() { return m_iLFBAdjustNumerator; }
	inline int getLFBAdjustDenominator() { return m_iLFBAdjustDenominator; }
	inline int getTECH_COST_FIRST_KNOWN_PREREQ_MODIFIER() { return m_iTECH_COST_FIRST_KNOWN_PREREQ_MODIFIER; }
	inline int getTECH_COST_KNOWN_PREREQ_MODIFIER() { return m_iTECH_COST_KNOWN_PREREQ_MODIFIER; }
	inline int getTECH_COST_MODIFIER() { return m_iTECH_COST_MODIFIER; }
	inline int getTECH_DIFFUSION_KNOWN_TEAM_MODIFIER() { return m_iTECH_DIFFUSION_KNOWN_TEAM_MODIFIER; }
	inline int getTECH_DIFFUSION_WELFARE_THRESHOLD() { return m_iTECH_DIFFUSION_WELFARE_THRESHOLD; }
	inline int getTECH_DIFFUSION_WELFARE_MODIFIER() { return m_iTECH_DIFFUSION_WELFARE_MODIFIER; }
	inline int getWAR_SUCCESS_CITY_CAPTURING() { return m_iWAR_SUCCESS_CITY_CAPTURING; }
	inline int getREHOME_PERCENT_CHANCE() { return m_iREHOME_PERCENT_CHANCE; }

	inline bool getACO_DEBUG() { return m_bACO_DEBUG; }
	inline bool getACO_ENABLED() { return m_bACO_ENABLED; }
	inline bool getACO_FORCE_ORIGINAL_ODDS() { return m_bACO_FORCE_ORIGINAL_ODDS; }
	inline bool getACO_IGNORE_BARB_FREE_WINS() { return m_bACO_IGNORE_BARB_FREE_WINS; }
	inline bool getACO_MERGE_SHORT_BARS() { return m_bACO_MERGE_SHORT_BARS; }
	inline bool getACO_SWAP_VIEWS() { return m_bACO_SWAP_VIEWS; }
	inline bool getBBAI_AIR_COMBAT() { return m_bBBAI_AIR_COMBAT; }
	inline bool getBBAI_HUMAN_AS_VASSAL_OPTION() { return m_bBBAI_HUMAN_AS_VASSAL_OPTION; }
	inline bool getBBAI_HUMAN_VASSAL_WAR_BUILD() { return m_bBBAI_HUMAN_VASSAL_WAR_BUILD; }
	inline bool getBUG_BUILDING_ADDITIONAL_COMMERCE_HOVER() { return m_bBUG_BUILDING_ADDITIONAL_COMMERCE_HOVER; }
	inline bool getBUG_BUILDING_ADDITIONAL_GREAT_PEOPLE_HOVER() { return m_bBUG_BUILDING_ADDITIONAL_GREAT_PEOPLE_HOVER; }
	inline bool getBUG_BUILDING_ADDITIONAL_HAPPINESS_HOVER() { return m_bBUG_BUILDING_ADDITIONAL_HAPPINESS_HOVER; }
	inline bool getBUG_BUILDING_ADDITIONAL_HEALTH_HOVER() { return m_bBUG_BUILDING_ADDITIONAL_HEALTH_HOVER; }
	inline bool getBUG_BUILDING_ADDITIONAL_PRODUCTION_HOVER() { return m_bBUG_BUILDING_ADDITIONAL_PRODUCTION_HOVER; }
	inline bool getBUG_BUILDING_HOVER_ACTUAL_EFFECTS() { return m_bBUG_BUILDING_HOVER_ACTUAL_EFFECTS; }
	inline bool getBUG_BUILDING_SAVED_MAINTENANCE_HOVER() { return m_bBUG_BUILDING_SAVED_MAINTENANCE_HOVER; }
	inline bool getBUG_CITY_SCREEN_BASE_COMMERCE_HOVER() { return m_bBUG_CITY_SCREEN_BASE_COMMERCE_HOVER; }
	inline bool getBUG_MISC_SPECIALIST_HOVER_ACTUAL_EFFECTS() { return m_bBUG_MISC_SPECIALIST_HOVER_ACTUAL_EFFECTS; }
	inline bool getLFBEnable() { return m_bLFBEnable; }
	inline bool getLFBUseCombatOdds() { return m_bLFBUseCombatOdds; }
	inline bool getLFBUseSlidingScale() { return m_bLFBUseSlidingScale; }
	inline bool getUSE_AI_UNIT_UPDATE_CALLBACK() { return m_bUSE_AI_UNIT_UPDATE_CALLBACK; }
	inline bool getUSE_AI_DO_DIPLO_CALLBACK() { return m_bUSE_AI_DO_DIPLO_CALLBACK; }
	inline bool getUSE_AI_CHOOSE_PRODUCTION_CALLBACK() { return m_bUSE_AI_CHOOSE_PRODUCTION_CALLBACK; }
	inline bool getUSE_AI_DO_WAR_CALLBACK() { return m_bUSE_AI_DO_WAR_CALLBACK; }
	inline bool getUSE_AI_CHOOSE_TECH_CALLBACK() { return m_bUSE_AI_CHOOSE_TECH_CALLBACK; }
	inline bool getUSE_DO_COMBAT_CALLBACK() { return m_bUSE_DO_COMBAT_CALLBACK; }
	inline bool getUSE_DO_CULTURE_CALLBACK() { return m_bUSE_DO_CULTURE_CALLBACK; }
	inline bool getUSE_DO_GREAT_PEOPLE_CALLBACK() { return m_bUSE_DO_GREAT_PEOPLE_CALLBACK; }
	inline bool getUSE_DO_GROWTH_CALLBACK() { return m_bUSE_DO_GROWTH_CALLBACK; }
	inline bool getUSE_DO_PLOT_CULTURE_CALLBACK() { return m_bUSE_DO_PLOT_CULTURE_CALLBACK; }
	inline bool getUSE_DO_MELTDOWN_CALLBACK() { return m_bUSE_DO_MELTDOWN_CALLBACK; }
	inline bool getUSE_DO_PILLAGE_GOLD_CALLBACK() { return m_bUSE_DO_PILLAGE_GOLD_CALLBACK; }
	inline bool getUSE_DO_PRODUCTION_CALLBACK() { return m_bUSE_DO_PRODUCTION_CALLBACK; }
	inline bool getUSE_DO_RELIGION_CALLBACK() { return m_bUSE_DO_RELIGION_CALLBACK; }
	inline bool getUSE_GET_EXPERIENCE_NEEDED_CALLBACK() { return m_bUSE_GET_EXPERIENCE_NEEDED_CALLBACK; }
	inline bool getUSE_UNIT_UPGRADE_PRICE_CALLBACK() { return m_bUSE_UNIT_UPGRADE_PRICE_CALLBACK; }
	inline bool getRAPID_UNIT_CYCLING() { return m_bRAPID_UNIT_CYCLING; }
	inline bool getSIMPLE_SELECTION_MODE() { return m_bSIMPLE_SELECTION_MODE; }
	inline bool getTECH_DIFFUSION_ENABLE() { return m_bTECH_DIFFUSION_ENABLE; }

	// more reliable versions of the 'gDLL->xxxKey' functions:
	inline bool altKey() { return (GetKeyState(VK_MENU) & 0x8000); }
	inline bool ctrlKey() { return (GetKeyState(VK_CONTROL) & 0x8000); }
	inline bool shiftKey() { return (GetKeyState(VK_SHIFT) & 0x8000); }
	// NOTE: I've replaced all calls to the gDLL key functions with calls to these functions.

	inline bool suppressCycling() { return (GetKeyState('X') & 0x8000); } // hold X to temporarily suppress automatic unit cycling.

	DllExport int getMAX_CIV_PLAYERS();
	DllExport int getMAX_PLAYERS();
	DllExport int getMAX_CIV_TEAMS();
	DllExport int getMAX_TEAMS();
	DllExport int getBARBARIAN_PLAYER();
	DllExport int getBARBARIAN_TEAM();
	DllExport int getINVALID_PLOT_COORD();
	DllExport int getNUM_CITY_PLOTS();
	DllExport int getCITY_HOME_PLOT();

	// ***** END EXPOSED TO PYTHON *****

	////////////// END DEFINES //////////////////

	DllExport void setDLLIFace(CvDLLUtilityIFaceBase* pDll);
#ifdef _USRDLL
	CvDLLUtilityIFaceBase* getDLLIFace() { return m_pDLL; }		// inlined for perf reasons, do not use outside of dll
#endif
	DllExport CvDLLUtilityIFaceBase* getDLLIFaceNonInl();
	DllExport void setDLLProfiler(FProfiler* prof);
	FProfiler* getDLLProfiler();
	DllExport void enableDLLProfiler(bool bEnable);
	bool isDLLProfilerEnabled() const;

	DllExport bool IsGraphicsInitialized() const;
	DllExport void SetGraphicsInitialized(bool bVal);

	// for caching
	DllExport bool readBuildingInfoArray(FDataStreamBase* pStream);
	DllExport void writeBuildingInfoArray(FDataStreamBase* pStream);

	DllExport bool readTechInfoArray(FDataStreamBase* pStream);
	DllExport void writeTechInfoArray(FDataStreamBase* pStream);

	DllExport bool readUnitInfoArray(FDataStreamBase* pStream);
	DllExport void writeUnitInfoArray(FDataStreamBase* pStream);

	DllExport bool readLeaderHeadInfoArray(FDataStreamBase* pStream);
	DllExport void writeLeaderHeadInfoArray(FDataStreamBase* pStream);

	DllExport bool readCivilizationInfoArray(FDataStreamBase* pStream);
	DllExport void writeCivilizationInfoArray(FDataStreamBase* pStream);

	DllExport bool readPromotionInfoArray(FDataStreamBase* pStream);
	DllExport void writePromotionInfoArray(FDataStreamBase* pStream);

	DllExport bool readDiplomacyInfoArray(FDataStreamBase* pStream);
	DllExport void writeDiplomacyInfoArray(FDataStreamBase* pStream);

	DllExport bool readCivicInfoArray(FDataStreamBase* pStream);
	DllExport void writeCivicInfoArray(FDataStreamBase* pStream);

	DllExport bool readHandicapInfoArray(FDataStreamBase* pStream);
	DllExport void writeHandicapInfoArray(FDataStreamBase* pStream);

	DllExport bool readBonusInfoArray(FDataStreamBase* pStream);
	DllExport void writeBonusInfoArray(FDataStreamBase* pStream);

	DllExport bool readImprovementInfoArray(FDataStreamBase* pStream);
	DllExport void writeImprovementInfoArray(FDataStreamBase* pStream);

	DllExport bool readEventInfoArray(FDataStreamBase* pStream);
	DllExport void writeEventInfoArray(FDataStreamBase* pStream);

	DllExport bool readEventTriggerInfoArray(FDataStreamBase* pStream);
	DllExport void writeEventTriggerInfoArray(FDataStreamBase* pStream);

	//
	// additional accessors for initting globals
	//

	DllExport void setInterface(CvInterface* pVal);
	DllExport void setDiplomacyScreen(CvDiplomacyScreen* pVal);
	DllExport void setMPDiplomacyScreen(CMPDiplomacyScreen* pVal);
	DllExport void setMessageQueue(CMessageQueue* pVal);
	DllExport void setHotJoinMessageQueue(CMessageQueue* pVal);
	DllExport void setMessageControl(CMessageControl* pVal);
	DllExport void setSetupData(CvSetupData* pVal);
	DllExport void setMessageCodeTranslator(CvMessageCodeTranslator* pVal);
	DllExport void setDropMgr(CvDropMgr* pVal);
	DllExport void setPortal(CvPortal* pVal);
	DllExport void setStatsReport(CvStatsReporter* pVal);
	DllExport void setPathFinder(FAStar* pVal);
	DllExport void setInterfacePathFinder(FAStar* pVal);
	DllExport void setStepFinder(FAStar* pVal);
	DllExport void setRouteFinder(FAStar* pVal);
	DllExport void setBorderFinder(FAStar* pVal);
	DllExport void setAreaFinder(FAStar* pVal);
	DllExport void setPlotGroupFinder(FAStar* pVal);

	// So that CvEnums are moddable in the DLL
	DllExport int getNumDirections() const;
	DllExport int getNumGameOptions() const;
	DllExport int getNumMPOptions() const;
	DllExport int getNumSpecialOptions() const;
	DllExport int getNumGraphicOptions() const;
	DllExport int getNumTradeableItems() const;
	DllExport int getNumBasicItems() const;
	DllExport int getNumTradeableHeadings() const;
	DllExport int getNumCommandInfos() const;
	DllExport int getNumControlInfos() const;
	DllExport int getNumMissionInfos() const;
	DllExport int getNumPlayerOptionInfos() const;
	DllExport int getMaxNumSymbols() const;
	DllExport int getNumGraphicLevels() const;
	DllExport int getNumGlobeLayers() const;

	void deleteInfoArrays();

protected:

	bool m_bGraphicsInitialized;
	bool m_bDLLProfiler;
	bool m_bLogging;
	bool m_bRandLogging;
	bool m_bSynchLogging;
	bool m_bOverwriteLogs;
	NiPoint3  m_pt3CameraDir;
	int m_iNewPlayers;

	CMainMenu* m_pkMainMenu;

	bool m_bZoomOut;
	bool m_bZoomIn;
	bool m_bLoadGameFromFile;

	FMPIManager* m_pFMPMgr;

	CvRandom* m_asyncRand;

	CvGameAI* m_game;

	CMessageQueue* m_messageQueue;
	CMessageQueue* m_hotJoinMsgQueue;
	CMessageControl* m_messageControl;
	CvSetupData* m_setupData;
	CvInitCore* m_iniInitCore;
	CvInitCore* m_loadedInitCore;
	CvInitCore* m_initCore;
	CvMessageCodeTranslator* m_messageCodes;
	CvDropMgr* m_dropMgr;
	CvPortal* m_portal;
	CvStatsReporter* m_statsReporter;
	CvInterface* m_interface;

	CvArtFileMgr* m_pArtFileMgr;

	CvMap* m_map;

	CvDiplomacyScreen* m_diplomacyScreen;
	CMPDiplomacyScreen* m_mpDiplomacyScreen;

	FAStar* m_pathFinder;
	FAStar* m_interfacePathFinder;
	FAStar* m_stepFinder;
	FAStar* m_routeFinder;
	FAStar* m_borderFinder;
	FAStar* m_areaFinder;
	FAStar* m_plotGroupFinder;

	NiPoint3 m_pt3Origin;

	int* m_aiPlotDirectionX;	// [NUM_DIRECTION_TYPES];
	int* m_aiPlotDirectionY;	// [NUM_DIRECTION_TYPES];
	int* m_aiPlotCardinalDirectionX;	// [NUM_CARDINALDIRECTION_TYPES];
	int* m_aiPlotCardinalDirectionY;	// [NUM_CARDINALDIRECTION_TYPES];
	int* m_aiCityPlotX;	// [NUM_CITY_PLOTS];
	int* m_aiCityPlotY;	// [NUM_CITY_PLOTS];
	int* m_aiCityPlotPriority;	// [NUM_CITY_PLOTS];
	int m_aaiXYCityPlot[CITY_PLOTS_DIAMETER][CITY_PLOTS_DIAMETER];

	DirectionTypes* m_aeTurnLeftDirection;	// [NUM_DIRECTION_TYPES];
	DirectionTypes* m_aeTurnRightDirection;	// [NUM_DIRECTION_TYPES];
	DirectionTypes m_aaeXYDirection[DIRECTION_DIAMETER][DIRECTION_DIAMETER];

	//InterfaceModeInfo m_aInterfaceModeInfo[NUM_INTERFACEMODE_TYPES] =
	std::vector<CvInterfaceModeInfo*> m_paInterfaceModeInfo;

	/***********************************************************************************************************************
	Globals loaded from XML
	************************************************************************************************************************/

	// all type strings are upper case and are kept in this hash map for fast lookup, Moose
	typedef stdext::hash_map<std::string /* type string */, int /* info index */> InfosMap;
	InfosMap m_infosMap;
	std::vector<std::vector<CvInfoBase*>*> m_aInfoVectors;

	std::vector<CvColorInfo*> m_paColorInfo;
	std::vector<CvPlayerColorInfo*> m_paPlayerColorInfo;
	std::vector<CvAdvisorInfo*> m_paAdvisorInfo;
	std::vector<CvInfoBase*> m_paHints;
	std::vector<CvMainMenuInfo*> m_paMainMenus;
	std::vector<CvTerrainInfo*> m_paTerrainInfo;
	std::vector<CvLandscapeInfo*> m_paLandscapeInfo;
	int m_iActiveLandscapeID;
	std::vector<CvWorldInfo*> m_paWorldInfo;
	std::vector<CvClimateInfo*> m_paClimateInfo;
	std::vector<CvSeaLevelInfo*> m_paSeaLevelInfo;
	std::vector<CvYieldInfo*> m_paYieldInfo;
	std::vector<CvCommerceInfo*> m_paCommerceInfo;
	std::vector<CvRouteInfo*> m_paRouteInfo;
	std::vector<CvFeatureInfo*> m_paFeatureInfo;
	std::vector<CvBonusClassInfo*> m_paBonusClassInfo;
	std::vector<CvBonusInfo*> m_paBonusInfo;
	std::vector<CvImprovementInfo*> m_paImprovementInfo;
	std::vector<CvGoodyInfo*> m_paGoodyInfo;
	std::vector<CvBuildInfo*> m_paBuildInfo;
	std::vector<CvHandicapInfo*> m_paHandicapInfo;
	std::vector<CvGameSpeedInfo*> m_paGameSpeedInfo;
	std::vector<CvTurnTimerInfo*> m_paTurnTimerInfo;
	std::vector<CvCivilizationInfo*> m_paCivilizationInfo;
	int m_iNumPlayableCivilizationInfos;
	int m_iNumAIPlayableCivilizationInfos;
	std::vector<CvLeaderHeadInfo*> m_paLeaderHeadInfo;
	std::vector<CvTraitInfo*> m_paTraitInfo;
	std::vector<CvCursorInfo*> m_paCursorInfo;
	std::vector<CvThroneRoomCamera*> m_paThroneRoomCamera;
	std::vector<CvThroneRoomInfo*> m_paThroneRoomInfo;
	std::vector<CvThroneRoomStyleInfo*> m_paThroneRoomStyleInfo;
	std::vector<CvSlideShowInfo*> m_paSlideShowInfo;
	std::vector<CvSlideShowRandomInfo*> m_paSlideShowRandomInfo;
	std::vector<CvWorldPickerInfo*> m_paWorldPickerInfo;
	std::vector<CvSpaceShipInfo*> m_paSpaceShipInfo;
	std::vector<CvProcessInfo*> m_paProcessInfo;
	std::vector<CvVoteInfo*> m_paVoteInfo;
	std::vector<CvProjectInfo*> m_paProjectInfo;
	std::vector<CvBuildingClassInfo*> m_paBuildingClassInfo;
	std::vector<CvBuildingInfo*> m_paBuildingInfo;
	std::vector<CvSpecialBuildingInfo*> m_paSpecialBuildingInfo;
	std::vector<CvUnitClassInfo*> m_paUnitClassInfo;
	std::vector<CvUnitInfo*> m_paUnitInfo;
	std::vector<CvSpecialUnitInfo*> m_paSpecialUnitInfo;
	std::vector<CvInfoBase*> m_paConceptInfo;
	std::vector<CvInfoBase*> m_paNewConceptInfo;
	std::vector<CvInfoBase*> m_paCityTabInfo;
	std::vector<CvInfoBase*> m_paCalendarInfo;
	std::vector<CvInfoBase*> m_paSeasonInfo;
	std::vector<CvInfoBase*> m_paMonthInfo;
	std::vector<CvInfoBase*> m_paDenialInfo;
	std::vector<CvInfoBase*> m_paInvisibleInfo;
	std::vector<CvVoteSourceInfo*> m_paVoteSourceInfo;
	std::vector<CvInfoBase*> m_paUnitCombatInfo;
	std::vector<CvInfoBase*> m_paDomainInfo;
	std::vector<CvInfoBase*> m_paUnitAIInfos;
	std::vector<CvInfoBase*> m_paAttitudeInfos;
	std::vector<CvInfoBase*> m_paMemoryInfos;
	std::vector<CvInfoBase*> m_paFeatInfos;
	std::vector<CvGameOptionInfo*> m_paGameOptionInfos;
	std::vector<CvMPOptionInfo*> m_paMPOptionInfos;
	std::vector<CvForceControlInfo*> m_paForceControlInfos;
	std::vector<CvPlayerOptionInfo*> m_paPlayerOptionInfos;
	std::vector<CvGraphicOptionInfo*> m_paGraphicOptionInfos;
	std::vector<CvSpecialistInfo*> m_paSpecialistInfo;
	std::vector<CvEmphasizeInfo*> m_paEmphasizeInfo;
	std::vector<CvUpkeepInfo*> m_paUpkeepInfo;
	std::vector<CvCultureLevelInfo*> m_paCultureLevelInfo;
	std::vector<CvReligionInfo*> m_paReligionInfo;
	std::vector<CvCorporationInfo*> m_paCorporationInfo;
	std::vector<CvActionInfo*> m_paActionInfo;
	std::vector<CvMissionInfo*> m_paMissionInfo;
	std::vector<CvControlInfo*> m_paControlInfo;
	std::vector<CvCommandInfo*> m_paCommandInfo;
	std::vector<CvAutomateInfo*> m_paAutomateInfo;
	std::vector<CvPromotionInfo*> m_paPromotionInfo;
	std::vector<CvTechInfo*> m_paTechInfo;
	std::vector<CvCivicOptionInfo*> m_paCivicOptionInfo;
	std::vector<CvCivicInfo*> m_paCivicInfo;
	std::vector<CvDiplomacyInfo*> m_paDiplomacyInfo;
	std::vector<CvEraInfo*> m_aEraInfo;	// [NUM_ERA_TYPES];
	std::vector<CvHurryInfo*> m_paHurryInfo;
	std::vector<CvVictoryInfo*> m_paVictoryInfo;
	std::vector<CvRouteModelInfo*> m_paRouteModelInfo;
	std::vector<CvRiverInfo*> m_paRiverInfo;
	std::vector<CvRiverModelInfo*> m_paRiverModelInfo;
	std::vector<CvWaterPlaneInfo*> m_paWaterPlaneInfo;
	std::vector<CvTerrainPlaneInfo*> m_paTerrainPlaneInfo;
	std::vector<CvCameraOverlayInfo*> m_paCameraOverlayInfo;
	std::vector<CvAnimationPathInfo*> m_paAnimationPathInfo;
	std::vector<CvAnimationCategoryInfo*> m_paAnimationCategoryInfo;
	std::vector<CvEntityEventInfo*> m_paEntityEventInfo;
	std::vector<CvUnitFormationInfo*> m_paUnitFormationInfo;
	std::vector<CvEffectInfo*> m_paEffectInfo;
	std::vector<CvAttachableInfo*> m_paAttachableInfo;
	std::vector<CvCameraInfo*> m_paCameraInfo;
	std::vector<CvQuestInfo*> m_paQuestInfo;
	std::vector<CvTutorialInfo*> m_paTutorialInfo;
	std::vector<CvEventTriggerInfo*> m_paEventTriggerInfo;
	std::vector<CvEventInfo*> m_paEventInfo;
	std::vector<CvEspionageMissionInfo*> m_paEspionageMissionInfo;
	std::vector<CvUnitArtStyleTypeInfo*> m_paUnitArtStyleTypeInfo;

	// Game Text
	std::vector<CvGameText*> m_paGameTextXML;

	//////////////////////////////////////////////////////////////////////////
	// GLOBAL TYPES
	//////////////////////////////////////////////////////////////////////////

	// all type strings are upper case and are kept in this hash map for fast lookup, Moose
	typedef stdext::hash_map<std::string /* type string */, int /*enum value */> TypesMap;
	TypesMap m_typesMap;

	// XXX These are duplicates and are kept for enumeration convenience - most could be removed, Moose
	CvString* m_paszEntityEventTypes2;
	CvString* m_paszEntityEventTypes;
	int m_iNumEntityEventTypes;

	CvString* m_paszAnimationOperatorTypes;
	int m_iNumAnimationOperatorTypes;

	CvString* m_paszFunctionTypes;

	CvString* m_paszFlavorTypes;
	int m_iNumFlavorTypes;

	CvString* m_paszArtStyleTypes;
	int m_iNumArtStyleTypes;

	CvString* m_paszCitySizeTypes;
	int m_iNumCitySizeTypes;

	CvString* m_paszContactTypes;

	CvString* m_paszDiplomacyPowerTypes;

	CvString* m_paszAutomateTypes;

	CvString* m_paszDirectionTypes;

	CvString* m_paszFootstepAudioTypes;
	int m_iNumFootstepAudioTypes;

	CvString* m_paszFootstepAudioTags;
	int m_iNumFootstepAudioTags;

	CvString m_szCurrentXMLFile;
	//////////////////////////////////////////////////////////////////////////
	// Formerly Global Defines
	//////////////////////////////////////////////////////////////////////////

	FVariableSystem* m_VarSystem;

	int m_iEXTRA_YIELD; // K-Mod
	int m_iMOVE_DENOMINATOR;
	int m_iNUM_UNIT_PREREQ_OR_BONUSES;
	int m_iNUM_BUILDING_PREREQ_OR_BONUSES;
	int m_iFOOD_CONSUMPTION_PER_POPULATION;
	int m_iMAX_HIT_POINTS;
	int m_iPATH_DAMAGE_WEIGHT;
	int m_iHILLS_EXTRA_DEFENSE;
	int m_iRIVER_ATTACK_MODIFIER;
	int m_iAMPHIB_ATTACK_MODIFIER;
	int m_iHILLS_EXTRA_MOVEMENT;
	int m_iMAX_PLOT_LIST_ROWS;
	int m_iUNIT_MULTISELECT_MAX;
	int m_iPERCENT_ANGER_DIVISOR;
	int m_iEVENT_MESSAGE_TIME;
	int m_iROUTE_FEATURE_GROWTH_MODIFIER;
	int m_iFEATURE_GROWTH_MODIFIER;
	int m_iMIN_CITY_RANGE;
	int m_iCITY_MAX_NUM_BUILDINGS;
	int m_iNUM_UNIT_AND_TECH_PREREQS;
	int m_iNUM_AND_TECH_PREREQS;
	int m_iNUM_OR_TECH_PREREQS;
	int m_iLAKE_MAX_AREA_SIZE;
	int m_iNUM_ROUTE_PREREQ_OR_BONUSES;
	int m_iMIN_WATER_SIZE_FOR_OCEAN;
	int m_iFORTIFY_MODIFIER_PER_TURN;
	int m_iMAX_CITY_DEFENSE_DAMAGE;
	int m_iNUM_CORPORATION_PREREQ_BONUSES;
	int m_iPEAK_SEE_THROUGH_CHANGE;
	int m_iHILLS_SEE_THROUGH_CHANGE;
	int m_iSEAWATER_SEE_FROM_CHANGE;
	int m_iPEAK_SEE_FROM_CHANGE;
	int m_iHILLS_SEE_FROM_CHANGE;
	int m_iUSE_SPIES_NO_ENTER_BORDERS;

	float m_fCAMERA_MIN_YAW;
	float m_fCAMERA_MAX_YAW;
	float m_fCAMERA_FAR_CLIP_Z_HEIGHT;
	float m_fCAMERA_MAX_TRAVEL_DISTANCE;
	float m_fCAMERA_START_DISTANCE;
	float m_fAIR_BOMB_HEIGHT;
	float m_fPLOT_SIZE;
	float m_fCAMERA_SPECIAL_PITCH;
	float m_fCAMERA_MAX_TURN_OFFSET;
	float m_fCAMERA_MIN_DISTANCE;
	float m_fCAMERA_UPPER_PITCH;
	float m_fCAMERA_LOWER_PITCH;
	float m_fFIELD_OF_VIEW;
	float m_fSHADOW_SCALE;
	float m_fUNIT_MULTISELECT_DISTANCE;

	int m_iACO_SHOW_MODIFIER_LABELS;
	int m_iACO_SHOW_SURVIVAL_ODDS;
	int m_iACO_SHOW_ATTACKER_HEALTH_BARS;
	int m_iACO_SHOW_DEFENDER_HEALTH_BARS;
	int m_iACO_SHOW_BASIC_INFO;
	int m_iACO_SHOW_EXPERIENCE_RANGE;
	int m_iACO_SHOW_AVERAGE_HEALTH;
	int m_iACO_SHOW_UNHARMED_ODDS;
	int m_iACO_SHOW_UNROUNDED_EXPERIENCE;
	int m_iACO_SHOW_SHIFT_INSTRUCTIONS;
	int m_iACO_SHOW_ATTACKER_INFO;
	int m_iACO_SHOW_DEFENDER_INFO;
	int m_iACO_SHOW_DEFENCE_MODIFIERS;
	int m_iACO_SHOW_TOTAL_DEFENCE_MODIFIER;
	int m_iBBAI_DEFENSIVE_PACT_BEHAVIOR;
	int m_iBBAI_ATTACK_CITY_STACK_RATIO;
	int m_iBBAI_SKIP_BOMBARD_BEST_ATTACK_ODDS;
	int m_iBBAI_SKIP_BOMBARD_BASE_STACK_RATIO;
	int m_iBBAI_SKIP_BOMBARD_MIN_STACK_RATIO;
	int m_iCOMBAT_DIE_SIDES;
	int m_iCOMBAT_DAMAGE;
	int m_iLFBBasedOnGeneral;
	int m_iLFBBasedOnExperience;
	int m_iLFBBasedOnLimited;
	int m_iLFBBasedOnHealer;
	int m_iLFBDefensiveAdjustment;
	int	m_iLFBAdjustNumerator;
	int	m_iLFBAdjustDenominator;
	int m_iTECH_DIFFUSION_KNOWN_TEAM_MODIFIER;
	int m_iTECH_DIFFUSION_WELFARE_THRESHOLD;
	int m_iTECH_DIFFUSION_WELFARE_MODIFIER;
	int m_iTECH_COST_FIRST_KNOWN_PREREQ_MODIFIER;
	int m_iTECH_COST_KNOWN_PREREQ_MODIFIER;
	int m_iTECH_COST_MODIFIER;
	int m_iUSE_CANNOT_FOUND_CITY_CALLBACK;
	int m_iUSE_CAN_FOUND_CITIES_ON_WATER_CALLBACK;
	int m_iUSE_IS_PLAYER_RESEARCH_CALLBACK;
	int m_iUSE_CAN_RESEARCH_CALLBACK;
	int m_iUSE_CANNOT_DO_CIVIC_CALLBACK;
	int m_iUSE_CAN_DO_CIVIC_CALLBACK;
	int m_iUSE_CANNOT_CONSTRUCT_CALLBACK;
	int m_iUSE_CAN_CONSTRUCT_CALLBACK;
	int m_iUSE_CAN_DECLARE_WAR_CALLBACK;
	int m_iUSE_CANNOT_RESEARCH_CALLBACK;
	int m_iUSE_GET_UNIT_COST_MOD_CALLBACK;
	int m_iUSE_GET_BUILDING_COST_MOD_CALLBACK;
	int m_iUSE_GET_CITY_FOUND_VALUE_CALLBACK;
	int m_iUSE_CANNOT_HANDLE_ACTION_CALLBACK;
	int m_iUSE_CAN_BUILD_CALLBACK;
	int m_iUSE_CANNOT_TRAIN_CALLBACK;
	int m_iUSE_CAN_TRAIN_CALLBACK;
	int m_iUSE_UNIT_CANNOT_MOVE_INTO_CALLBACK;
	int m_iUSE_USE_CANNOT_SPREAD_RELIGION_CALLBACK;
	int m_iUSE_FINISH_TEXT_CALLBACK;
	int m_iUSE_ON_UNIT_SET_XY_CALLBACK;
	int m_iUSE_ON_UNIT_SELECTED_CALLBACK;
	int m_iUSE_ON_UPDATE_CALLBACK;
	int m_iUSE_ON_UNIT_CREATED_CALLBACK;
	int m_iUSE_ON_UNIT_LOST_CALLBACK;
	int m_iWAR_SUCCESS_CITY_CAPTURING;
	int m_iREHOME_PERCENT_CHANCE;

	bool m_bACO_ENABLED;
	bool m_bACO_DEBUG;
	bool m_bACO_FORCE_ORIGINAL_ODDS;
	bool m_bACO_IGNORE_BARB_FREE_WINS;
	bool m_bACO_SWAP_VIEWS;
	bool m_bACO_MERGE_SHORT_BARS;
	bool m_bBBAI_AIR_COMBAT;
	bool m_bBBAI_HUMAN_VASSAL_WAR_BUILD;
	bool m_bBBAI_HUMAN_AS_VASSAL_OPTION;
	bool m_bBUG_CITY_SCREEN_BASE_COMMERCE_HOVER;
	bool m_bBUG_MISC_SPECIALIST_HOVER_ACTUAL_EFFECTS;
	bool m_bBUG_BUILDING_HOVER_ACTUAL_EFFECTS;
	bool m_bBUG_BUILDING_SAVED_MAINTENANCE_HOVER;
	bool m_bBUG_BUILDING_ADDITIONAL_HEALTH_HOVER;
	bool m_bBUG_BUILDING_ADDITIONAL_HAPPINESS_HOVER;
	bool m_bBUG_BUILDING_ADDITIONAL_PRODUCTION_HOVER;
	bool m_bBUG_BUILDING_ADDITIONAL_COMMERCE_HOVER;
	bool m_bBUG_BUILDING_ADDITIONAL_GREAT_PEOPLE_HOVER;
	bool m_bLFBEnable;
	bool m_bLFBUseCombatOdds;
	bool m_bLFBUseSlidingScale;
	bool m_bRAPID_UNIT_CYCLING;
	bool m_bSIMPLE_SELECTION_MODE;
	bool m_bTECH_DIFFUSION_ENABLE;
	bool m_bUSE_AI_UNIT_UPDATE_CALLBACK;
	bool m_bUSE_AI_DO_DIPLO_CALLBACK;
	bool m_bUSE_AI_CHOOSE_PRODUCTION_CALLBACK;
	bool m_bUSE_AI_DO_WAR_CALLBACK;
	bool m_bUSE_AI_CHOOSE_TECH_CALLBACK;
	bool m_bUSE_DO_COMBAT_CALLBACK;
	bool m_bUSE_DO_CULTURE_CALLBACK;
	bool m_bUSE_DO_GREAT_PEOPLE_CALLBACK;
	bool m_bUSE_DO_GROWTH_CALLBACK;
	bool m_bUSE_DO_MELTDOWN_CALLBACK;
	bool m_bUSE_DO_PILLAGE_GOLD_CALLBACK;
	bool m_bUSE_DO_PLOT_CULTURE_CALLBACK;
	bool m_bUSE_DO_PRODUCTION_CALLBACK;
	bool m_bUSE_DO_RELIGION_CALLBACK;
	bool m_bUSE_GET_EXPERIENCE_NEEDED_CALLBACK;
	bool m_bUSE_UNIT_UPGRADE_PRICE_CALLBACK;

	// DLL interface
	CvDLLUtilityIFaceBase* m_pDLL;

	FProfiler* m_Profiler;		// profiler
	CvString m_szDllProfileText;

};

extern CvGlobals gGlobals;	// for debugging

//
// inlines
//
inline CvGlobals& CvGlobals::getInstance() {
	return gGlobals;
}


//
// helpers
//
#define GC CvGlobals::getInstance()
#ifndef _USRDLL
#define gDLL GC.getDLLIFaceNonInl()
#else
#define gDLL GC.getDLLIFace()
#endif

#ifndef _USRDLL
#define NUM_DIRECTION_TYPES (GC.getNumDirections())
#define NUM_GAMEOPTION_TYPES (GC.getNumGameOptions())
#define NUM_MPOPTION_TYPES (GC.getNumMPOptions())
#define NUM_SPECIALOPTION_TYPES (GC.getNumSpecialOptions())
#define NUM_GRAPHICOPTION_TYPES (GC.getNumGraphicOptions())
#define NUM_TRADEABLE_ITEMS (GC.getNumTradeableItems())
#define NUM_BASIC_ITEMS (GC.getNumBasicItems())
#define NUM_TRADEABLE_HEADINGS (GC.getNumTradeableHeadings())
#define NUM_COMMAND_TYPES (GC.getNumCommandInfos())
#define NUM_CONTROL_TYPES (GC.getNumControlInfos())
#define NUM_MISSION_TYPES (GC.getNumMissionInfos())
#define NUM_PLAYEROPTION_TYPES (GC.getNumPlayerOptionInfos())
#define MAX_NUM_SYMBOLS (GC.getMaxNumSymbols())
#define NUM_GRAPHICLEVELS (GC.getNumGraphicLevels())
#define NUM_GLOBE_LAYER_TYPES (GC.getNumGlobeLayers())
#endif

#endif
