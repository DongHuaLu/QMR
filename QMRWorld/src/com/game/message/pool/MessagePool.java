package com.game.message.pool;
	
import java.util.HashMap;import com.game.server.message.ReqRegisterWorldMessage;
import com.game.server.handler.ReqRegisterWorldHandler;
import com.game.server.message.ReqRegisterWorldForGateMessage;
import com.game.server.handler.ReqRegisterWorldForGateHandler;
import com.game.login.message.ResLoginSuccessToWorldMessage;
import com.game.login.handler.ResLoginSuccessToWorldHandler;
import com.game.team.message.ReqCreateateamWorldMessage;
import com.game.team.handler.ReqCreateateamWorldHandler;
import com.game.team.message.ReqApplyWorldMessage;
import com.game.team.handler.ReqApplyWorldHandler;
import com.game.team.message.ReqApplyWorldSelectMessage;
import com.game.team.handler.ReqApplyWorldSelectHandler;
import com.game.team.message.ReqInviteWorldMessage;
import com.game.team.handler.ReqInviteWorldHandler;
import com.game.team.message.ReqInviteWorldSelectMessage;
import com.game.team.handler.ReqInviteWorldSelectHandler;
import com.game.team.message.ReqToleaveWorldMessage;
import com.game.team.handler.ReqToleaveWorldHandler;
import com.game.team.message.ReqAppointWorldMessage;
import com.game.team.handler.ReqAppointWorldHandler;
import com.game.team.message.ReqAppointWorldSelectMessage;
import com.game.team.handler.ReqAppointWorldSelectHandler;
import com.game.team.message.ReqUpdateTeaminfoWorldMessage;
import com.game.team.handler.ReqUpdateTeaminfoWorldHandler;
import com.game.team.message.ReqAutoTeaminvitedWorldMessage;
import com.game.team.handler.ReqAutoTeaminvitedWorldHandler;
import com.game.team.message.ReqAutoIntoTeamApplyWorldMessage;
import com.game.team.handler.ReqAutoIntoTeamApplyWorldHandler;
import com.game.friend.message.ReqRelationGetListToWorldMessage;
import com.game.friend.handler.ReqRelationGetListToWorldHandler;
import com.game.friend.message.ReqRelationAddToWorldMessage;
import com.game.friend.handler.ReqRelationAddToWorldHandler;
import com.game.friend.message.ReqRelationDeleteToWorldMessage;
import com.game.friend.handler.ReqRelationDeleteToWorldHandler;
import com.game.friend.message.ReqRelationSortToWorldMessage;
import com.game.friend.handler.ReqRelationSortToWorldHandler;
import com.game.friend.message.ReqRelationInnerAddToWorldMessage;
import com.game.friend.handler.ReqRelationInnerAddToWorldHandler;
import com.game.friend.message.ReqRelationMoodToWorldMessage;
import com.game.friend.handler.ReqRelationMoodToWorldHandler;
import com.game.friend.message.ReqRelationInnerNotifyToWorldMessage;
import com.game.friend.handler.ReqRelationInnerNotifyToWorldHandler;
import com.game.guild.message.ReqInnerGuildCreateToWorldMessage;
import com.game.guild.handler.ReqInnerGuildCreateToWorldHandler;
import com.game.guild.message.ReqInnerGuildAutoArgeeAddGuildToWorldMessage;
import com.game.guild.handler.ReqInnerGuildAutoArgeeAddGuildToWorldHandler;
import com.game.guild.message.ReqInnerGuildGetGuildListToWorldMessage;
import com.game.guild.handler.ReqInnerGuildGetGuildListToWorldHandler;
import com.game.guild.message.ReqInnerGuildApplyAddToWorldMessage;
import com.game.guild.handler.ReqInnerGuildApplyAddToWorldHandler;
import com.game.guild.message.ReqInnerGuildInviteAddToWorldMessage;
import com.game.guild.handler.ReqInnerGuildInviteAddToWorldHandler;
import com.game.guild.message.ReqInnerGuildGetMemberListToWorldMessage;
import com.game.guild.handler.ReqInnerGuildGetMemberListToWorldHandler;
import com.game.guild.message.ReqInnerGuildAddMemberToWorldMessage;
import com.game.guild.handler.ReqInnerGuildAddMemberToWorldHandler;
import com.game.guild.message.ReqInnerGuildQuitToWorldMessage;
import com.game.guild.handler.ReqInnerGuildQuitToWorldHandler;
import com.game.guild.message.ReqInnerGuildChangeNickNameToWorldMessage;
import com.game.guild.handler.ReqInnerGuildChangeNickNameToWorldHandler;
import com.game.guild.message.ReqInnerGuildChangePowerLevelToWorldMessage;
import com.game.guild.handler.ReqInnerGuildChangePowerLevelToWorldHandler;
import com.game.guild.message.ReqInnerGuildDeleteMemberToWorldMessage;
import com.game.guild.handler.ReqInnerGuildDeleteMemberToWorldHandler;
import com.game.guild.message.ReqInnerGuildAutoGuildArgeeAddToWorldMessage;
import com.game.guild.handler.ReqInnerGuildAutoGuildArgeeAddToWorldHandler;
import com.game.guild.message.ReqInnerGuildChangeBulletinToWorldMessage;
import com.game.guild.handler.ReqInnerGuildChangeBulletinToWorldHandler;
import com.game.guild.message.ReqInnerGuildSubmitItemToWorldMessage;
import com.game.guild.handler.ReqInnerGuildSubmitItemToWorldHandler;
import com.game.guild.message.ReqInnerGuildChangeBannerIconToWorldMessage;
import com.game.guild.handler.ReqInnerGuildChangeBannerIconToWorldHandler;
import com.game.guild.message.ReqInnerGuildChangeBannerNameToWorldMessage;
import com.game.guild.handler.ReqInnerGuildChangeBannerNameToWorldHandler;
import com.game.guild.message.ReqInnerGuildBannerLevelUpToWorldMessage;
import com.game.guild.handler.ReqInnerGuildBannerLevelUpToWorldHandler;
import com.game.guild.message.ReqInnerGuildAddDiplomaticToWorldMessage;
import com.game.guild.handler.ReqInnerGuildAddDiplomaticToWorldHandler;
import com.game.guild.message.ReqInnerGuildDeleteDiplomaticToWorldMessage;
import com.game.guild.handler.ReqInnerGuildDeleteDiplomaticToWorldHandler;
import com.game.guild.message.ReqInnerGuildDeleteGuildToWorldMessage;
import com.game.guild.handler.ReqInnerGuildDeleteGuildToWorldHandler;
import com.game.guild.message.ReqInnerGuildGetEventListToWorldMessage;
import com.game.guild.handler.ReqInnerGuildGetEventListToWorldHandler;
import com.game.login.message.ResRemoveCharacterToWorldMessage;
import com.game.login.handler.ResRemoveCharacterToWorldHandler;
import com.game.team.message.ReqMapSearchTeamInfoWorldMessage;
import com.game.team.handler.ReqMapSearchTeamInfoWorldHandler;
import com.game.team.message.ReqMapSearchPlayerInfoWorldMessage;
import com.game.team.handler.ReqMapSearchPlayerInfoWorldHandler;
import com.game.monster.message.ReqMonsterSyncMessage;
import com.game.monster.handler.ReqMonsterSyncHandler;
import com.game.player.message.ReqSyncPlayerGoldExpendMessage;
import com.game.player.message.ReqSyncPlayerInfoMessage;
import com.game.player.handler.ReqSyncPlayerGoldExpendHandler;
import com.game.player.handler.ReqSyncPlayerInfoHandler;
import com.game.player.message.ReqSyncPlayerHpMessage;
import com.game.player.handler.ReqSyncPlayerHpHandler;
import com.game.player.message.ReqSyncPlayerPositionMessage;
import com.game.player.handler.ReqSyncPlayerPositionHandler;
import com.game.player.message.ReqSyncPlayerMapMessage;
import com.game.player.handler.ReqSyncPlayerMapHandler;
import com.game.player.message.ReqSyncPlayerLevelMessage;
import com.game.player.handler.ReqSyncPlayerLevelHandler;
import com.game.player.message.ReqSyncPlayerAppearanceInfoMessage;
import com.game.player.handler.ReqSyncPlayerAppearanceInfoHandler;
import com.game.stalls.message.ReqStallsOpenUpToWorldMessage;
import com.game.stalls.handler.ReqStallsOpenUpToWorldHandler;
import com.game.stalls.message.ReqStallsPlayerIdLookToWorldMessage;
import com.game.stalls.handler.ReqStallsPlayerIdLookToWorldHandler;
import com.game.stalls.message.ReqStallsSortToWorldMessage;
import com.game.stalls.handler.ReqStallsSortToWorldHandler;
import com.game.stalls.message.ReqStallsBuycheckToWorldMessage;
import com.game.stalls.handler.ReqStallsBuycheckToWorldHandler;
import com.game.stalls.message.ReqStallsBuyToWorldMessage;
import com.game.stalls.handler.ReqStallsBuyToWorldHandler;
import com.game.stalls.message.ReqStallsProductWasAddedToWorldMessage;
import com.game.stalls.handler.ReqStallsProductWasAddedToWorldHandler;
import com.game.stalls.message.ReqStallsAdjustPricesToWorldMessage;
import com.game.stalls.handler.ReqStallsAdjustPricesToWorldHandler;
import com.game.stalls.message.ReqStallsOffShelfToWorldMessage;
import com.game.stalls.handler.ReqStallsOffShelfToWorldHandler;
import com.game.stalls.message.ReqStallsSearchToWorldMessage;
import com.game.stalls.handler.ReqStallsSearchToWorldHandler;
import com.game.stalls.message.ReqChangeStallsNameToWorldMessage;
import com.game.stalls.handler.ReqChangeStallsNameToWorldHandler;
import com.game.chat.message.ChatResponseSWMessage;
import com.game.chat.handler.ChatResponseSWHandler;
import com.game.player.message.ReqOtherPlayerInfoToWorldMessage;
import com.game.player.handler.ReqOtherPlayerInfoToWorldHandler;
import com.game.player.message.ReqNonageTimeToWorldMessage;
import com.game.player.handler.ReqNonageTimeToWorldHandler;
import com.game.gm.message.GmCommandToWorldMessage;
import com.game.gm.handler.GmCommandToWorldHandler;
import com.game.player.message.ReqNonageRegisterToWorldMessage;
import com.game.player.handler.ReqNonageRegisterToWorldHandler;
import com.game.team.message.ReqMapSearchMemberNameWorldMessage;
import com.game.team.handler.ReqMapSearchMemberNameWorldHandler;
import com.game.team.message.ReqTeamMessageWorldMessage;
import com.game.team.handler.ReqTeamMessageWorldHandler;
import com.game.team.message.ReqGenericSearchToWorldMessage;
import com.game.team.handler.ReqGenericSearchToWorldHandler;
import com.game.setupmenu.message.ResSetMenuStatusToWorldMessage;
import com.game.setupmenu.handler.ResSetMenuStatusToWorldHandler;
import com.game.friend.message.ReqRelationConfigToWorldMessage;
import com.game.friend.handler.ReqRelationConfigToWorldHandler;
import com.game.team.message.ReqIntoTeamToWorldMessage;
import com.game.team.handler.ReqIntoTeamToWorldHandler;
import com.game.player.message.ReqPlayerCheckOnlineToWorldMessage;
import com.game.player.handler.ReqPlayerCheckOnlineToWorldHandler;
import com.game.player.message.ReqGetPlayerAppearanceInfoToWorldMessage;
import com.game.player.handler.ReqGetPlayerAppearanceInfoToWorldHandler;
import com.game.player.message.ReqDelPlayerToWorldMessage;
import com.game.player.handler.ReqDelPlayerToWorldHandler;
import com.game.task.message.ReqTargetMonsterMessage;
import com.game.task.handler.ReqTargetMonsterHandler;
import com.game.player.message.ReqChangePlayerNameToWorldMessage;
import com.game.player.handler.ReqChangePlayerNameToWorldHandler;
import com.game.server.message.ResCloseSucessForGameMessage;
import com.game.server.handler.ResCloseSucessForGameHandler;
import com.game.server.message.ResCloseSucessForGateMessage;
import com.game.server.handler.ResCloseSucessForGateHandler;
import com.game.backend.message.ResPlayerMoneyGoldToWorldMessage;
import com.game.backend.handler.ResPlayerMoneyGoldToWorldHandler;
import com.game.backend.message.ResPlayerInfoToWorldMessage;
import com.game.backend.handler.ResPlayerInfoToWorldHandler;
import com.game.guild.message.ReqInnerGuildNotifyToWorldMessage;
import com.game.guild.handler.ReqInnerGuildNotifyToWorldHandler;
import com.game.spirittree.message.ReqGetAllFruitInfoToWorldMessage;
import com.game.spirittree.handler.ReqGetAllFruitInfoToWorldHandler;
import com.game.spirittree.message.ReqGetSingleFruitInfoToWorldMessage;
import com.game.spirittree.handler.ReqGetSingleFruitInfoToWorldHandler;
import com.game.spirittree.message.ReqRipeningFruitToWorldMessage;
import com.game.spirittree.handler.ReqRipeningFruitToWorldHandler;
import com.game.spirittree.message.ReqOpenGuildFruitToWorldMessage;
import com.game.spirittree.handler.ReqOpenGuildFruitToWorldHandler;
import com.game.spirittree.message.ReqGuildFruitOperatingToWorldMessage;
import com.game.spirittree.handler.ReqGuildFruitOperatingToWorldHandler;
import com.game.version.message.ReqVersionUpdateToWorldMessage;
import com.game.version.handler.ReqVersionUpdateToWorldHandler;
import com.game.card.message.ReqInnerCardToWorldMessage;
import com.game.card.handler.ReqInnerCardToWorldHandler;
import com.game.spirittree.message.ResRipeningDecYBToWorldMessage;
import com.game.spirittree.handler.ResRipeningDecYBToWorldHandler;
import com.game.ybcard.message.ReqYBCardToWorldMessage;
import com.game.ybcard.handler.ReqYBCardToWorldHandler;
import com.game.spirittree.message.ReqGuildFruitLogToWorldMessage;
import com.game.spirittree.handler.ReqGuildFruitLogToWorldHandler;
import com.game.spirittree.message.ReqGuildGMToWorldMessage;
import com.game.spirittree.handler.ReqGuildGMToWorldHandler;
import com.game.monster.message.ReqMonsterDoubleNoticeMessage;
import com.game.monster.handler.ReqMonsterDoubleNoticeHandler;
import com.game.toplist.message.ReqGetTopListToWorldMessage;
import com.game.toplist.handler.ReqGetTopListToWorldHandler;
import com.game.toplist.message.ReqWorShipToWorldMessage;
import com.game.toplist.handler.ReqWorShipToWorldHandler;
import com.game.player.message.ReqSyncPlayerEventcutMessage;
import com.game.player.handler.ReqSyncPlayerEventcutHandler;
import com.game.player.message.ReqSyncPlayerSkillMessage;
import com.game.player.handler.ReqSyncPlayerSkillHandler;
import com.game.player.message.ReqSyncPlayerHorseMessage;
import com.game.player.handler.ReqSyncPlayerHorseHandler;
import com.game.player.message.ReqSyncPlayerLongyuanMessage;
import com.game.player.handler.ReqSyncPlayerLongyuanHandler;
import com.game.player.message.ReqSyncPlayerOrderInfoMessage;
import com.game.player.handler.ReqSyncPlayerOrderInfoHandler;
import com.game.player.message.ReqSyncPlayerEquipMessage;
import com.game.player.handler.ReqSyncPlayerEquipHandler;
import com.game.player.message.ReqSyncPlayerGemMessage;
import com.game.player.handler.ReqSyncPlayerGemHandler;
import com.game.player.message.ReqSyncPlayerAttributeMessage;
import com.game.player.handler.ReqSyncPlayerAttributeHandler;
import com.game.player.message.ReqScriptCommonServerToWorldMessage;
import com.game.player.handler.ReqScriptCommonServerToWorldHandler;
import com.game.toplist.message.ReqZoneTopToWorldMessage;
import com.game.toplist.handler.ReqZoneTopToWorldHandler;
import com.game.mail.message.ReqMailSendSystemMailToWorldMessage;
import com.game.mail.handler.ReqMailSendSystemMailToWorldHandler;
import com.game.country.message.ReqCountryStructureInfoToWoridMessage;
import com.game.country.handler.ReqCountryStructureInfoToWoridHandler;
import com.game.guild.message.ReqInnerKingCityEventToWorldMessage;
import com.game.guild.handler.ReqInnerKingCityEventToWorldHandler;
import com.game.country.message.ReqCountrySyncKingCityToWoridMessage;
import com.game.country.handler.ReqCountrySyncKingCityToWoridHandler;
import com.game.activities.message.ReqActivitiesInfoToWorldMessage;
import com.game.activities.handler.ReqActivitiesInfoToWorldHandler;
import com.game.activities.message.ReqGetRewardToWorldMessage;
import com.game.activities.handler.ReqGetRewardToWorldHandler;
import com.game.card.message.ReqInnerCardPhoneToWorldMessage;
import com.game.card.handler.ReqInnerCardPhoneToWorldHandler;
import com.game.scripts.message.ReqScriptToWorldMessage;
import com.game.scripts.handler.ReqScriptToWorldHandler;
import com.game.player.message.ReqSyncPlayerPetMessage;
import com.game.player.handler.ReqSyncPlayerPetHandler;
import com.game.player.message.ReqSyncPlayerPetInfoMessage;
import com.game.player.handler.ReqSyncPlayerPetInfoHandler;
import com.game.guild.message.ReqGuildStrMessageToWorldMessage;
import com.game.guild.handler.ReqGuildStrMessageToWorldHandler;
import com.game.player.message.ReqSyncPlayerRankMessage;
import com.game.player.handler.ReqSyncPlayerRankHandler;
import com.game.player.message.ReqSyncPlayerArrowMessage;
import com.game.player.handler.ReqSyncPlayerArrowHandler;
import com.game.spirittree.message.ReqActivityCheckFruitToWorldMessage;
import com.game.spirittree.handler.ReqActivityCheckFruitToWorldHandler;
import com.game.spirittree.message.ReqUrgeRipeToWorldMessage;
import com.game.spirittree.handler.ReqUrgeRipeToWorldHandler;
import com.game.spirittree.message.ReqContinuousRipeningToWorldMessage;
import com.game.spirittree.handler.ReqContinuousRipeningToWorldHandler;
import com.game.player.message.ReqSyncPlayerHorseWeaponMessage;
import com.game.player.handler.ReqSyncPlayerHorseWeaponHandler;
import com.game.player.message.ReqSyncPlayerHiddenWeaponMessage;
import com.game.player.handler.ReqSyncPlayerHiddenWeaponHandler;
import com.game.player.message.ReqSyncPlayerRealMMessage;
import com.game.player.handler.ReqSyncPlayerRealMHandler;
import com.game.activities.message.ReqGetTailiToWorldMessage;
import com.game.activities.handler.ReqGetTailiToWorldHandler;
import com.game.marriage.message.ReqUpdatedMarriageToWorldMessage;
import com.game.marriage.handler.ReqUpdatedMarriageToWorldHandler;
import com.game.marriage.message.ReqDeleteMarriageToWorldMessage;
import com.game.marriage.handler.ReqDeleteMarriageToWorldHandler;
import com.game.command.Handler;
import com.game.message.Message;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 引用类列表
 */
