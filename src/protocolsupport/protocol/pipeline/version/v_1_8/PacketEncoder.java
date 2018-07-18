package protocolsupport.protocol.pipeline.version.v_1_8;

import java.util.concurrent.TimeUnit;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import protocolsupport.api.Connection;
import protocolsupport.api.utils.NetworkState;
import protocolsupport.protocol.packet.ClientBoundPacket;
import protocolsupport.protocol.packet.middleimpl.ClientBoundPacketData;
import protocolsupport.protocol.packet.middleimpl.clientbound.login.v_4_5_6_7_8_9r1_9r2_10_11_12r1_12r2.EncryptionRequest;
import protocolsupport.protocol.packet.middleimpl.clientbound.login.v_7_8_9r1_9r2_10_11_12r1_12r2.LoginDisconnect;
import protocolsupport.protocol.packet.middleimpl.clientbound.login.v_7_8_9r1_9r2_10_11_12r1_12r2.LoginSuccess;
import protocolsupport.protocol.packet.middleimpl.clientbound.login.v_8_9r1_9r2_10_11_12r1_12r2.SetCompression;
import protocolsupport.protocol.packet.middleimpl.clientbound.play.noop.NoopAdvancements;
import protocolsupport.protocol.packet.middleimpl.clientbound.play.noop.NoopAdvanementsTab;
import protocolsupport.protocol.packet.middleimpl.clientbound.play.noop.NoopBossBar;
import protocolsupport.protocol.packet.middleimpl.clientbound.play.noop.NoopCraftingGridConfirm;
import protocolsupport.protocol.packet.middleimpl.clientbound.play.noop.NoopDeclareCommands;
import protocolsupport.protocol.packet.middleimpl.clientbound.play.noop.NoopDeclareRecipes;
import protocolsupport.protocol.packet.middleimpl.clientbound.play.noop.NoopDeclareTags;
import protocolsupport.protocol.packet.middleimpl.clientbound.play.noop.NoopSetCooldown;
import protocolsupport.protocol.packet.middleimpl.clientbound.play.noop.NoopStatistics;
import protocolsupport.protocol.packet.middleimpl.clientbound.play.noop.NoopUnlockRecipes;
import protocolsupport.protocol.packet.middleimpl.clientbound.play.noop.NoopVehicleMove;
import protocolsupport.protocol.packet.middleimpl.clientbound.play.v_4_5_6_7_8.WorldCustomSound;
import protocolsupport.protocol.packet.middleimpl.clientbound.play.v_4_5_6_7_8.WorldSound;
import protocolsupport.protocol.packet.middleimpl.clientbound.play.v_4_5_6_7_8_9r1_9r2_10_11_12r1_12r2.EntityStatus;
import protocolsupport.protocol.packet.middleimpl.clientbound.play.v_4_5_6_7_8_9r1_9r2_10_11_12r1_12r2.InventoryClose;
import protocolsupport.protocol.packet.middleimpl.clientbound.play.v_4_5_6_7_8_9r1_9r2_10_11_12r1_12r2.InventoryConfirmTransaction;
import protocolsupport.protocol.packet.middleimpl.clientbound.play.v_4_5_6_7_8_9r1_9r2_10_11_12r1_12r2.InventoryData;
import protocolsupport.protocol.packet.middleimpl.clientbound.play.v_4_5_6_7_8_9r1_9r2_10_11_12r1_12r2.InventorySetItems;
import protocolsupport.protocol.packet.middleimpl.clientbound.play.v_4_5_6_7_8_9r1_9r2_10_11_12r1_12r2.InventorySetSlot;
import protocolsupport.protocol.packet.middleimpl.clientbound.play.v_4_5_6_7_8_9r1_9r2_10_11_12r1_12r2.ScoreboardDisplay;
import protocolsupport.protocol.packet.middleimpl.clientbound.play.v_4_5_6_7_8_9r1_9r2_10_11_12r1_12r2.TimeUpdate;
import protocolsupport.protocol.packet.middleimpl.clientbound.play.v_6_7_8.EntityLeash;
import protocolsupport.protocol.packet.middleimpl.clientbound.play.v_6_7_8.VehiclePassengers;
import protocolsupport.protocol.packet.middleimpl.clientbound.play.v_6_7_8_9r1_9r2_10_11_12r1_12r2.PlayerAbilities;
import protocolsupport.protocol.packet.middleimpl.clientbound.play.v_7_8.SpawnExpOrb;
import protocolsupport.protocol.packet.middleimpl.clientbound.play.v_7_8.SpawnGlobal;
import protocolsupport.protocol.packet.middleimpl.clientbound.play.v_7_8.SpawnLiving;
import protocolsupport.protocol.packet.middleimpl.clientbound.play.v_7_8_9r1_9r2_10_11_12r1_12r2.Animation;
import protocolsupport.protocol.packet.middleimpl.clientbound.play.v_7_8_9r1_9r2_10_11_12r1_12r2.ChangeDimension;
import protocolsupport.protocol.packet.middleimpl.clientbound.play.v_7_8_9r1_9r2_10_11_12r1_12r2.Explosion;
import protocolsupport.protocol.packet.middleimpl.clientbound.play.v_7_8_9r1_9r2_10_11_12r1_12r2.GameStateChange;
import protocolsupport.protocol.packet.middleimpl.clientbound.play.v_7_8_9r1_9r2_10_11_12r1_12r2.HeldSlot;
import protocolsupport.protocol.packet.middleimpl.clientbound.play.v_7_8_9r1_9r2_10_11_12r1_12r2.KickDisconnect;
import protocolsupport.protocol.packet.middleimpl.clientbound.play.v_7_8_9r1_9r2_10_11_12r1_12r2.TabComplete;
import protocolsupport.protocol.packet.middleimpl.clientbound.play.v_8.Chunk;
import protocolsupport.protocol.packet.middleimpl.clientbound.play.v_8.EntityEquipment;
import protocolsupport.protocol.packet.middleimpl.clientbound.play.v_8.EntityMetadata;
import protocolsupport.protocol.packet.middleimpl.clientbound.play.v_8.EntityRelMove;
import protocolsupport.protocol.packet.middleimpl.clientbound.play.v_8.EntityRelMoveLook;
import protocolsupport.protocol.packet.middleimpl.clientbound.play.v_8.EntityTeleport;
import protocolsupport.protocol.packet.middleimpl.clientbound.play.v_8.Map;
import protocolsupport.protocol.packet.middleimpl.clientbound.play.v_8.ScoreboardTeam;
import protocolsupport.protocol.packet.middleimpl.clientbound.play.v_8.SetPosition;
import protocolsupport.protocol.packet.middleimpl.clientbound.play.v_8.SpawnNamed;
import protocolsupport.protocol.packet.middleimpl.clientbound.play.v_8.SpawnObject;
import protocolsupport.protocol.packet.middleimpl.clientbound.play.v_8.SpawnPainting;
import protocolsupport.protocol.packet.middleimpl.clientbound.play.v_8.UnloadChunk;
import protocolsupport.protocol.packet.middleimpl.clientbound.play.v_8_9r1_9r2_10.CollectEffect;
import protocolsupport.protocol.packet.middleimpl.clientbound.play.v_8_9r1_9r2_10.Title;
import protocolsupport.protocol.packet.middleimpl.clientbound.play.v_8_9r1_9r2_10_11_12r1_12r2.BlockAction;
import protocolsupport.protocol.packet.middleimpl.clientbound.play.v_8_9r1_9r2_10_11_12r1_12r2.BlockBreakAnimation;
import protocolsupport.protocol.packet.middleimpl.clientbound.play.v_8_9r1_9r2_10_11_12r1_12r2.BlockChangeMulti;
import protocolsupport.protocol.packet.middleimpl.clientbound.play.v_8_9r1_9r2_10_11_12r1_12r2.BlockChangeSingle;
import protocolsupport.protocol.packet.middleimpl.clientbound.play.v_8_9r1_9r2_10_11_12r1_12r2.BlockOpenSignEditor;
import protocolsupport.protocol.packet.middleimpl.clientbound.play.v_8_9r1_9r2_10_11_12r1_12r2.BlockTileUpdate;
import protocolsupport.protocol.packet.middleimpl.clientbound.play.v_8_9r1_9r2_10_11_12r1_12r2.Camera;
import protocolsupport.protocol.packet.middleimpl.clientbound.play.v_8_9r1_9r2_10_11_12r1_12r2.Chat;
import protocolsupport.protocol.packet.middleimpl.clientbound.play.v_8_9r1_9r2_10_11_12r1_12r2.CombatEvent;
import protocolsupport.protocol.packet.middleimpl.clientbound.play.v_8_9r1_9r2_10_11_12r1_12r2.CustomPayload;
import protocolsupport.protocol.packet.middleimpl.clientbound.play.v_8_9r1_9r2_10_11_12r1_12r2.Entity;
import protocolsupport.protocol.packet.middleimpl.clientbound.play.v_8_9r1_9r2_10_11_12r1_12r2.EntityDestroy;
import protocolsupport.protocol.packet.middleimpl.clientbound.play.v_8_9r1_9r2_10_11_12r1_12r2.EntityEffectAdd;
import protocolsupport.protocol.packet.middleimpl.clientbound.play.v_8_9r1_9r2_10_11_12r1_12r2.EntityEffectRemove;
import protocolsupport.protocol.packet.middleimpl.clientbound.play.v_8_9r1_9r2_10_11_12r1_12r2.EntityHeadRotation;
import protocolsupport.protocol.packet.middleimpl.clientbound.play.v_8_9r1_9r2_10_11_12r1_12r2.EntityLook;
import protocolsupport.protocol.packet.middleimpl.clientbound.play.v_8_9r1_9r2_10_11_12r1_12r2.EntitySetAttributes;
import protocolsupport.protocol.packet.middleimpl.clientbound.play.v_8_9r1_9r2_10_11_12r1_12r2.EntityVelocity;
import protocolsupport.protocol.packet.middleimpl.clientbound.play.v_8_9r1_9r2_10_11_12r1_12r2.InventoryOpen;
import protocolsupport.protocol.packet.middleimpl.clientbound.play.v_8_9r1_9r2_10_11_12r1_12r2.KeepAlive;
import protocolsupport.protocol.packet.middleimpl.clientbound.play.v_8_9r1_9r2_10_11_12r1_12r2.PlayerListHeaderFooter;
import protocolsupport.protocol.packet.middleimpl.clientbound.play.v_8_9r1_9r2_10_11_12r1_12r2.PlayerListSetEntry;
import protocolsupport.protocol.packet.middleimpl.clientbound.play.v_8_9r1_9r2_10_11_12r1_12r2.ResourcePack;
import protocolsupport.protocol.packet.middleimpl.clientbound.play.v_8_9r1_9r2_10_11_12r1_12r2.ScoreboardObjective;
import protocolsupport.protocol.packet.middleimpl.clientbound.play.v_8_9r1_9r2_10_11_12r1_12r2.ScoreboardScore;
import protocolsupport.protocol.packet.middleimpl.clientbound.play.v_8_9r1_9r2_10_11_12r1_12r2.ServerDifficulty;
import protocolsupport.protocol.packet.middleimpl.clientbound.play.v_8_9r1_9r2_10_11_12r1_12r2.SetExperience;
import protocolsupport.protocol.packet.middleimpl.clientbound.play.v_8_9r1_9r2_10_11_12r1_12r2.SetHealth;
import protocolsupport.protocol.packet.middleimpl.clientbound.play.v_8_9r1_9r2_10_11_12r1_12r2.SpawnPosition;
import protocolsupport.protocol.packet.middleimpl.clientbound.play.v_8_9r1_9r2_10_11_12r1_12r2.StartGame;
import protocolsupport.protocol.packet.middleimpl.clientbound.play.v_8_9r1_9r2_10_11_12r1_12r2.UseBed;
import protocolsupport.protocol.packet.middleimpl.clientbound.play.v_8_9r1_9r2_10_11_12r1_12r2.WorldBorder;
import protocolsupport.protocol.packet.middleimpl.clientbound.play.v_8_9r1_9r2_10_11_12r1_12r2.WorldEvent;
import protocolsupport.protocol.packet.middleimpl.clientbound.play.v_8_9r1_9r2_10_11_12r1_12r2.WorldParticle;
import protocolsupport.protocol.packet.middleimpl.clientbound.status.v_7_8_9r1_9r2_10_11_12r1_12r2.Pong;
import protocolsupport.protocol.packet.middleimpl.clientbound.status.v_7_8_9r1_9r2_10_11_12r1_12r2.ServerInfo;
import protocolsupport.protocol.pipeline.version.util.encoder.AbstractModernPacketEncoder;
import protocolsupport.protocol.storage.netcache.NetworkDataCache;
import protocolsupport.protocol.typeremapper.packet.ChunkSendIntervalPacketQueue;
import protocolsupport.protocol.utils.registry.PacketIdTransformerRegistry;
import protocolsupport.utils.recyclable.RecyclableCollection;
import protocolsupport.zplatform.ServerPlatform;

