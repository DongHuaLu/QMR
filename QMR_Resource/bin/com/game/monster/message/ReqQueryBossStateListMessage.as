package com.game.monster.message{
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 请求BOSS状态列表
	 */
	public class ReqQueryBossStateListMessage extends Message {
	
		//怪物模型列表
		private var _monsterModelId: Vector.<int> = new Vector.<int>();
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			var i: int = 0;
			//怪物模型列表
			writeShort(_monsterModelId.length);
			for (i = 0; i < _monsterModelId.length; i++) {
				writeInt(_monsterModelId[i]);
			}
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			var i: int = 0;
			//怪物模型列表
			var monsterModelId_length : int = readShort();
			for (i = 0; i < monsterModelId_length; i++) {
				_monsterModelId[i] = readInt();
			}
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 114203;
		}
		
		/**
		 * get 怪物模型列表
		 * @return 
		 */
		public function get monsterModelId(): Vector.<int>{
			return _monsterModelId;
		}
		
		/**
		 * set 怪物模型列表
		 */
		public function set monsterModelId(value: Vector.<int>): void{
			this._monsterModelId = value;
		}
		
	}
}