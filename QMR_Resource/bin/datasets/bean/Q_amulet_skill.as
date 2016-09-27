package datasets.bean{
	import com.game.message.Bean;
	
	/** 
	 * @author ExcelUtil Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * Q_amulet_skill
	 */
	public class Q_amulet_skill extends Bean{
	
		//关联物品id
		private var _q_item_id: int;
		
		//技能ID
		private var _q_skill_id: int;
		
		//技能等级
		private var _q_skill_level: int;
		
		//所需阶数
		private var _q_need_layer: int;
		
		//替换互斥概率（暂时不用）
		private var _q_prob: int;
		
		//重鉴技能书破碎几率(万分比)
		private var _q_broken_prob: int;
		
		//鉴定成功后随机抽取的技能书ID(技能ID_概率；技能ID_概率)
		private var _q_select_info: String;
		
		//鉴定失败后随机抽取的技能书ID(技能ID_概率；技能ID_概率)
		private var _q_fail_info: String;
		
		//技能书类型（1增加所有武学层数，0增加对应技能书武学层数）
		private var _q_type: int;
		
		//鉴定技能书所需铜币
		private var _q_coper: int;
		
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//关联物品id
			_q_item_id = readInt();
			//技能ID
			_q_skill_id = readInt();
			//技能等级
			_q_skill_level = readInt();
			//所需阶数
			_q_need_layer = readInt();
			//替换互斥概率（暂时不用）
			_q_prob = readInt();
			//重鉴技能书破碎几率(万分比)
			_q_broken_prob = readInt();
			//鉴定成功后随机抽取的技能书ID(技能ID_概率；技能ID_概率)
			_q_select_info = readString();
			//鉴定失败后随机抽取的技能书ID(技能ID_概率；技能ID_概率)
			_q_fail_info = readString();
			//技能书类型（1增加所有武学层数，0增加对应技能书武学层数）
			_q_type = readInt();
			//鉴定技能书所需铜币
			_q_coper = readInt();
			return true;
		}
		
		/**
		 * get 关联物品id
		 * @return 
		 */
		public function get q_item_id(): int{
			return _q_item_id;
		}
		
		/**
		 * set 关联物品id
		 */
		public function set q_item_id(value: int): void{
			this._q_item_id = value;
		}
		
		/**
		 * get 技能ID
		 * @return 
		 */
		public function get q_skill_id(): int{
			return _q_skill_id;
		}
		
		/**
		 * set 技能ID
		 */
		public function set q_skill_id(value: int): void{
			this._q_skill_id = value;
		}
		
		/**
		 * get 技能等级
		 * @return 
		 */
		public function get q_skill_level(): int{
			return _q_skill_level;
		}
		
		/**
		 * set 技能等级
		 */
		public function set q_skill_level(value: int): void{
			this._q_skill_level = value;
		}
		
		/**
		 * get 所需阶数
		 * @return 
		 */
		public function get q_need_layer(): int{
			return _q_need_layer;
		}
		
		/**
		 * set 所需阶数
		 */
		public function set q_need_layer(value: int): void{
			this._q_need_layer = value;
		}
		
		/**
		 * get 替换互斥概率（暂时不用）
		 * @return 
		 */
		public function get q_prob(): int{
			return _q_prob;
		}
		
		/**
		 * set 替换互斥概率（暂时不用）
		 */
		public function set q_prob(value: int): void{
			this._q_prob = value;
		}
		
		/**
		 * get 重鉴技能书破碎几率(万分比)
		 * @return 
		 */
		public function get q_broken_prob(): int{
			return _q_broken_prob;
		}
		
		/**
		 * set 重鉴技能书破碎几率(万分比)
		 */
		public function set q_broken_prob(value: int): void{
			this._q_broken_prob = value;
		}
		
		/**
		 * get 鉴定成功后随机抽取的技能书ID(技能ID_概率；技能ID_概率)
		 * @return 
		 */
		public function get q_select_info(): String{
			return _q_select_info;
		}
		
		/**
		 * set 鉴定成功后随机抽取的技能书ID(技能ID_概率；技能ID_概率)
		 */
		public function set q_select_info(value: String): void{
			this._q_select_info = value;
		}
		
		/**
		 * get 鉴定失败后随机抽取的技能书ID(技能ID_概率；技能ID_概率)
		 * @return 
		 */
		public function get q_fail_info(): String{
			return _q_fail_info;
		}
		
		/**
		 * set 鉴定失败后随机抽取的技能书ID(技能ID_概率；技能ID_概率)
		 */
		public function set q_fail_info(value: String): void{
			this._q_fail_info = value;
		}
		
		/**
		 * get 技能书类型（1增加所有武学层数，0增加对应技能书武学层数）
		 * @return 
		 */
		public function get q_type(): int{
			return _q_type;
		}
		
		/**
		 * set 技能书类型（1增加所有武学层数，0增加对应技能书武学层数）
		 */
		public function set q_type(value: int): void{
			this._q_type = value;
		}
		
		/**
		 * get 鉴定技能书所需铜币
		 * @return 
		 */
		public function get q_coper(): int{
			return _q_coper;
		}
		
		/**
		 * set 鉴定技能书所需铜币
		 */
		public function set q_coper(value: int): void{
			this._q_coper = value;
		}
		
	}
}