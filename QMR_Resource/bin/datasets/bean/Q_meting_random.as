package datasets.bean{
	import com.game.message.Bean;
	
	/** 
	 * @author ExcelUtil Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * Q_meting_random
	 */
	public class Q_meting_random extends Bean{
	
		//熔炼id(装备等级_属性类型)
		private var _q_metingid: String;
		
		//装备等级
		private var _q_itemlv: int;
		
		//属性类型
		private var _q_attrtype: int;
		
		//属性条数
		private var _q_num: int;
		
		//几率
		private var _q_random: int;
		
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//熔炼id(装备等级_属性类型)
			_q_metingid = readString();
			//装备等级
			_q_itemlv = readInt();
			//属性类型
			_q_attrtype = readInt();
			//属性条数
			_q_num = readInt();
			//几率
			_q_random = readInt();
			return true;
		}
		
		/**
		 * get 熔炼id(装备等级_属性类型)
		 * @return 
		 */
		public function get q_metingid(): String{
			return _q_metingid;
		}
		
		/**
		 * set 熔炼id(装备等级_属性类型)
		 */
		public function set q_metingid(value: String): void{
			this._q_metingid = value;
		}
		
		/**
		 * get 装备等级
		 * @return 
		 */
		public function get q_itemlv(): int{
			return _q_itemlv;
		}
		
		/**
		 * set 装备等级
		 */
		public function set q_itemlv(value: int): void{
			this._q_itemlv = value;
		}
		
		/**
		 * get 属性类型
		 * @return 
		 */
		public function get q_attrtype(): int{
			return _q_attrtype;
		}
		
		/**
		 * set 属性类型
		 */
		public function set q_attrtype(value: int): void{
			this._q_attrtype = value;
		}
		
		/**
		 * get 属性条数
		 * @return 
		 */
		public function get q_num(): int{
			return _q_num;
		}
		
		/**
		 * set 属性条数
		 */
		public function set q_num(value: int): void{
			this._q_num = value;
		}
		
		/**
		 * get 几率
		 * @return 
		 */
		public function get q_random(): int{
			return _q_random;
		}
		
		/**
		 * set 几率
		 */
		public function set q_random(value: int): void{
			this._q_random = value;
		}
		
	}
}