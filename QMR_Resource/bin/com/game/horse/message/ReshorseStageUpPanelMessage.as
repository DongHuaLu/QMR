package com.game.horse.message{
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 发送坐骑升阶面板信息
	 */
	public class ReshorseStageUpPanelMessage extends Message {
	
		//消耗金币
		private var _gold: int;
		
		//消耗元宝
		private var _yuanbao: int;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//消耗金币
			writeInt(_gold);
			//消耗元宝
			writeInt(_yuanbao);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//消耗金币
			_gold = readInt();
			//消耗元宝
			_yuanbao = readInt();
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 126104;
		}
		
		/**
		 * get 消耗金币
		 * @return 
		 */
		public function get gold(): int{
			return _gold;
		}
		
		/**
		 * set 消耗金币
		 */
		public function set gold(value: int): void{
			this._gold = value;
		}
		
		/**
		 * get 消耗元宝
		 * @return 
		 */
		public function get yuanbao(): int{
			return _yuanbao;
		}
		
		/**
		 * set 消耗元宝
		 */
		public function set yuanbao(value: int): void{
			this._yuanbao = value;
		}
		
	}
}