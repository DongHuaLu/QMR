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
	 * 发送坐骑技能列表
	 */
	public class ResSkillInfoMessage extends Message {
	
		//坐骑技能列表
		private var _skillinfolist: Vector.<HorseSkillInfo> = new Vector.<HorseSkillInfo>();
		//拉杆需要的金币
		private var _money: int;
		
		//连珠需要的元宝
		private var _yuanbao: int;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			var i: int = 0;
			//坐骑技能列表
			writeShort(_skillinfolist.length);
			for (i = 0; i < _skillinfolist.length; i++) {
				writeBean(_skillinfolist[i]);
			}
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
			var i: int = 0;
			//坐骑技能列表
			var skillinfolist_length : int = readShort();
			for (i = 0; i < skillinfolist_length; i++) {
				_skillinfolist[i] = readBean(HorseSkillInfo) as HorseSkillInfo;
			}
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
			return 126114;
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