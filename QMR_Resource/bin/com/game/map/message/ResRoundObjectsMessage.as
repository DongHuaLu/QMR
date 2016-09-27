package com.game.map.message{
	import com.game.map.bean.EffectInfo;
	import com.game.map.bean.PetInfo;
	import com.game.utils.long;
	import com.game.map.bean.MonsterInfo;
	import com.game.map.bean.PlayerInfo;
	import com.game.map.bean.NpcInfo;
	import com.game.map.bean.DropGoodsInfo;
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 周围情况变化
	 */
	public class ResRoundObjectsMessage extends Message {
	
		//周围玩家信息
		private var _player: Vector.<PlayerInfo> = new Vector.<PlayerInfo>();
		//消失角色列表
		private var _playerIds: Vector.<long> = new Vector.<long>();
		//周围怪物信息
		private var _monster: Vector.<MonsterInfo> = new Vector.<MonsterInfo>();
		//消失怪物列表
		private var _monstersIds: Vector.<long> = new Vector.<long>();
		//周围掉落物品信息
		private var _goods: Vector.<DropGoodsInfo> = new Vector.<DropGoodsInfo>();
		//消失物品列表
		private var _goodsIds: Vector.<long> = new Vector.<long>();
		//周围宠物信息
		private var _pets: Vector.<PetInfo> = new Vector.<PetInfo>();
		//消失物品列表
		private var _petIds: Vector.<long> = new Vector.<long>();
		//NPC信息
		private var _npcs: Vector.<NpcInfo> = new Vector.<NpcInfo>();
		//消失的
		private var _npcid: Vector.<long> = new Vector.<long>();
		//周围效果信息
		private var _Effect: Vector.<EffectInfo> = new Vector.<EffectInfo>();
		//消失的效果信息
		private var _effectid: Vector.<long> = new Vector.<long>();
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			var i: int = 0;
			//周围玩家信息
			writeShort(_player.length);
			for (i = 0; i < _player.length; i++) {
				writeBean(_player[i]);
			}
			//消失角色列表
			writeShort(_playerIds.length);
			for (i = 0; i < _playerIds.length; i++) {
				writeLong(_playerIds[i]);
			}
			//周围怪物信息
			writeShort(_monster.length);
			for (i = 0; i < _monster.length; i++) {
				writeBean(_monster[i]);
			}
			//消失怪物列表
			writeShort(_monstersIds.length);
			for (i = 0; i < _monstersIds.length; i++) {
				writeLong(_monstersIds[i]);
			}
			//周围掉落物品信息
			writeShort(_goods.length);
			for (i = 0; i < _goods.length; i++) {
				writeBean(_goods[i]);
			}
			//消失物品列表
			writeShort(_goodsIds.length);
			for (i = 0; i < _goodsIds.length; i++) {
				writeLong(_goodsIds[i]);
			}
			//周围宠物信息
			writeShort(_pets.length);
			for (i = 0; i < _pets.length; i++) {
				writeBean(_pets[i]);
			}
			//消失物品列表
			writeShort(_petIds.length);
			for (i = 0; i < _petIds.length; i++) {
				writeLong(_petIds[i]);
			}
			//NPC信息
			writeShort(_npcs.length);
			for (i = 0; i < _npcs.length; i++) {
				writeBean(_npcs[i]);
			}
			//消失的
			writeShort(_npcid.length);
			for (i = 0; i < _npcid.length; i++) {
				writeLong(_npcid[i]);
			}
			//周围效果信息
			writeShort(_Effect.length);
			for (i = 0; i < _Effect.length; i++) {
				writeBean(_Effect[i]);
			}
			//消失的效果信息
			writeShort(_effectid.length);
			for (i = 0; i < _effectid.length; i++) {
				writeLong(_effectid[i]);
			}
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			var i: int = 0;
			//周围玩家信息
			var player_length : int = readShort();
			for (i = 0; i < player_length; i++) {
				_player[i] = readBean(PlayerInfo) as PlayerInfo;
			}
			//消失角色列表
			var playerIds_length : int = readShort();
			for (i = 0; i < playerIds_length; i++) {
				_playerIds[i] = readLong();
			}
			//周围怪物信息
			var monster_length : int = readShort();
			for (i = 0; i < monster_length; i++) {
				_monster[i] = readBean(MonsterInfo) as MonsterInfo;
			}
			//消失怪物列表
			var monstersIds_length : int = readShort();
			for (i = 0; i < monstersIds_length; i++) {
				_monstersIds[i] = readLong();
			}
			//周围掉落物品信息
			var goods_length : int = readShort();
			for (i = 0; i < goods_length; i++) {
				_goods[i] = readBean(DropGoodsInfo) as DropGoodsInfo;
			}
			//消失物品列表
			var goodsIds_length : int = readShort();
			for (i = 0; i < goodsIds_length; i++) {
				_goodsIds[i] = readLong();
			}
			//周围宠物信息
			var pets_length : int = readShort();
			for (i = 0; i < pets_length; i++) {
				_pets[i] = readBean(PetInfo) as PetInfo;
			}
			//消失物品列表
			var petIds_length : int = readShort();
			for (i = 0; i < petIds_length; i++) {
				_petIds[i] = readLong();
			}
			//NPC信息
			var npcs_length : int = readShort();
			for (i = 0; i < npcs_length; i++) {
				_npcs[i] = readBean(NpcInfo) as NpcInfo;
			}
			//消失的
			var npcid_length : int = readShort();
			for (i = 0; i < npcid_length; i++) {
				_npcid[i] = readLong();
			}
			//周围效果信息
			var Effect_length : int = readShort();
			for (i = 0; i < Effect_length; i++) {
				_Effect[i] = readBean(EffectInfo) as EffectInfo;
			}
			//消失的效果信息
			var effectid_length : int = readShort();
			for (i = 0; i < effectid_length; i++) {
				_effectid[i] = readLong();
			}
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 101125;
		}
		
		/**
		 * get 周围玩家信息
		 * @return 
		 */
		public function get player(): Vector.<PlayerInfo>{
			return _player;
		}
		
		/**
		 * set 周围玩家信息
		 */
		public function set player(value: Vector.<PlayerInfo>): void{
			this._player = value;
		}
		
		/**
		 * get 消失角色列表
		 * @return 
		 */
		public function get playerIds(): Vector.<long>{
			return _playerIds;
		}
		
		/**
		 * set 消失角色列表
		 */
		public function set playerIds(value: Vector.<long>): void{
			this._playerIds = value;
		}
		
		/**
		 * get 周围怪物信息
		 * @return 
		 */
		public function get monster(): Vector.<MonsterInfo>{
			return _monster;
		}
		
		/**
		 * set 周围怪物信息
		 */
		public function set monster(value: Vector.<MonsterInfo>): void{
			this._monster = value;
		}
		
		/**
		 * get 消失怪物列表
		 * @return 
		 */
		public function get monstersIds(): Vector.<long>{
			return _monstersIds;
		}
		
		/**
		 * set 消失怪物列表
		 */
		public function set monstersIds(value: Vector.<long>): void{
			this._monstersIds = value;
		}
		
		/**
		 * get 周围掉落物品信息
		 * @return 
		 */
		public function get goods(): Vector.<DropGoodsInfo>{
			return _goods;
		}
		
		/**
		 * set 周围掉落物品信息
		 */
		public function set goods(value: Vector.<DropGoodsInfo>): void{
			this._goods = value;
		}
		
		/**
		 * get 消失物品列表
		 * @return 
		 */
		public function get goodsIds(): Vector.<long>{
			return _goodsIds;
		}
		
		/**
		 * set 消失物品列表
		 */
		public function set goodsIds(value: Vector.<long>): void{
			this._goodsIds = value;
		}
		
		/**
		 * get 周围宠物信息
		 * @return 
		 */
		public function get pets(): Vector.<PetInfo>{
			return _pets;
		}
		
		/**
		 * set 周围宠物信息
		 */
		public function set pets(value: Vector.<PetInfo>): void{
			this._pets = value;
		}
		
		/**
		 * get 消失物品列表
		 * @return 
		 */
		public function get petIds(): Vector.<long>{
			return _petIds;
		}
		
		/**
		 * set 消失物品列表
		 */
		public function set petIds(value: Vector.<long>): void{
			this._petIds = value;
		}
		
		/**
		 * get NPC信息
		 * @return 
		 */
		public function get npcs(): Vector.<NpcInfo>{
			return _npcs;
		}
		
		/**
		 * set NPC信息
		 */
		public function set npcs(value: Vector.<NpcInfo>): void{
			this._npcs = value;
		}
		
		/**
		 * get 消失的
		 * @return 
		 */
		public function get npcid(): Vector.<long>{
			return _npcid;
		}
		
		/**
		 * set 消失的
		 */
		public function set npcid(value: Vector.<long>): void{
			this._npcid = value;
		}
		
		/**
		 * get 周围效果信息
		 * @return 
		 */
		public function get Effect(): Vector.<EffectInfo>{
			return _Effect;
		}
		
		/**
		 * set 周围效果信息
		 */
		public function set Effect(value: Vector.<EffectInfo>): void{
			this._Effect = value;
		}
		
		/**
		 * get 消失的效果信息
		 * @return 
		 */
		public function get effectid(): Vector.<long>{
			return _effectid;
		}
		
		/**
		 * set 消失的效果信息
		 */
		public function set effectid(value: Vector.<long>): void{
			this._effectid = value;
		}
		
	}
}