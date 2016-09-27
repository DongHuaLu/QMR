package com.game.plugset.bean{
	import net.Bean;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 自动挂机设置内容
	 */
	public class PlugSetInfo extends Bean {
	
		//总设置参数
		private var _parameter: int;
		
		//血量百分比
		private var _hpper: int;
		
		//内力百分比
		private var _mpper: int;
		
		//体力百分比
		private var _spper: int;
		
		//自动出售道具颜色类型
		private var _itemcolor: int;
		
		//自动出售道具强化等级
		private var _itemlv: int;
		
		//自动打怪技能
		private var _skillid: int;
		
		//挂机范围
		private var _range: int;
		
		//拾取道具类型
		private var _pickup: int;
		
		//挂机时间 （分钟）
		private var _time: int;
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//总设置参数
			writeInt(_parameter);
			//血量百分比
			writeByte(_hpper);
			//内力百分比
			writeByte(_mpper);
			//体力百分比
			writeByte(_spper);
			//自动出售道具颜色类型
			writeByte(_itemcolor);
			//自动出售道具强化等级
			writeByte(_itemlv);
			//自动打怪技能
			writeInt(_skillid);
			//挂机范围
			writeByte(_range);
			//拾取道具类型
			writeByte(_pickup);
			//挂机时间 （分钟）
			writeInt(_time);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//总设置参数
			_parameter = readInt();
			//血量百分比
			_hpper = readByte();
			//内力百分比
			_mpper = readByte();
			//体力百分比
			_spper = readByte();
			//自动出售道具颜色类型
			_itemcolor = readByte();
			//自动出售道具强化等级
			_itemlv = readByte();
			//自动打怪技能
			_skillid = readInt();
			//挂机范围
			_range = readByte();
			//拾取道具类型
			_pickup = readByte();
			//挂机时间 （分钟）
			_time = readInt();
			return true;
		}
		
		/**
		 * get 总设置参数
		 * @return 
		 */
		public function get parameter(): int{
			return _parameter;
		}
		
		/**
		 * set 总设置参数
		 */
		public function set parameter(value: int): void{
			this._parameter = value;
		}
		
		/**
		 * get 血量百分比
		 * @return 
		 */
		public function get hpper(): int{
			return _hpper;
		}
		
		/**
		 * set 血量百分比
		 */
		public function set hpper(value: int): void{
			this._hpper = value;
		}
		
		/**
		 * get 内力百分比
		 * @return 
		 */
		public function get mpper(): int{
			return _mpper;
		}
		
		/**
		 * set 内力百分比
		 */
		public function set mpper(value: int): void{
			this._mpper = value;
		}
		
		/**
		 * get 体力百分比
		 * @return 
		 */
		public function get spper(): int{
			return _spper;
		}
		
		/**
		 * set 体力百分比
		 */
		public function set spper(value: int): void{
			this._spper = value;
		}
		
		/**
		 * get 自动出售道具颜色类型
		 * @return 
		 */
		public function get itemcolor(): int{
			return _itemcolor;
		}
		
		/**
		 * set 自动出售道具颜色类型
		 */
		public function set itemcolor(value: int): void{
			this._itemcolor = value;
		}
		
		/**
		 * get 自动出售道具强化等级
		 * @return 
		 */
		public function get itemlv(): int{
			return _itemlv;
		}
		
		/**
		 * set 自动出售道具强化等级
		 */
		public function set itemlv(value: int): void{
			this._itemlv = value;
		}
		
		/**
		 * get 自动打怪技能
		 * @return 
		 */
		public function get skillid(): int{
			return _skillid;
		}
		
		/**
		 * set 自动打怪技能
		 */
		public function set skillid(value: int): void{
			this._skillid = value;
		}
		
		/**
		 * get 挂机范围
		 * @return 
		 */
		public function get range(): int{
			return _range;
		}
		
		/**
		 * set 挂机范围
		 */
		public function set range(value: int): void{
			this._range = value;
		}
		
		/**
		 * get 拾取道具类型
		 * @return 
		 */
		public function get pickup(): int{
			return _pickup;
		}
		
		/**
		 * set 拾取道具类型
		 */
		public function set pickup(value: int): void{
			this._pickup = value;
		}
		
		/**
		 * get 挂机时间 （分钟）
		 * @return 
		 */
		public function get time(): int{
			return _time;
		}
		
		/**
		 * set 挂机时间 （分钟）
		 */
		public function set time(value: int): void{
			this._time = value;
		}
		
	}
}