public class MessagePool {
	//消息类字典
	HashMap<Integer, Class<?>> messages = new HashMap<Integer, Class<?>>();
	//处理类字典
	HashMap<Integer, Class<?>> handlers = new HashMap<Integer, Class<?>>();
	
	public MessagePool(){
		register(300303, ReqRegisterWorldMessage.class, ReqRegisterWorldHandler.class);
		register(300305, ReqRegisterWorldForGateMessage.class, ReqRegisterWorldForGateHandler.class);
		register(100303, ResLoginSuccessToWorldMessage.class, ResLoginSuccessToWorldHandler.class);
		register(118301, ReqCreateateamWorldMessage.class, ReqCreateateamWorldHandler.class);
		register(118303, ReqApplyWorldMessage.class, ReqApplyWorldHandler.class);
		register(118304, ReqApplyWorldSelectMessage.class, ReqApplyWorldSelectHandler.class);
		register(118305, ReqInviteWorldMessage.class, ReqInviteWorldHandler.class);
		register(118306, ReqInviteWorldSelectMessage.class, ReqInviteWorldSelectHandler.class);
		register(118307, ReqToleaveWorldMessage.class, ReqToleaveWorldHandler.class);
		register(118308, ReqAppointWorldMessage.class, ReqAppointWorldHandler.class);
		register(118309, ReqAppointWorldSelectMessage.class, ReqAppointWorldSelectHandler.class);
		register(118311, ReqUpdateTeaminfoWorldMessage.class, ReqUpdateTeaminfoWorldHandler.class);
		register(118312, ReqAutoTeaminvitedWorldMessage.class, ReqAutoTeaminvitedWorldHandler.class);
		register(118313, ReqAutoIntoTeamApplyWorldMessage.class, ReqAutoIntoTeamApplyWorldHandler.class);
		register(119301, ReqRelationGetListToWorldMessage.class, ReqRelationGetListToWorldHandler.class);
		register(119302, ReqRelationAddToWorldMessage.class, ReqRelationAddToWorldHandler.class);
		register(119303, ReqRelationDeleteToWorldMessage.class, ReqRelationDeleteToWorldHandler.class);
		register(119304, ReqRelationSortToWorldMessage.class, ReqRelationSortToWorldHandler.class);
		register(119305, ReqRelationInnerAddToWorldMessage.class, ReqRelationInnerAddToWorldHandler.class);
		register(119307, ReqRelationMoodToWorldMessage.class, ReqRelationMoodToWorldHandler.class);
		register(119306, ReqRelationInnerNotifyToWorldMessage.class, ReqRelationInnerNotifyToWorldHandler.class);
		register(121301, ReqInnerGuildCreateToWorldMessage.class, ReqInnerGuildCreateToWorldHandler.class);
		register(121302, ReqInnerGuildAutoArgeeAddGuildToWorldMessage.class, ReqInnerGuildAutoArgeeAddGuildToWorldHandler.class);
		register(121303, ReqInnerGuildGetGuildListToWorldMessage.class, ReqInnerGuildGetGuildListToWorldHandler.class);
		register(121304, ReqInnerGuildApplyAddToWorldMessage.class, ReqInnerGuildApplyAddToWorldHandler.class);
		register(121305, ReqInnerGuildInviteAddToWorldMessage.class, ReqInnerGuildInviteAddToWorldHandler.class);
		register(121306, ReqInnerGuildGetMemberListToWorldMessage.class, ReqInnerGuildGetMemberListToWorldHandler.class);
		register(121307, ReqInnerGuildAddMemberToWorldMessage.class, ReqInnerGuildAddMemberToWorldHandler.class);
		register(121308, ReqInnerGuildQuitToWorldMessage.class, ReqInnerGuildQuitToWorldHandler.class);
		register(121309, ReqInnerGuildChangeNickNameToWorldMessage.class, ReqInnerGuildChangeNickNameToWorldHandler.class);
		register(121310, ReqInnerGuildChangePowerLevelToWorldMessage.class, ReqInnerGuildChangePowerLevelToWorldHandler.class);
		register(121311, ReqInnerGuildDeleteMemberToWorldMessage.class, ReqInnerGuildDeleteMemberToWorldHandler.class);
		register(121312, ReqInnerGuildAutoGuildArgeeAddToWorldMessage.class, ReqInnerGuildAutoGuildArgeeAddToWorldHandler.class);
		register(121313, ReqInnerGuildChangeBulletinToWorldMessage.class, ReqInnerGuildChangeBulletinToWorldHandler.class);
		register(121314, ReqInnerGuildSubmitItemToWorldMessage.class, ReqInnerGuildSubmitItemToWorldHandler.class);
		register(121315, ReqInnerGuildChangeBannerIconToWorldMessage.class, ReqInnerGuildChangeBannerIconToWorldHandler.class);
		register(121316, ReqInnerGuildChangeBannerNameToWorldMessage.class, ReqInnerGuildChangeBannerNameToWorldHandler.class);
		register(121317, ReqInnerGuildBannerLevelUpToWorldMessage.class, ReqInnerGuildBannerLevelUpToWorldHandler.class);
		register(121318, ReqInnerGuildAddDiplomaticToWorldMessage.class, ReqInnerGuildAddDiplomaticToWorldHandler.class);
		register(121319, ReqInnerGuildDeleteDiplomaticToWorldMessage.class, ReqInnerGuildDeleteDiplomaticToWorldHandler.class);
		register(121320, ReqInnerGuildDeleteGuildToWorldMessage.class, ReqInnerGuildDeleteGuildToWorldHandler.class);
		register(121321, ReqInnerGuildGetEventListToWorldMessage.class, ReqInnerGuildGetEventListToWorldHandler.class);
		register(100307, ResRemoveCharacterToWorldMessage.class, ResRemoveCharacterToWorldHandler.class);
		register(118315, ReqMapSearchTeamInfoWorldMessage.class, ReqMapSearchTeamInfoWorldHandler.class);
		register(118316, ReqMapSearchPlayerInfoWorldMessage.class, ReqMapSearchPlayerInfoWorldHandler.class);
		register(114301, ReqMonsterSyncMessage.class, ReqMonsterSyncHandler.class);
		register(103301, ReqSyncPlayerInfoMessage.class, ReqSyncPlayerInfoHandler.class);
		register(103302, ReqSyncPlayerHpMessage.class, ReqSyncPlayerHpHandler.class);
		register(103303, ReqSyncPlayerPositionMessage.class, ReqSyncPlayerPositionHandler.class);
		register(103304, ReqSyncPlayerMapMessage.class, ReqSyncPlayerMapHandler.class);
		register(103305, ReqSyncPlayerLevelMessage.class, ReqSyncPlayerLevelHandler.class);
		register(103306, ReqSyncPlayerAppearanceInfoMessage.class, ReqSyncPlayerAppearanceInfoHandler.class);
		register(123301, ReqStallsOpenUpToWorldMessage.class, ReqStallsOpenUpToWorldHandler.class);
		register(123302, ReqStallsPlayerIdLookToWorldMessage.class, ReqStallsPlayerIdLookToWorldHandler.class);
		register(123303, ReqStallsSortToWorldMessage.class, ReqStallsSortToWorldHandler.class);
		register(123304, ReqStallsBuycheckToWorldMessage.class, ReqStallsBuycheckToWorldHandler.class);
		register(123306, ReqStallsBuyToWorldMessage.class, ReqStallsBuyToWorldHandler.class);
		register(123310, ReqStallsProductWasAddedToWorldMessage.class, ReqStallsProductWasAddedToWorldHandler.class);
		register(123312, ReqStallsAdjustPricesToWorldMessage.class, ReqStallsAdjustPricesToWorldHandler.class);
		register(123314, ReqStallsOffShelfToWorldMessage.class, ReqStallsOffShelfToWorldHandler.class);
		register(123316, ReqStallsSearchToWorldMessage.class, ReqStallsSearchToWorldHandler.class);
		register(123317, ReqChangeStallsNameToWorldMessage.class, ReqChangeStallsNameToWorldHandler.class);
		register(111301, ChatResponseSWMessage.class, ChatResponseSWHandler.class);
		register(103307, ReqOtherPlayerInfoToWorldMessage.class, ReqOtherPlayerInfoToWorldHandler.class);
		register(103309, ReqNonageTimeToWorldMessage.class, ReqNonageTimeToWorldHandler.class);
		register(200301, GmCommandToWorldMessage.class, GmCommandToWorldHandler.class);
		register(103310, ReqNonageRegisterToWorldMessage.class, ReqNonageRegisterToWorldHandler.class);
		register(118317, ReqMapSearchMemberNameWorldMessage.class, ReqMapSearchMemberNameWorldHandler.class);
		register(118318, ReqTeamMessageWorldMessage.class, ReqTeamMessageWorldHandler.class);
		register(118319, ReqGenericSearchToWorldMessage.class, ReqGenericSearchToWorldHandler.class);
		register(125301, ResSetMenuStatusToWorldMessage.class, ResSetMenuStatusToWorldHandler.class);
		register(119308, ReqRelationConfigToWorldMessage.class, ReqRelationConfigToWorldHandler.class);
		register(118320, ReqIntoTeamToWorldMessage.class, ReqIntoTeamToWorldHandler.class);
		register(103311, ReqPlayerCheckOnlineToWorldMessage.class, ReqPlayerCheckOnlineToWorldHandler.class);
		register(103312, ReqGetPlayerAppearanceInfoToWorldMessage.class, ReqGetPlayerAppearanceInfoToWorldHandler.class);
		register(103313, ReqDelPlayerToWorldMessage.class, ReqDelPlayerToWorldHandler.class);
		register(120301, ReqTargetMonsterMessage.class, ReqTargetMonsterHandler.class);
		register(103318, ReqChangePlayerNameToWorldMessage.class, ReqChangePlayerNameToWorldHandler.class);
		register(300309, ResCloseSucessForGameMessage.class, ResCloseSucessForGameHandler.class);
		register(300310, ResCloseSucessForGateMessage.class, ResCloseSucessForGateHandler.class);
		register(135303, ResPlayerMoneyGoldToWorldMessage.class, ResPlayerMoneyGoldToWorldHandler.class);
		register(135304, ResPlayerInfoToWorldMessage.class, ResPlayerInfoToWorldHandler.class);
		register(121322, ReqInnerGuildNotifyToWorldMessage.class, ReqInnerGuildNotifyToWorldHandler.class);
		register(133301, ReqGetAllFruitInfoToWorldMessage.class, ReqGetAllFruitInfoToWorldHandler.class);
		register(133302, ReqGetSingleFruitInfoToWorldMessage.class, ReqGetSingleFruitInfoToWorldHandler.class);
		register(133304, ReqRipeningFruitToWorldMessage.class, ReqRipeningFruitToWorldHandler.class);
		register(133305, ReqOpenGuildFruitToWorldMessage.class, ReqOpenGuildFruitToWorldHandler.class);
		register(133306, ReqGuildFruitOperatingToWorldMessage.class, ReqGuildFruitOperatingToWorldHandler.class);
		register(202301, ReqVersionUpdateToWorldMessage.class, ReqVersionUpdateToWorldHandler.class);
		register(137201, ReqInnerCardToWorldMessage.class, ReqInnerCardToWorldHandler.class);
		register(133309, ResRipeningDecYBToWorldMessage.class, ResRipeningDecYBToWorldHandler.class);
		register(139301, ReqYBCardToWorldMessage.class, ReqYBCardToWorldHandler.class);
		register(133310, ReqGuildFruitLogToWorldMessage.class, ReqGuildFruitLogToWorldHandler.class);
		register(133312, ReqGuildGMToWorldMessage.class, ReqGuildGMToWorldHandler.class);
		register(114303, ReqMonsterDoubleNoticeMessage.class, ReqMonsterDoubleNoticeHandler.class);
		register(142301, ReqGetTopListToWorldMessage.class, ReqGetTopListToWorldHandler.class);
		register(142302, ReqWorShipToWorldMessage.class, ReqWorShipToWorldHandler.class);
		register(103320, ReqSyncPlayerEventcutMessage.class, ReqSyncPlayerEventcutHandler.class);
		register(103321, ReqSyncPlayerSkillMessage.class, ReqSyncPlayerSkillHandler.class);
		register(103322, ReqSyncPlayerHorseMessage.class, ReqSyncPlayerHorseHandler.class);
		register(103323, ReqSyncPlayerLongyuanMessage.class, ReqSyncPlayerLongyuanHandler.class);
		register(103324, ReqSyncPlayerOrderInfoMessage.class, ReqSyncPlayerOrderInfoHandler.class);
		register(103325, ReqSyncPlayerEquipMessage.class, ReqSyncPlayerEquipHandler.class);
		register(103326, ReqSyncPlayerGemMessage.class, ReqSyncPlayerGemHandler.class);
		register(103327, ReqSyncPlayerAttributeMessage.class, ReqSyncPlayerAttributeHandler.class);
		register(103328, ReqScriptCommonServerToWorldMessage.class, ReqScriptCommonServerToWorldHandler.class);
		register(142303, ReqZoneTopToWorldMessage.class, ReqZoneTopToWorldHandler.class);
		register(124251, ReqMailSendSystemMailToWorldMessage.class, ReqMailSendSystemMailToWorldHandler.class);
		register(146300, ReqCountryStructureInfoToWoridMessage.class, ReqCountryStructureInfoToWoridHandler.class);
		register(121323, ReqInnerKingCityEventToWorldMessage.class, ReqInnerKingCityEventToWorldHandler.class);
		register(146301, ReqCountrySyncKingCityToWoridMessage.class, ReqCountrySyncKingCityToWoridHandler.class);
		register(138301, ReqActivitiesInfoToWorldMessage.class, ReqActivitiesInfoToWorldHandler.class);
		register(138302, ReqGetRewardToWorldMessage.class, ReqGetRewardToWorldHandler.class);
		register(137202, ReqInnerCardPhoneToWorldMessage.class, ReqInnerCardPhoneToWorldHandler.class);
		register(148301, ReqScriptToWorldMessage.class, ReqScriptToWorldHandler.class);
		register(103330, ReqSyncPlayerPetMessage.class, ReqSyncPlayerPetHandler.class);
		register(103331, ReqSyncPlayerPetInfoMessage.class, ReqSyncPlayerPetInfoHandler.class);
		register(121324, ReqGuildStrMessageToWorldMessage.class, ReqGuildStrMessageToWorldHandler.class);
		register(103332, ReqSyncPlayerRankMessage.class, ReqSyncPlayerRankHandler.class);
		register(103333, ReqSyncPlayerArrowMessage.class, ReqSyncPlayerArrowHandler.class);
		register(133313, ReqActivityCheckFruitToWorldMessage.class, ReqActivityCheckFruitToWorldHandler.class);
		register(133315, ReqUrgeRipeToWorldMessage.class, ReqUrgeRipeToWorldHandler.class);
		register(133316, ReqContinuousRipeningToWorldMessage.class, ReqContinuousRipeningToWorldHandler.class);
		register(103334, ReqSyncPlayerHorseWeaponMessage.class, ReqSyncPlayerHorseWeaponHandler.class);
		register(103335, ReqSyncPlayerHiddenWeaponMessage.class, ReqSyncPlayerHiddenWeaponHandler.class);
		register(103336, ReqSyncPlayerRealMMessage.class, ReqSyncPlayerRealMHandler.class);
		register(138303, ReqGetTailiToWorldMessage.class, ReqGetTailiToWorldHandler.class);
		register(163301, ReqUpdatedMarriageToWorldMessage.class, ReqUpdatedMarriageToWorldHandler.class);
		register(163302, ReqDeleteMarriageToWorldMessage.class, ReqDeleteMarriageToWorldHandler.class);
		register(103337, ReqSyncPlayerGoldExpendMessage.class, ReqSyncPlayerGoldExpendHandler.class);
	}
	
	private void register(int id, Class<?> messageClass, Class<?> handlerClass){
		messages.put(id, messageClass);
		if(handlerClass!=null) handlers.put(id, handlerClass);
	}
	
	/**
	 * 获取消息体
	 * @param id
	 * @return
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 */
	public Message getMessage(int id) throws InstantiationException, IllegalAccessException{
		if(!messages.containsKey(id)){
			return null;
		}else{
			return (Message)messages.get(id).newInstance();
		}
	}
	
	/**
	 * 获取处理函数
	 * @param id
	 * @return
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 */
	public Handler getHandler(int id) throws InstantiationException, IllegalAccessException{
		if(!handlers.containsKey(id)){
			return null;
		}else{
			return (Handler)handlers.get(id).newInstance();
		}
	}
}