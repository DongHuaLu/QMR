package com.game.horse.message{
	import com.game.horse.bean.HorseSkillInfo;
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 幸运拉杆（使用金币）
	 */
	public class ReshorseLuckyRodMessage extends Message {
	
		//已使用连珠和拉杆总次数
		private var _num: int;
		
		//坐骑技能列表
		private var _skillinfolist: Vector.<HorseSkillInfo> = new Vector.<HorseSkillInfo>();
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			var i: int = 0;
			//已使用连珠和拉杆总次数
			writeByte(_num);
			//坐骑技能列表
			writeShort(_skillinfolist.length);
			for (i = 0; i < _skillinfolist.length; i++) {
				writeBean(_skillinfolist[i]);
			}
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			var i: int = 0;
			//已使用连珠和拉杆总次数
			_num = readByte();
			//坐骑技能列表
			var skillinfolist_length : int = readShort();
			for (i = 0; i < skillinfolist_length; i++) {
				_skillinfolist[i] = readBean(HorseSkillInfo) as HorseSkillInfo;
			}
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 126106;
		}
		
		/**
		 * get 已使用连珠和拉杆总次数
		 * @return 
		 */
		public function get num(): int{
			return _num;
		}
		
		/**
		 * set 已使用连珠和拉杆总次数
		 */
		public function set num(value: int): void{
			this._num = value;
		}
		
		/**
		 * get 坐骑技能列表
		 * @return 
		 */
		public function get skillinfolist(): Vector.<HorseSkillInfo>{
			return _skillinfolist;
		}
		
		/**
		 * set 坐骑技能列表
		 */
		public function set skillinfolist(value: Vector.<HorseSkillInfo>): void{
			this._skillinfolist = value;
		}
		
	}
}