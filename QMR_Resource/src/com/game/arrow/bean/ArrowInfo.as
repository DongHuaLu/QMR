package com.game.arrow.bean{
	import com.game.arrow.bean.StarInfo;
	import com.game.arrow.bean.BowInfo;
	import com.game.arrow.bean.FightSpiritInfo;
	import net.Bean;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 弓箭信息
	 */
	public class ArrowInfo extends Bean {
	
		//弓箭等阶
		private var _arrowlv: int;
		
		//星斗信息
		private var _starinfo: StarInfo;
		
		//箭支信息
		private var _bowinfo: BowInfo;
		
		//战魂信息列表
		private var _fightspiritList: Vector.<FightSpiritInfo> = new Vector.<FightSpiritInfo>();
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//弓箭等阶
			writeInt(_arrowlv);
			//星斗信息
			writeBean(_starinfo);
			//箭支信息
			writeBean(_bowinfo);
			//战魂信息列表
			writeShort(_fightspiritList.length);
			for (var i: int = 0; i < _fightspiritList.length; i++) {
				writeBean(_fightspiritList[i]);
			}
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//弓箭等阶
			_arrowlv = readInt();
			//星斗信息
			_starinfo = readBean(StarInfo) as StarInfo;
			//箭支信息
			_bowinfo = readBean(BowInfo) as BowInfo;
			//战魂信息列表
			var fightspiritList_length : int = readShort();
			for (var i: int = 0; i < fightspiritList_length; i++) {
				_fightspiritList[i] = readBean(FightSpiritInfo) as FightSpiritInfo;
			}
			return true;
		}
		
		/**
		 * get 弓箭等阶
		 * @return 
		 */
		public function get arrowlv(): int{
			return _arrowlv;
		}
		
		/**
		 * set 弓箭等阶
		 */
		public function set arrowlv(value: int): void{
			this._arrowlv = value;
		}
		
		/**
		 * get 星斗信息
		 * @return 
		 */
		public function get starinfo(): StarInfo{
			return _starinfo;
		}
		
		/**
		 * set 星斗信息
		 */
		public function set starinfo(value: StarInfo): void{
			this._starinfo = value;
		}
		
		/**
		 * get 箭支信息
		 * @return 
		 */
		public function get bowinfo(): BowInfo{
			return _bowinfo;
		}
		
		/**
		 * set 箭支信息
		 */
		public function set bowinfo(value: BowInfo): void{
			this._bowinfo = value;
		}
		
		/**
		 * get 战魂信息列表
		 * @return 
		 */
		public function get fightspiritList(): Vector.<FightSpiritInfo>{
			return _fightspiritList;
		}
		
		/**
		 * set 战魂信息列表
		 */
		public function set fightspiritList(value: Vector.<FightSpiritInfo>): void{
			this._fightspiritList = value;
		}
		
	}
}