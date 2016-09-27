package com.game.amulet.message{
	import com.game.utils.long;
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 护身符信息,发送给周围玩家的
	 */
	public class ResAmuletInfoOtherMessage extends Message {
	
		//角色ID
		private var _playerid: long;
		
		//护身符信息
		private var _model: int;
		
		//配偶的护身符model
		private var _spouseModel: int;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//角色ID
			writeLong(_playerid);
			//护身符信息
			writeInt(_model);
			//配偶的护身符model
			writeInt(_spouseModel);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//角色ID
			_playerid = readLong();
			//护身符信息
			_model = readInt();
			//配偶的护身符model
			_spouseModel = readInt();
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 166104;
		}
		
		/**
		 * get 角色ID
		 * @return 
		 */
		public function get playerid(): long{
			return _playerid;
		}
		
		/**
		 * set 角色ID
		 */
		public function set playerid(value: long): void{
			this._playerid = value;
		}
		
		/**
		 * get 护身符信息
		 * @return 
		 */
		public function get model(): int{
			return _model;
		}
		
		/**
		 * set 护身符信息
		 */
		public function set model(value: int): void{
			this._model = value;
		}
		
		/**
		 * get 配偶的护身符model
		 * @return 
		 */
		public function get spouseModel(): int{
			return _spouseModel;
		}
		
		/**
		 * set 配偶的护身符model
		 */
		public function set spouseModel(value: int): void{
			this._spouseModel = value;
		}
		
	}
}