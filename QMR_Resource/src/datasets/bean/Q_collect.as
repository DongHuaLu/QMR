package datasets.bean{
	import com.game.message.Bean;
	
	/** 
	 * @author ExcelUtil Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * Q_collect
	 */
	public class Q_collect extends Bean{
	
		//藏品ID
		private var _q_coll_id: int;
		
		//藏品类型
		private var _q_coll_type: int;
		
		//藏品名称
		private var _q_coll_name: String;
		
		//碎片1物品ID
		private var _q_frag1_id: int;
		
		//碎片1数量
		private var _q_frag1_num: int;
		
		//碎片1产出来源（支持HTML）
		private var _q_frag1_desc: String;
		
		//碎片2物品ID
		private var _q_frag2_id: int;
		
		//碎片2数量
		private var _q_frag2_num: int;
		
		//碎片2产出来源（支持HTML）
		private var _q_frag2_desc: String;
		
		//碎片3物品ID
		private var _q_frag3_id: int;
		
		//碎片3数量
		private var _q_frag3_num: int;
		
		//碎片3产出来源（支持HTML）
		private var _q_frag3_desc: String;
		
		//碎片4物品ID
		private var _q_frag4_id: int;
		
		//碎片4数量
		private var _q_frag4_num: int;
		
		//碎片4产出来源（支持HTML）
		private var _q_frag4_desc: String;
		
		//碎片5物品ID
		private var _q_frag5_id: int;
		
		//碎片5数量
		private var _q_frag5_num: int;
		
		//碎片5产出来源（支持HTML）
		private var _q_frag5_desc: String;
		
		//碎片6物品ID
		private var _q_frag6_id: int;
		
		//碎片6数量
		private var _q_frag6_num: int;
		
		//碎片6产出来源（支持HTML）
		private var _q_frag6_desc: String;
		
		//碎片7物品ID
		private var _q_frag7_id: int;
		
		//碎片7数量
		private var _q_frag7_num: int;
		
		//碎片7产出来源（支持HTML）
		private var _q_frag7_desc: String;
		
		//碎片8物品ID
		private var _q_frag8_id: int;
		
		//碎片8数量
		private var _q_frag8_num: int;
		
		//碎片8产出来源（支持HTML）
		private var _q_frag8_desc: String;
		
		//碎片9物品ID
		private var _q_frag9_id: int;
		
		//碎片9数量
		private var _q_frag9_num: int;
		
		//碎片9产出来源（支持HTML）
		private var _q_frag9_desc: String;
		
		//碎片10物品ID
		private var _q_frag10_id: int;
		
		//碎片10数量
		private var _q_frag10_num: int;
		
		//碎片10产出来源（支持HTML）
		private var _q_frag10_desc: String;
		
		//激活BUFFID（BUFF1ID；BUFF2ID）
		private var _q_buff_id: int;
		
		//物品描述（支持html）
		private var _q_item_desc: String;
		
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//藏品ID
			_q_coll_id = readInt();
			//藏品类型
			_q_coll_type = readInt();
			//藏品名称
			_q_coll_name = readString();
			//碎片1物品ID
			_q_frag1_id = readInt();
			//碎片1数量
			_q_frag1_num = readInt();
			//碎片1产出来源（支持HTML）
			_q_frag1_desc = readString();
			//碎片2物品ID
			_q_frag2_id = readInt();
			//碎片2数量
			_q_frag2_num = readInt();
			//碎片2产出来源（支持HTML）
			_q_frag2_desc = readString();
			//碎片3物品ID
			_q_frag3_id = readInt();
			//碎片3数量
			_q_frag3_num = readInt();
			//碎片3产出来源（支持HTML）
			_q_frag3_desc = readString();
			//碎片4物品ID
			_q_frag4_id = readInt();
			//碎片4数量
			_q_frag4_num = readInt();
			//碎片4产出来源（支持HTML）
			_q_frag4_desc = readString();
			//碎片5物品ID
			_q_frag5_id = readInt();
			//碎片5数量
			_q_frag5_num = readInt();
			//碎片5产出来源（支持HTML）
			_q_frag5_desc = readString();
			//碎片6物品ID
			_q_frag6_id = readInt();
			//碎片6数量
			_q_frag6_num = readInt();
			//碎片6产出来源（支持HTML）
			_q_frag6_desc = readString();
			//碎片7物品ID
			_q_frag7_id = readInt();
			//碎片7数量
			_q_frag7_num = readInt();
			//碎片7产出来源（支持HTML）
			_q_frag7_desc = readString();
			//碎片8物品ID
			_q_frag8_id = readInt();
			//碎片8数量
			_q_frag8_num = readInt();
			//碎片8产出来源（支持HTML）
			_q_frag8_desc = readString();
			//碎片9物品ID
			_q_frag9_id = readInt();
			//碎片9数量
			_q_frag9_num = readInt();
			//碎片9产出来源（支持HTML）
			_q_frag9_desc = readString();
			//碎片10物品ID
			_q_frag10_id = readInt();
			//碎片10数量
			_q_frag10_num = readInt();
			//碎片10产出来源（支持HTML）
			_q_frag10_desc = readString();
			//激活BUFFID（BUFF1ID；BUFF2ID）
			_q_buff_id = readInt();
			//物品描述（支持html）
			_q_item_desc = readString();
			return true;
		}
		
		/**
		 * get 藏品ID
		 * @return 
		 */
		public function get q_coll_id(): int{
			return _q_coll_id;
		}
		
		/**
		 * set 藏品ID
		 */
		public function set q_coll_id(value: int): void{
			this._q_coll_id = value;
		}
		
		/**
		 * get 藏品类型
		 * @return 
		 */
		public function get q_coll_type(): int{
			return _q_coll_type;
		}
		
		/**
		 * set 藏品类型
		 */
		public function set q_coll_type(value: int): void{
			this._q_coll_type = value;
		}
		
		/**
		 * get 藏品名称
		 * @return 
		 */
		public function get q_coll_name(): String{
			return _q_coll_name;
		}
		
		/**
		 * set 藏品名称
		 */
		public function set q_coll_name(value: String): void{
			this._q_coll_name = value;
		}
		
		/**
		 * get 碎片1物品ID
		 * @return 
		 */
		public function get q_frag1_id(): int{
			return _q_frag1_id;
		}
		
		/**
		 * set 碎片1物品ID
		 */
		public function set q_frag1_id(value: int): void{
			this._q_frag1_id = value;
		}
		
		/**
		 * get 碎片1数量
		 * @return 
		 */
		public function get q_frag1_num(): int{
			return _q_frag1_num;
		}
		
		/**
		 * set 碎片1数量
		 */
		public function set q_frag1_num(value: int): void{
			this._q_frag1_num = value;
		}
		
		/**
		 * get 碎片1产出来源（支持HTML）
		 * @return 
		 */
		public function get q_frag1_desc(): String{
			return _q_frag1_desc;
		}
		
		/**
		 * set 碎片1产出来源（支持HTML）
		 */
		public function set q_frag1_desc(value: String): void{
			this._q_frag1_desc = value;
		}
		
		/**
		 * get 碎片2物品ID
		 * @return 
		 */
		public function get q_frag2_id(): int{
			return _q_frag2_id;
		}
		
		/**
		 * set 碎片2物品ID
		 */
		public function set q_frag2_id(value: int): void{
			this._q_frag2_id = value;
		}
		
		/**
		 * get 碎片2数量
		 * @return 
		 */
		public function get q_frag2_num(): int{
			return _q_frag2_num;
		}
		
		/**
		 * set 碎片2数量
		 */
		public function set q_frag2_num(value: int): void{
			this._q_frag2_num = value;
		}
		
		/**
		 * get 碎片2产出来源（支持HTML）
		 * @return 
		 */
		public function get q_frag2_desc(): String{
			return _q_frag2_desc;
		}
		
		/**
		 * set 碎片2产出来源（支持HTML）
		 */
		public function set q_frag2_desc(value: String): void{
			this._q_frag2_desc = value;
		}
		
		/**
		 * get 碎片3物品ID
		 * @return 
		 */
		public function get q_frag3_id(): int{
			return _q_frag3_id;
		}
		
		/**
		 * set 碎片3物品ID
		 */
		public function set q_frag3_id(value: int): void{
			this._q_frag3_id = value;
		}
		
		/**
		 * get 碎片3数量
		 * @return 
		 */
		public function get q_frag3_num(): int{
			return _q_frag3_num;
		}
		
		/**
		 * set 碎片3数量
		 */
		public function set q_frag3_num(value: int): void{
			this._q_frag3_num = value;
		}
		
		/**
		 * get 碎片3产出来源（支持HTML）
		 * @return 
		 */
		public function get q_frag3_desc(): String{
			return _q_frag3_desc;
		}
		
		/**
		 * set 碎片3产出来源（支持HTML）
		 */
		public function set q_frag3_desc(value: String): void{
			this._q_frag3_desc = value;
		}
		
		/**
		 * get 碎片4物品ID
		 * @return 
		 */
		public function get q_frag4_id(): int{
			return _q_frag4_id;
		}
		
		/**
		 * set 碎片4物品ID
		 */
		public function set q_frag4_id(value: int): void{
			this._q_frag4_id = value;
		}
		
		/**
		 * get 碎片4数量
		 * @return 
		 */
		public function get q_frag4_num(): int{
			return _q_frag4_num;
		}
		
		/**
		 * set 碎片4数量
		 */
		public function set q_frag4_num(value: int): void{
			this._q_frag4_num = value;
		}
		
		/**
		 * get 碎片4产出来源（支持HTML）
		 * @return 
		 */
		public function get q_frag4_desc(): String{
			return _q_frag4_desc;
		}
		
		/**
		 * set 碎片4产出来源（支持HTML）
		 */
		public function set q_frag4_desc(value: String): void{
			this._q_frag4_desc = value;
		}
		
		/**
		 * get 碎片5物品ID
		 * @return 
		 */
		public function get q_frag5_id(): int{
			return _q_frag5_id;
		}
		
		/**
		 * set 碎片5物品ID
		 */
		public function set q_frag5_id(value: int): void{
			this._q_frag5_id = value;
		}
		
		/**
		 * get 碎片5数量
		 * @return 
		 */
		public function get q_frag5_num(): int{
			return _q_frag5_num;
		}
		
		/**
		 * set 碎片5数量
		 */
		public function set q_frag5_num(value: int): void{
			this._q_frag5_num = value;
		}
		
		/**
		 * get 碎片5产出来源（支持HTML）
		 * @return 
		 */
		public function get q_frag5_desc(): String{
			return _q_frag5_desc;
		}
		
		/**
		 * set 碎片5产出来源（支持HTML）
		 */
		public function set q_frag5_desc(value: String): void{
			this._q_frag5_desc = value;
		}
		
		/**
		 * get 碎片6物品ID
		 * @return 
		 */
		public function get q_frag6_id(): int{
			return _q_frag6_id;
		}
		
		/**
		 * set 碎片6物品ID
		 */
		public function set q_frag6_id(value: int): void{
			this._q_frag6_id = value;
		}
		
		/**
		 * get 碎片6数量
		 * @return 
		 */
		public function get q_frag6_num(): int{
			return _q_frag6_num;
		}
		
		/**
		 * set 碎片6数量
		 */
		public function set q_frag6_num(value: int): void{
			this._q_frag6_num = value;
		}
		
		/**
		 * get 碎片6产出来源（支持HTML）
		 * @return 
		 */
		public function get q_frag6_desc(): String{
			return _q_frag6_desc;
		}
		
		/**
		 * set 碎片6产出来源（支持HTML）
		 */
		public function set q_frag6_desc(value: String): void{
			this._q_frag6_desc = value;
		}
		
		/**
		 * get 碎片7物品ID
		 * @return 
		 */
		public function get q_frag7_id(): int{
			return _q_frag7_id;
		}
		
		/**
		 * set 碎片7物品ID
		 */
		public function set q_frag7_id(value: int): void{
			this._q_frag7_id = value;
		}
		
		/**
		 * get 碎片7数量
		 * @return 
		 */
		public function get q_frag7_num(): int{
			return _q_frag7_num;
		}
		
		/**
		 * set 碎片7数量
		 */
		public function set q_frag7_num(value: int): void{
			this._q_frag7_num = value;
		}
		
		/**
		 * get 碎片7产出来源（支持HTML）
		 * @return 
		 */
		public function get q_frag7_desc(): String{
			return _q_frag7_desc;
		}
		
		/**
		 * set 碎片7产出来源（支持HTML）
		 */
		public function set q_frag7_desc(value: String): void{
			this._q_frag7_desc = value;
		}
		
		/**
		 * get 碎片8物品ID
		 * @return 
		 */
		public function get q_frag8_id(): int{
			return _q_frag8_id;
		}
		
		/**
		 * set 碎片8物品ID
		 */
		public function set q_frag8_id(value: int): void{
			this._q_frag8_id = value;
		}
		
		/**
		 * get 碎片8数量
		 * @return 
		 */
		public function get q_frag8_num(): int{
			return _q_frag8_num;
		}
		
		/**
		 * set 碎片8数量
		 */
		public function set q_frag8_num(value: int): void{
			this._q_frag8_num = value;
		}
		
		/**
		 * get 碎片8产出来源（支持HTML）
		 * @return 
		 */
		public function get q_frag8_desc(): String{
			return _q_frag8_desc;
		}
		
		/**
		 * set 碎片8产出来源（支持HTML）
		 */
		public function set q_frag8_desc(value: String): void{
			this._q_frag8_desc = value;
		}
		
		/**
		 * get 碎片9物品ID
		 * @return 
		 */
		public function get q_frag9_id(): int{
			return _q_frag9_id;
		}
		
		/**
		 * set 碎片9物品ID
		 */
		public function set q_frag9_id(value: int): void{
			this._q_frag9_id = value;
		}
		
		/**
		 * get 碎片9数量
		 * @return 
		 */
		public function get q_frag9_num(): int{
			return _q_frag9_num;
		}
		
		/**
		 * set 碎片9数量
		 */
		public function set q_frag9_num(value: int): void{
			this._q_frag9_num = value;
		}
		
		/**
		 * get 碎片9产出来源（支持HTML）
		 * @return 
		 */
		public function get q_frag9_desc(): String{
			return _q_frag9_desc;
		}
		
		/**
		 * set 碎片9产出来源（支持HTML）
		 */
		public function set q_frag9_desc(value: String): void{
			this._q_frag9_desc = value;
		}
		
		/**
		 * get 碎片10物品ID
		 * @return 
		 */
		public function get q_frag10_id(): int{
			return _q_frag10_id;
		}
		
		/**
		 * set 碎片10物品ID
		 */
		public function set q_frag10_id(value: int): void{
			this._q_frag10_id = value;
		}
		
		/**
		 * get 碎片10数量
		 * @return 
		 */
		public function get q_frag10_num(): int{
			return _q_frag10_num;
		}
		
		/**
		 * set 碎片10数量
		 */
		public function set q_frag10_num(value: int): void{
			this._q_frag10_num = value;
		}
		
		/**
		 * get 碎片10产出来源（支持HTML）
		 * @return 
		 */
		public function get q_frag10_desc(): String{
			return _q_frag10_desc;
		}
		
		/**
		 * set 碎片10产出来源（支持HTML）
		 */
		public function set q_frag10_desc(value: String): void{
			this._q_frag10_desc = value;
		}
		
		/**
		 * get 激活BUFFID（BUFF1ID；BUFF2ID）
		 * @return 
		 */
		public function get q_buff_id(): int{
			return _q_buff_id;
		}
		
		/**
		 * set 激活BUFFID（BUFF1ID；BUFF2ID）
		 */
		public function set q_buff_id(value: int): void{
			this._q_buff_id = value;
		}
		
		/**
		 * get 物品描述（支持html）
		 * @return 
		 */
		public function get q_item_desc(): String{
			return _q_item_desc;
		}
		
		/**
		 * set 物品描述（支持html）
		 */
		public function set q_item_desc(value: String): void{
			this._q_item_desc = value;
		}
		
	}
}