package com.game.horse.message{
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 打开技能升级面板消息
	 */
	public class ResOpenSkillUpPanelMessage extends Message {
	
		//已使用连珠和拉杆次数
		private var _boxnum: int;
		
		//拉杆需要的金币
		private var _money: int;
		
		//连珠需要的元宝
		private var _yuanbao: int;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//已使用连珠和拉杆次数
			writeByte(_boxnum);
			//拉杆需要的金币
			writeInt(_money);
			//连珠需要的元宝
			writeInt(_yuanbao);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//已使用连珠和拉杆次数
			_boxnum = readByte();
			//拉杆需要的金币
			_money = readInt();
			//连珠需要的元宝
			_yuanbao = readInt();
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 126113;
		}
		
		/**
		 * get 已使用连珠和拉杆次数
		 * @return 
		 */
		public function get boxnum(): int{
			return _boxnum;
		}
		
		/**
		 * set 已使用连珠和拉杆次数
		 */
		public function set boxnum(value: int): void{
			this._boxnum = value;
		}
		
		/**
		 * get 拉杆需要的金币
		 * @return 
		 */
		public function get money(): int{
			return _money;
		}
		
		/**
		 * set 拉杆需要的金币
		 */
		public function set money(value: int): void{
			this._money = value;
		}
		
		/**
		 * get 连珠需要的元宝
		 * @return 
		 */
		public function get yuanbao(): int{
			return _yuanbao;
		}
		
		/**
		 * set 连珠需要的元宝
		 */
		public function set yuanbao(value: int): void{
			this._yuanbao = value;
		}
		
	}
}