package com.game.horse.bean{
	import com.game.equip.bean.EquipInfo;
	import com.game.horse.bean.HorseSkillInfo;
	import net.Bean;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 坐骑信息
	 */
	public class HorseInfo extends Bean {
	
		//当前最高坐骑阶层
		private var _layer: int;
		
		//当前使用的坐骑阶层
		private var _curlayer: int;
		
		//是否骑乘，1骑乘，0未骑乘
		private var _status: int;
		
		//坐骑技能列表
		private var _skillinfolist: Vector.<HorseSkillInfo> = new Vector.<HorseSkillInfo>();
		//坐骑装备列表
		private var _horseequipinfo: Vector.<com.game.equip.bean.EquipInfo> = new Vector.<com.game.equip.bean.EquipInfo>();
		//当前祝福值
		private var _dayblessvalue: int;
		
		//历史祝福值
		private var _hisblessvalue: int;
		
		//当前进阶次数
		private var _dayupnum: int;
		
		//历史进阶次数
		private var _hisupnum: int;
		
		//已使用连珠和拉杆总次数
		private var _boxnum: int;
		
		//拉杆CD剩余时间（秒）
		private var _boxcdtime: int;
		
		//清除CD需要的元宝数量
		private var _cdtimeyuanbao: int;
		
		//潜能点
		private var _potential: int;
		
		//坐骑锻骨草使用数量
		private var _horseduangu: int;
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//当前最高坐骑阶层
			writeShort(_layer);
			//当前使用的坐骑阶层
			writeShort(_curlayer);
			//是否骑乘，1骑乘，0未骑乘
			writeByte(_status);
			//坐骑技能列表
			writeShort(_skillinfolist.length);
			for (var i: int = 0; i < _skillinfolist.length; i++) {
				writeBean(_skillinfolist[i]);
			}
			//坐骑装备列表
			writeShort(_horseequipinfo.length);
			for (var i: int = 0; i < _horseequipinfo.length; i++) {
				writeBean(_horseequipinfo[i]);
			}
			//当前祝福值
			writeInt(_dayblessvalue);
			//历史祝福值
			writeInt(_hisblessvalue);
			//当前进阶次数
			writeShort(_dayupnum);
			//历史进阶次数
			writeShort(_hisupnum);
			//已使用连珠和拉杆总次数
			writeByte(_boxnum);
			//拉杆CD剩余时间（秒）
			writeInt(_boxcdtime);
			//清除CD需要的元宝数量
			writeInt(_cdtimeyuanbao);
			//潜能点
			writeInt(_potential);
			//坐骑锻骨草使用数量
			writeShort(_horseduangu);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//当前最高坐骑阶层
			_layer = readShort();
			//当前使用的坐骑阶层
			_curlayer = readShort();
			//是否骑乘，1骑乘，0未骑乘
			_status = readByte();
			//坐骑技能列表
			var skillinfolist_length : int = readShort();
			for (var i: int = 0; i < skillinfolist_length; i++) {
				_skillinfolist[i] = readBean(HorseSkillInfo) as HorseSkillInfo;
			}
			//坐骑装备列表
			var horseequipinfo_length : int = readShort();
			for (var i: int = 0; i < horseequipinfo_length; i++) {
				_horseequipinfo[i] = readBean(com.game.equip.bean.EquipInfo) as com.game.equip.bean.EquipInfo;
			}
			//当前祝福值
			_dayblessvalue = readInt();
			//历史祝福值
			_hisblessvalue = readInt();
			//当前进阶次数
			_dayupnum = readShort();
			//历史进阶次数
			_hisupnum = readShort();
			//已使用连珠和拉杆总次数
			_boxnum = readByte();
			//拉杆CD剩余时间（秒）
			_boxcdtime = readInt();
			//清除CD需要的元宝数量
			_cdtimeyuanbao = readInt();
			//潜能点
			_potential = readInt();
			//坐骑锻骨草使用数量
			_horseduangu = readShort();
			return true;
		}
		
		/**
		 * get 当前最高坐骑阶层
		 * @return 
		 */
		public function get layer(): int{
			return _layer;
		}
		
		/**
		 * set 当前最高坐骑阶层
		 */
		public function set layer(value: int): void{
			this._layer = value;
		}
		
		/**
		 * get 当前使用的坐骑阶层
		 * @return 
		 */
		public function get curlayer(): int{
			return _curlayer;
		}
		
		/**
		 * set 当前使用的坐骑阶层
		 */
		public function set curlayer(value: int): void{
			this._curlayer = value;
		}
		
		/**
		 * get 是否骑乘，1骑乘，0未骑乘
		 * @return 
		 */
		public function get status(): int{
			return _status;
		}
		
		/**
		 * set 是否骑乘，1骑乘，0未骑乘
		 */
		public function set status(value: int): void{
			this._status = value;
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
		 * get 坐骑装备列表
		 * @return 
		 */
		public function get horseequipinfo(): Vector.<com.game.equip.bean.EquipInfo>{
			return _horseequipinfo;
		}
		
		/**
		 * set 坐骑装备列表
		 */
		public function set horseequipinfo(value: Vector.<com.game.equip.bean.EquipInfo>): void{
			this._horseequipinfo = value;
		}
		
		/**
		 * get 当前祝福值
		 * @return 
		 */
		public function get dayblessvalue(): int{
			return _dayblessvalue;
		}
		
		/**
		 * set 当前祝福值
		 */
		public function set dayblessvalue(value: int): void{
			this._dayblessvalue = value;
		}
		
		/**
		 * get 历史祝福值
		 * @return 
		 */
		public function get hisblessvalue(): int{
			return _hisblessvalue;
		}
		
		/**
		 * set 历史祝福值
		 */
		public function set hisblessvalue(value: int): void{
			this._hisblessvalue = value;
		}
		
		/**
		 * get 当前进阶次数
		 * @return 
		 */
		public function get dayupnum(): int{
			return _dayupnum;
		}
		
		/**
		 * set 当前进阶次数
		 */
		public function set dayupnum(value: int): void{
			this._dayupnum = value;
		}
		
		/**
		 * get 历史进阶次数
		 * @return 
		 */
		public function get hisupnum(): int{
			return _hisupnum;
		}
		
		/**
		 * set 历史进阶次数
		 */
		public function set hisupnum(value: int): void{
			this._hisupnum = value;
		}
		
		/**
		 * get 已使用连珠和拉杆总次数
		 * @return 
		 */
		public function get boxnum(): int{
			return _boxnum;
		}
		
		/**
		 * set 已使用连珠和拉杆总次数
		 */
		public function set boxnum(value: int): void{
			this._boxnum = value;
		}
		
		/**
		 * get 拉杆CD剩余时间（秒）
		 * @return 
		 */
		public function get boxcdtime(): int{
			return _boxcdtime;
		}
		
		/**
		 * set 拉杆CD剩余时间（秒）
		 */
		public function set boxcdtime(value: int): void{
			this._boxcdtime = value;
		}
		
		/**
		 * get 清除CD需要的元宝数量
		 * @return 
		 */
		public function get cdtimeyuanbao(): int{
			return _cdtimeyuanbao;
		}
		
		/**
		 * set 清除CD需要的元宝数量
		 */
		public function set cdtimeyuanbao(value: int): void{
			this._cdtimeyuanbao = value;
		}
		
		/**
		 * get 潜能点
		 * @return 
		 */
		public function get potential(): int{
			return _potential;
		}
		
		/**
		 * set 潜能点
		 */
		public function set potential(value: int): void{
			this._potential = value;
		}
		
		/**
		 * get 坐骑锻骨草使用数量
		 * @return 
		 */
		public function get horseduangu(): int{
			return _horseduangu;
		}
		
		/**
		 * set 坐骑锻骨草使用数量
		 */
		public function set horseduangu(value: int): void{
			this._horseduangu = value;
		}
		
	}
}