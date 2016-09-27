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
	 * 幸运连珠（使用元宝）
	 */
	public class ReshorseLuckyPenteMessage extends Message {
	
		//已使用连珠和拉杆总次数
		private var _num: int;
		
		//单个坐骑技能
		private var _skillinfo: HorseSkillInfo;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//已使用连珠和拉杆总次数
			writeByte(_num);
			//单个坐骑技能
			writeBean(_skillinfo);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//已使用连珠和拉杆总次数
			_num = readByte();
			//单个坐骑技能
			_skillinfo = readBean(HorseSkillInfo) as HorseSkillInfo;
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 126107;
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
		 * get 单个坐骑技能
		 * @return 
		 */
		public function get skillinfo(): HorseSkillInfo{
			return _skillinfo;
		}
		
		/**
		 * set 单个坐骑技能
		 */
		public function set skillinfo(value: HorseSkillInfo): void{
			this._skillinfo = value;
		}
		
	}
}