public class PacketEncoder extends AbstractModernPacketEncoder {

	protected static final PacketIdTransformerRegistry packetIdRegistry = new PacketIdTransformerRegistry();
	static {
		packetIdRegistry.register(NetworkState.LOGIN, ClientBoundPacket.LOGIN_DISCONNECT_ID, 0x00);
		packetIdRegistry.register(NetworkState.LOGIN, ClientBoundPacket.LOGIN_ENCRYPTION_BEGIN_ID, 0x01);
		packetIdRegistry.register(NetworkState.LOGIN, ClientBoundPacket.LOGIN_SUCCESS_ID, 0x02);
		packetIdRegistry.register(NetworkState.LOGIN, ClientBoundPacket.LOGIN_SET_COMPRESSION_ID, 0x03);
		packetIdRegistry.register(NetworkState.STATUS, ClientBoundPacket.STATUS_SERVER_INFO_ID, 0x00);
		packetIdRegistry.register(NetworkState.STATUS, ClientBoundPacket.STATUS_PONG_ID, 0x01);
		packetIdRegistry.register(NetworkState.PLAY, ClientBoundPacket.PLAY_KEEP_ALIVE_ID, 0x00);
		packetIdRegistry.register(NetworkState.PLAY, ClientBoundPacket.PLAY_LOGIN_ID, 0x01);
		packetIdRegistry.register(NetworkState.PLAY, ClientBoundPacket.PLAY_CHAT_ID, 0x02);
		packetIdRegistry.register(NetworkState.PLAY, ClientBoundPacket.PLAY_UPDATE_TIME_ID, 0x03);
		packetIdRegistry.register(NetworkState.PLAY, ClientBoundPacket.PLAY_ENTITY_EQUIPMENT_ID, 0x04);
		packetIdRegistry.register(NetworkState.PLAY, ClientBoundPacket.PLAY_SPAWN_POSITION_ID, 0x05);
		packetIdRegistry.register(NetworkState.PLAY, ClientBoundPacket.PLAY_UPDATE_HEALTH_ID, 0x06);
		packetIdRegistry.register(NetworkState.PLAY, ClientBoundPacket.PLAY_RESPAWN_ID, 0x07);
		packetIdRegistry.register(NetworkState.PLAY, ClientBoundPacket.PLAY_POSITION_ID, 0x08);
		packetIdRegistry.register(NetworkState.PLAY, ClientBoundPacket.PLAY_HELD_SLOT_ID, 0x09);
		packetIdRegistry.register(NetworkState.PLAY, ClientBoundPacket.PLAY_BED_ID, 0x0A);
		packetIdRegistry.register(NetworkState.PLAY, ClientBoundPacket.PLAY_ANIMATION_ID, 0x0B);
		packetIdRegistry.register(NetworkState.PLAY, ClientBoundPacket.PLAY_SPAWN_NAMED_ID, 0x0C);
		packetIdRegistry.register(NetworkState.PLAY, ClientBoundPacket.PLAY_COLLECT_EFFECT_ID, 0x0D);
		packetIdRegistry.register(NetworkState.PLAY, ClientBoundPacket.PLAY_SPAWN_OBJECT_ID, 0x0E);
		packetIdRegistry.register(NetworkState.PLAY, ClientBoundPacket.PLAY_SPAWN_LIVING_ID, 0x0F);
		packetIdRegistry.register(NetworkState.PLAY, ClientBoundPacket.PLAY_SPAWN_PAINTING_ID, 0x10);
		packetIdRegistry.register(NetworkState.PLAY, ClientBoundPacket.PLAY_SPAWN_EXP_ORB_ID, 0x11);
		packetIdRegistry.register(NetworkState.PLAY, ClientBoundPacket.PLAY_ENTITY_VELOCITY_ID, 0x12);
		packetIdRegistry.register(NetworkState.PLAY, ClientBoundPacket.PLAY_ENTITY_DESTROY_ID, 0x13);
		packetIdRegistry.register(NetworkState.PLAY, ClientBoundPacket.PLAY_ENTITY_ID, 0x14);
		packetIdRegistry.register(NetworkState.PLAY, ClientBoundPacket.PLAY_ENTITY_REL_MOVE_ID, 0x15);
		packetIdRegistry.register(NetworkState.PLAY, ClientBoundPacket.PLAY_ENTITY_LOOK_ID, 0x16);
		packetIdRegistry.register(NetworkState.PLAY, ClientBoundPacket.PLAY_ENTITY_REL_MOVE_LOOK_ID, 0x17);
		packetIdRegistry.register(NetworkState.PLAY, ClientBoundPacket.PLAY_ENTITY_TELEPORT_ID, 0x18);
		packetIdRegistry.register(NetworkState.PLAY, ClientBoundPacket.PLAY_ENTITY_HEAD_ROTATION_ID, 0x19);
		packetIdRegistry.register(NetworkState.PLAY, ClientBoundPacket.PLAY_ENTITY_STATUS_ID, 0x1A);
		packetIdRegistry.register(NetworkState.PLAY, ClientBoundPacket.PLAY_ENTITY_LEASH_ID, 0x1B);
		packetIdRegistry.register(NetworkState.PLAY, ClientBoundPacket.PLAY_ENTITY_METADATA_ID, 0x1C);
		packetIdRegistry.register(NetworkState.PLAY, ClientBoundPacket.PLAY_ENTITY_EFFECT_ADD_ID, 0x1D);
		packetIdRegistry.register(NetworkState.PLAY, ClientBoundPacket.PLAY_ENTITY_EFFECT_REMOVE_ID, 0x1E);
		packetIdRegistry.register(NetworkState.PLAY, ClientBoundPacket.PLAY_EXPERIENCE_ID, 0x1F);
		packetIdRegistry.register(NetworkState.PLAY, ClientBoundPacket.PLAY_ENTITY_ATTRIBUTES_ID, 0x20);
		packetIdRegistry.register(NetworkState.PLAY, ClientBoundPacket.PLAY_CHUNK_SINGLE_ID, 0x21);
		packetIdRegistry.register(NetworkState.PLAY, ClientBoundPacket.PLAY_BLOCK_CHANGE_MULTI_ID, 0x22);
		packetIdRegistry.register(NetworkState.PLAY, ClientBoundPacket.PLAY_BLOCK_CHANGE_SINGLE_ID, 0x23);
		packetIdRegistry.register(NetworkState.PLAY, ClientBoundPacket.PLAY_BLOCK_ACTION_ID, 0x24);
		packetIdRegistry.register(NetworkState.PLAY, ClientBoundPacket.PLAY_BLOCK_BREAK_ANIMATION_ID, 0x25);
		packetIdRegistry.register(NetworkState.PLAY, ClientBoundPacket.PLAY_EXPLOSION_ID, 0x27);
		packetIdRegistry.register(NetworkState.PLAY, ClientBoundPacket.PLAY_WORLD_EVENT_ID, 0x28);
		packetIdRegistry.register(NetworkState.PLAY, ClientBoundPacket.PLAY_WORLD_CUSTOM_SOUND_ID, 0x29);
		packetIdRegistry.register(NetworkState.PLAY, ClientBoundPacket.PLAY_WORLD_PARTICLES_ID, 0x2A);
		packetIdRegistry.register(NetworkState.PLAY, ClientBoundPacket.PLAY_GAME_STATE_CHANGE_ID, 0x2B);
		packetIdRegistry.register(NetworkState.PLAY, ClientBoundPacket.PLAY_SPAWN_WEATHER_ID, 0x2C);
		packetIdRegistry.register(NetworkState.PLAY, ClientBoundPacket.PLAY_WINDOW_OPEN_ID, 0x2D);
		packetIdRegistry.register(NetworkState.PLAY, ClientBoundPacket.PLAY_WINDOW_CLOSE_ID, 0x2E);
		packetIdRegistry.register(NetworkState.PLAY, ClientBoundPacket.PLAY_WINDOW_SET_SLOT_ID, 0x2F);
		packetIdRegistry.register(NetworkState.PLAY, ClientBoundPacket.PLAY_WINDOW_SET_ITEMS_ID, 0x30);
		packetIdRegistry.register(NetworkState.PLAY, ClientBoundPacket.PLAY_WINDOW_DATA_ID, 0x31);
		packetIdRegistry.register(NetworkState.PLAY, ClientBoundPacket.PLAY_WINDOW_TRANSACTION_ID, 0x32);
		packetIdRegistry.register(NetworkState.PLAY, ClientBoundPacket.LEGACY_PLAY_UPDATE_SIGN_ID, 0x33);
		packetIdRegistry.register(NetworkState.PLAY, ClientBoundPacket.PLAY_MAP_ID, 0x34);
		packetIdRegistry.register(NetworkState.PLAY, ClientBoundPacket.PLAY_UPDATE_TILE_ID, 0x35);
		packetIdRegistry.register(NetworkState.PLAY, ClientBoundPacket.PLAY_SIGN_EDITOR_ID, 0x36);
		packetIdRegistry.register(NetworkState.PLAY, ClientBoundPacket.PLAY_STATISTICS_ID, 0x37);
		packetIdRegistry.register(NetworkState.PLAY, ClientBoundPacket.PLAY_PLAYER_INFO_ID, 0x38);
		packetIdRegistry.register(NetworkState.PLAY, ClientBoundPacket.PLAY_ABILITIES_ID, 0x39);
		packetIdRegistry.register(NetworkState.PLAY, ClientBoundPacket.PLAY_TAB_COMPLETE_ID, 0x3A);
		packetIdRegistry.register(NetworkState.PLAY, ClientBoundPacket.PLAY_SCOREBOARD_OBJECTIVE_ID, 0x3B);
		packetIdRegistry.register(NetworkState.PLAY, ClientBoundPacket.PLAY_SCOREBOARD_SCORE_ID, 0x3C);
		packetIdRegistry.register(NetworkState.PLAY, ClientBoundPacket.PLAY_SCOREBOARD_DISPLAY_SLOT_ID, 0x3D);
		packetIdRegistry.register(NetworkState.PLAY, ClientBoundPacket.PLAY_SCOREBOARD_TEAM_ID, 0x3E);
		packetIdRegistry.register(NetworkState.PLAY, ClientBoundPacket.PLAY_CUSTOM_PAYLOAD_ID, 0x3F);
		packetIdRegistry.register(NetworkState.PLAY, ClientBoundPacket.PLAY_KICK_DISCONNECT_ID, 0x40);
		packetIdRegistry.register(NetworkState.PLAY, ClientBoundPacket.PLAY_SERVER_DIFFICULTY_ID, 0x41);
		packetIdRegistry.register(NetworkState.PLAY, ClientBoundPacket.PLAY_COMBAT_EVENT_ID, 0x42);
		packetIdRegistry.register(NetworkState.PLAY, ClientBoundPacket.PLAY_CAMERA_ID, 0x43);
		packetIdRegistry.register(NetworkState.PLAY, ClientBoundPacket.PLAY_WORLD_BORDER_ID, 0x44);
		packetIdRegistry.register(NetworkState.PLAY, ClientBoundPacket.PLAY_TITLE_ID, 0x45);
		packetIdRegistry.register(NetworkState.PLAY, ClientBoundPacket.PLAY_PLAYER_LIST_HEADER_FOOTER_ID, 0x47);
		packetIdRegistry.register(NetworkState.PLAY, ClientBoundPacket.PLAY_RESOURCE_PACK_ID, 0x48);
	}

	@Override
	protected int getNewPacketId(NetworkState currentProtocol, int oldPacketId) {
		return packetIdRegistry.getNewPacketId(currentProtocol, oldPacketId);
	}

	{
		registry.register(NetworkState.LOGIN, ClientBoundPacket.LOGIN_SUCCESS_ID, LoginSuccess.class);
		registry.register(NetworkState.LOGIN, ClientBoundPacket.LOGIN_ENCRYPTION_BEGIN_ID, EncryptionRequest.class);
		registry.register(NetworkState.LOGIN, ClientBoundPacket.LOGIN_DISCONNECT_ID, LoginDisconnect.class);
		registry.register(NetworkState.LOGIN, ClientBoundPacket.LOGIN_SET_COMPRESSION_ID, SetCompression.class);
		registry.register(NetworkState.STATUS, ClientBoundPacket.STATUS_SERVER_INFO_ID, ServerInfo.class);
		registry.register(NetworkState.STATUS, ClientBoundPacket.STATUS_PONG_ID, Pong.class);
		registry.register(NetworkState.PLAY, ClientBoundPacket.PLAY_KEEP_ALIVE_ID, KeepAlive.class);
		registry.register(NetworkState.PLAY, ClientBoundPacket.PLAY_LOGIN_ID, StartGame.class);
		registry.register(NetworkState.PLAY, ClientBoundPacket.PLAY_CHAT_ID, Chat.class);
		registry.register(NetworkState.PLAY, ClientBoundPacket.PLAY_UPDATE_TIME_ID, TimeUpdate.class);
		registry.register(NetworkState.PLAY, ClientBoundPacket.PLAY_ENTITY_EQUIPMENT_ID, EntityEquipment.class);
		registry.register(NetworkState.PLAY, ClientBoundPacket.PLAY_SPAWN_POSITION_ID, SpawnPosition.class);
		registry.register(NetworkState.PLAY, ClientBoundPacket.PLAY_UPDATE_HEALTH_ID, SetHealth.class);
		registry.register(NetworkState.PLAY, ClientBoundPacket.PLAY_RESPAWN_ID, ChangeDimension.class);
		registry.register(NetworkState.PLAY, ClientBoundPacket.PLAY_POSITION_ID, SetPosition.class);
		registry.register(NetworkState.PLAY, ClientBoundPacket.PLAY_HELD_SLOT_ID, HeldSlot.class);
		registry.register(NetworkState.PLAY, ClientBoundPacket.PLAY_BED_ID, UseBed.class);
		registry.register(NetworkState.PLAY, ClientBoundPacket.PLAY_ANIMATION_ID, Animation.class);
		registry.register(NetworkState.PLAY, ClientBoundPacket.PLAY_SPAWN_NAMED_ID, SpawnNamed.class);
		registry.register(NetworkState.PLAY, ClientBoundPacket.PLAY_COLLECT_EFFECT_ID, CollectEffect.class);
		registry.register(NetworkState.PLAY, ClientBoundPacket.PLAY_SPAWN_OBJECT_ID, SpawnObject.class);
		registry.register(NetworkState.PLAY, ClientBoundPacket.PLAY_SPAWN_LIVING_ID, SpawnLiving.class);
		registry.register(NetworkState.PLAY, ClientBoundPacket.PLAY_SPAWN_PAINTING_ID, SpawnPainting.class);
		registry.register(NetworkState.PLAY, ClientBoundPacket.PLAY_SPAWN_EXP_ORB_ID, SpawnExpOrb.class);
		registry.register(NetworkState.PLAY, ClientBoundPacket.PLAY_ENTITY_VELOCITY_ID, EntityVelocity.class);
		registry.register(NetworkState.PLAY, ClientBoundPacket.PLAY_ENTITY_DESTROY_ID, EntityDestroy.class);
		registry.register(NetworkState.PLAY, ClientBoundPacket.PLAY_ENTITY_ID, Entity.class);
		registry.register(NetworkState.PLAY, ClientBoundPacket.PLAY_ENTITY_REL_MOVE_ID, EntityRelMove.class);
		registry.register(NetworkState.PLAY, ClientBoundPacket.PLAY_ENTITY_LOOK_ID, EntityLook.class);
		registry.register(NetworkState.PLAY, ClientBoundPacket.PLAY_ENTITY_REL_MOVE_LOOK_ID, EntityRelMoveLook.class);
		registry.register(NetworkState.PLAY, ClientBoundPacket.PLAY_ENTITY_TELEPORT_ID, EntityTeleport.class);
		registry.register(NetworkState.PLAY, ClientBoundPacket.PLAY_ENTITY_HEAD_ROTATION_ID, EntityHeadRotation.class);
		registry.register(NetworkState.PLAY, ClientBoundPacket.PLAY_ENTITY_STATUS_ID, EntityStatus.class);
		registry.register(NetworkState.PLAY, ClientBoundPacket.PLAY_ENTITY_LEASH_ID, EntityLeash.class);
		registry.register(NetworkState.PLAY, ClientBoundPacket.PLAY_ENTITY_METADATA_ID, EntityMetadata.class);
		registry.register(NetworkState.PLAY, ClientBoundPacket.PLAY_ENTITY_EFFECT_ADD_ID, EntityEffectAdd.class);
		registry.register(NetworkState.PLAY, ClientBoundPacket.PLAY_ENTITY_EFFECT_REMOVE_ID, EntityEffectRemove.class);
		registry.register(NetworkState.PLAY, ClientBoundPacket.PLAY_EXPERIENCE_ID, SetExperience.class);
		registry.register(NetworkState.PLAY, ClientBoundPacket.PLAY_ENTITY_ATTRIBUTES_ID, EntitySetAttributes.class);
		registry.register(NetworkState.PLAY, ClientBoundPacket.PLAY_CHUNK_SINGLE_ID, Chunk.class);
		registry.register(NetworkState.PLAY, ClientBoundPacket.PLAY_BLOCK_CHANGE_MULTI_ID, BlockChangeMulti.class);
		registry.register(NetworkState.PLAY, ClientBoundPacket.PLAY_BLOCK_CHANGE_SINGLE_ID, BlockChangeSingle.class);
		registry.register(NetworkState.PLAY, ClientBoundPacket.PLAY_BLOCK_ACTION_ID, BlockAction.class);
		registry.register(NetworkState.PLAY, ClientBoundPacket.PLAY_BLOCK_BREAK_ANIMATION_ID, BlockBreakAnimation.class);
		registry.register(NetworkState.PLAY, ClientBoundPacket.PLAY_EXPLOSION_ID, Explosion.class);
		registry.register(NetworkState.PLAY, ClientBoundPacket.PLAY_WORLD_EVENT_ID, WorldEvent.class);
		registry.register(NetworkState.PLAY, ClientBoundPacket.PLAY_WORLD_SOUND_ID, WorldSound.class);
		registry.register(NetworkState.PLAY, ClientBoundPacket.PLAY_WORLD_CUSTOM_SOUND_ID, WorldCustomSound.class);
		registry.register(NetworkState.PLAY, ClientBoundPacket.PLAY_WORLD_PARTICLES_ID, WorldParticle.class);
		registry.register(NetworkState.PLAY, ClientBoundPacket.PLAY_GAME_STATE_CHANGE_ID, GameStateChange.class);
		registry.register(NetworkState.PLAY, ClientBoundPacket.PLAY_SPAWN_WEATHER_ID, SpawnGlobal.class);
		registry.register(NetworkState.PLAY, ClientBoundPacket.PLAY_WINDOW_OPEN_ID, InventoryOpen.class);
		registry.register(NetworkState.PLAY, ClientBoundPacket.PLAY_WINDOW_CLOSE_ID, InventoryClose.class);
		registry.register(NetworkState.PLAY, ClientBoundPacket.PLAY_WINDOW_SET_SLOT_ID, InventorySetSlot.class);
		registry.register(NetworkState.PLAY, ClientBoundPacket.PLAY_WINDOW_SET_ITEMS_ID, InventorySetItems.class);
		registry.register(NetworkState.PLAY, ClientBoundPacket.PLAY_WINDOW_DATA_ID, InventoryData.class);
		registry.register(NetworkState.PLAY, ClientBoundPacket.PLAY_WINDOW_TRANSACTION_ID, InventoryConfirmTransaction.class);
		registry.register(NetworkState.PLAY, ClientBoundPacket.PLAY_MAP_ID, Map.class);
		registry.register(NetworkState.PLAY, ClientBoundPacket.PLAY_UPDATE_TILE_ID, BlockTileUpdate.class);
		registry.register(NetworkState.PLAY, ClientBoundPacket.PLAY_SIGN_EDITOR_ID, BlockOpenSignEditor.class);
		registry.register(NetworkState.PLAY, ClientBoundPacket.PLAY_PLAYER_INFO_ID, PlayerListSetEntry.class);
		registry.register(NetworkState.PLAY, ClientBoundPacket.PLAY_ABILITIES_ID, PlayerAbilities.class);
		registry.register(NetworkState.PLAY, ClientBoundPacket.PLAY_TAB_COMPLETE_ID, TabComplete.class);
		registry.register(NetworkState.PLAY, ClientBoundPacket.PLAY_SCOREBOARD_OBJECTIVE_ID, ScoreboardObjective.class);
		registry.register(NetworkState.PLAY, ClientBoundPacket.PLAY_SCOREBOARD_SCORE_ID, ScoreboardScore.class);
		registry.register(NetworkState.PLAY, ClientBoundPacket.PLAY_SCOREBOARD_DISPLAY_SLOT_ID, ScoreboardDisplay.class);
		registry.register(NetworkState.PLAY, ClientBoundPacket.PLAY_SCOREBOARD_TEAM_ID, ScoreboardTeam.class);
		registry.register(NetworkState.PLAY, ClientBoundPacket.PLAY_CUSTOM_PAYLOAD_ID, CustomPayload.class);
		registry.register(NetworkState.PLAY, ClientBoundPacket.PLAY_RESOURCE_PACK_ID, ResourcePack.class);
		registry.register(NetworkState.PLAY, ClientBoundPacket.PLAY_KICK_DISCONNECT_ID, KickDisconnect.class);
		registry.register(NetworkState.PLAY, ClientBoundPacket.PLAY_CAMERA_ID, Camera.class);
		registry.register(NetworkState.PLAY, ClientBoundPacket.PLAY_PLAYER_LIST_HEADER_FOOTER_ID, PlayerListHeaderFooter.class);
		registry.register(NetworkState.PLAY, ClientBoundPacket.PLAY_SET_PASSENGERS_ID, VehiclePassengers.class);
		registry.register(NetworkState.PLAY, ClientBoundPacket.PLAY_TITLE_ID, Title.class);
		registry.register(NetworkState.PLAY, ClientBoundPacket.PLAY_WORLD_BORDER_ID, WorldBorder.class);
		registry.register(NetworkState.PLAY, ClientBoundPacket.PLAY_CHUNK_UNLOAD_ID, UnloadChunk.class);
		registry.register(NetworkState.PLAY, ClientBoundPacket.PLAY_SERVER_DIFFICULTY_ID, ServerDifficulty.class);
		registry.register(NetworkState.PLAY, ClientBoundPacket.PLAY_COMBAT_EVENT_ID, CombatEvent.class);
		registry.register(NetworkState.PLAY, ClientBoundPacket.PLAY_SET_COOLDOWN_ID, NoopSetCooldown.class);
		registry.register(NetworkState.PLAY, ClientBoundPacket.PLAY_BOSS_BAR_ID, NoopBossBar.class);
		registry.register(NetworkState.PLAY, ClientBoundPacket.PLAY_VEHICLE_MOVE_ID, NoopVehicleMove.class);
		registry.register(NetworkState.PLAY, ClientBoundPacket.PLAY_UNLOCK_RECIPES, NoopUnlockRecipes.class);
		registry.register(NetworkState.PLAY, ClientBoundPacket.PLAY_ADVANCEMENTS, NoopAdvancements.class);
		registry.register(NetworkState.PLAY, ClientBoundPacket.PLAY_ADVANCEMENTS_TAB, NoopAdvanementsTab.class);
		registry.register(NetworkState.PLAY, ClientBoundPacket.PLAY_CRAFTING_GRID_CONFIRM, NoopCraftingGridConfirm.class);
		registry.register(NetworkState.PLAY, ClientBoundPacket.PLAY_STATISTICS_ID, NoopStatistics.class);
		registry.register(NetworkState.PLAY, ClientBoundPacket.PLAY_DECLARE_COMMANDS, NoopDeclareCommands.class);
		registry.register(NetworkState.PLAY, ClientBoundPacket.PLAY_DECLARE_RECIPES, NoopDeclareRecipes.class);
		registry.register(NetworkState.PLAY, ClientBoundPacket.PLAY_DECLARE_TAGS, NoopDeclareTags.class);
	}

	protected final ChunkSendIntervalPacketQueue chunkqueue = new ChunkSendIntervalPacketQueue();

	public PacketEncoder(Connection connection, NetworkDataCache storage) {
		super(connection, storage);
	}

	@Override
	protected RecyclableCollection<ClientBoundPacketData> processPackets(Channel channel, RecyclableCollection<ClientBoundPacketData> data) {
		RecyclableCollection<ClientBoundPacketData> allowed = chunkqueue.processPackets(data);
		long delay = chunkqueue.getUnlockDelay();
		if (delay != -1) {
			channel.eventLoop().schedule(
				() -> {
					chunkqueue.unlock();
					connection.sendPacket(ServerPlatform.get().getPacketFactory().createEmptyCustomPayloadPacket("pushqueue"));
				},
				delay, TimeUnit.NANOSECONDS
			);
		}
		return allowed;
	}

	@Override
	public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
		super.handlerRemoved(ctx);
		chunkqueue.release();
	}

}
