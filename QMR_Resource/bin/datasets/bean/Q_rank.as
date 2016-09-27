package datasets.bean{
	import com.game.message.Bean;
	
	/** 
	 * @author ExcelUtil Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * Q_rank
	 */
	public class Q_rank extends Bean{
	
		//军衔等级
		private var _q_ranklv: int;
		
		//军衔名称
		private var _q_rankname: String;
		
		//角色名颜色（填入色值程序直接调用）
		private var _q_name_reg: String;
		
		//需要军功值
		private var _q_ranknum: int;
		
		//是否需要服务器排名（0：不需要，数字N：表示排名前N名玩家有资格获得该军衔）
		private var _q_isranking: int;
		
		//需要主角等级
		private var _q_need_level: int;
		
		//自动领悟技能编号（需要支持失去军衔自动删除技能,0：不领悟技能）
		private var _q_savvy_skill: int;
		
		//军衔激活技能（0：不触发技能激活事件。10010为技能编号，以此类推）
		private var _q_activation_skill: int;
		
		//攻击
		private var _q_attack: int;
		
		//防御
		private var _q_defence: int;
		
		//暴击
		private var _q_crit: int;
		
		//闪避
		private var _q_dodge: int;
		
		//血量
		private var _q_maxhp: int;
		
		//内力
		private var _q_maxmp: int;
		
		//体力
		private var _q_maxsp: int;
		
		//攻击速度
		private var _q_attackspeed: int;
		
		//移动速度
		private var _q_speed: int;
		
		//幸运
		private var _q_luck: int;
		
		//达到军衔发送公告
		private var _q_bulletin: int;
		
		//是否开启，1开启，0关闭
		private var _q_switch: int;
		
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//军衔等级
			_q_ranklv = readInt();
			//军衔名称
			_q_rankname = readString();
			//角色名颜色（填入色值程序直接调用）
			_q_name_reg = readString();
			//需要军功值
			_q_ranknum = readInt();
			//是否需要服务器排名（0：不需要，数字N：表示排名前N名玩家有资格获得该军衔）
			_q_isranking = readInt();
			//需要主角等级
			_q_need_level = readInt();
			//自动领悟技能编号（需要支持失去军衔自动删除技能,0：不领悟技能）
			_q_savvy_skill = readInt();
			//军衔激活技能（0：不触发技能激活事件。10010为技能编号，以此类推）
			_q_activation_skill = readInt();
			//攻击
			_q_attack = readInt();
			//防御
			_q_defence = readInt();
			//暴击
			_q_crit = readInt();
			//闪避
			_q_dodge = readInt();
			//血量
			_q_maxhp = readInt();
			//内力
			_q_maxmp = readInt();
			//体力
			_q_maxsp = readInt();
			//攻击速度
			_q_attackspeed = readInt();
			//移动速度
			_q_speed = readInt();
			//幸运
			_q_luck = readInt();
			//达到军衔发送公告
			_q_bulletin = readInt();
			//是否开启，1开启，0关闭
			_q_switch = readInt();
			return true;
		}
		
		/**
		 * get 军衔等级
		 * @return 
		 */
		public function get q_ranklv(): int{
			return _q_ranklv;
		}
		
		/**
		 * set 军衔等级
		 */
		public function set q_ranklv(value: int): void{
			this._q_ranklv = value;
		}
		
		/**
		 * get 军衔名称
		 * @return 
		 */
		public function get q_rankname(): String{
			return _q_rankname;
		}
		
		/**
		 * set 军衔名称
		 */
		public function set q_rankname(value: String): void{
			this._q_rankname = value;
		}
		
		/**
		 * get 角色名颜色（填入色值程序直接调用）
		 * @return 
		 */
		public function get q_name_reg(): String{
			return _q_name_reg;
		}
		
		/**
		 * set 角色名颜色（填入色值程序直接调用）
		 */
		public function set q_name_reg(value: String): void{
			this._q_name_reg = value;
		}
		
		/**
		 * get 需要军功值
		 * @return 
		 */
		public function get q_ranknum(): int{
			return _q_ranknum;
		}
		
		/**
		 * set 需要军功值
		 */
		public function set q_ranknum(value: int): void{
			this._q_ranknum = value;
		}
		
		/**
		 * get 是否需要服务器排名（0：不需要，数字N：表示排名前N名玩家有资格获得该军衔）
		 * @return 
		 */
		public function get q_isranking(): int{
			return _q_isranking;
		}
		
		/**
		 * set 是否需要服务器排名（0：不需要，数字N：表示排名前N名玩家有资格获得该军衔）
		 */
		public function set q_isranking(value: int): void{
			this._q_isranking = value;
		}
		
		/**
		 * get 需要主角等级
		 * @return 
		 */
		public function get q_need_level(): int{
			return _q_need_level;
		}
		
		/**
		 * set 需要主角等级
		 */
		public function set q_need_level(value: int): void{
			this._q_need_level = value;
		}
		
		/**
		 * get 自动领悟技能编号（需要支持失去军衔自动删除技能,0：不领悟技能）
		 * @return 
		 */
		public function get q_savvy_skill(): int{
			return _q_savvy_skill;
		}
		
		/**
		 * set 自动领悟技能编号（需要支持失去军衔自动删除技能,0：不领悟技能）
		 */
		public function set q_savvy_skill(value: int): void{
			this._q_savvy_skill = value;
		}
		
		/**
		 * get 军衔激活技能（0：不触发技能激活事件。10010为技能编号，以此类推）
		 * @return 
		 */
		public function get q_activation_skill(): int{
			return _q_activation_skill;
		}
		
		/**
		 * set 军衔激活技能（0：不触发技能激活事件。10010为技能编号，以此类推）
		 */
		public function set q_activation_skill(value: int): void{
			this._q_activation_skill = value;
		}
		
		/**
		 * get 攻击
		 * @return 
		 */
		public function get q_attack(): int{
			return _q_attack;
		}
		
		/**
		 * set 攻击
		 */
		public function set q_attack(value: int): void{
			this._q_attack = value;
		}
		
		/**
		 * get 防御
		 * @return 
		 */
		public function get q_defence(): int{
			return _q_defence;
		}
		
		/**
		 * set 防御
		 */
		public function set q_defence(value: int): void{
			this._q_defence = value;
		}
		
		/**
		 * get 暴击
		 * @return 
		 */
		public function get q_crit(): int{
			return _q_crit;
		}
		
		/**
		 * set 暴击
		 */
		public function set q_crit(value: int): void{
			this._q_crit = value;
		}
		
		/**
		 * get 闪避
		 * @return 
		 */
		public function get q_dodge(): int{
			return _q_dodge;
		}
		
		/**
		 * set 闪避
		 */
		public function set q_dodge(value: int): void{
			this._q_dodge = value;
		}
		
		/**
		 * get 血量
		 * @return 
		 */
		public function get q_maxhp(): int{
			return _q_maxhp;
		}
		
		/**
		 * set 血量
		 */
		public function set q_maxhp(value: int): void{
			this._q_maxhp = value;
		}
		
		/**
		 * get 内力
		 * @return 
		 */
		public function get q_maxmp(): int{
			return _q_maxmp;
		}
		
		/**
		 * set 内力
		 */
		public function set q_maxmp(value: int): void{
			this._q_maxmp = value;
		}
		
		/**
		 * get 体力
		 * @return 
		 */
		public function get q_maxsp(): int{
			return _q_maxsp;
		}
		
		/**
		 * set 体力
		 */
		public function set q_maxsp(value: int): void{
			this._q_maxsp = value;
		}
		
		/**
		 * get 攻击速度
		 * @return 
		 */
		public function get q_attackspeed(): int{
			return _q_attackspeed;
		}
		
		/**
		 * set 攻击速度
		 */
		public function set q_attackspeed(value: int): void{
			this._q_attackspeed = value;
		}
		
		/**
		 * get 移动速度
		 * @return 
		 */
		public function get q_speed(): int{
			return _q_speed;
		}
		
		/**
		 * set 移动速度
		 */
		public function set q_speed(value: int): void{
			this._q_speed = value;
		}
		
		/**
		 * get 幸运
		 * @return 
		 */
		public function get q_luck(): int{
			return _q_luck;
		}
		
		/**
		 * set 幸运
		 */
		public function set q_luck(value: int): void{
			this._q_luck = value;
		}
		
		/**
		 * get 达到军衔发送公告
		 * @return 
		 */
		public function get q_bulletin(): int{
			return _q_bulletin;
		}
		
		/**
		 * set 达到军衔发送公告
		 */
		public function set q_bulletin(value: int): void{
			this._q_bulletin = value;
		}
		
		/**
		 * get 是否开启，1开启，0关闭
		 * @return 
		 */
		public function get q_switch(): int{
			return _q_switch;
		}
		
		/**
		 * set 是否开启，1开启，0关闭
		 */
		public function set q_switch(value: int): void{
			this._q_switch = value;
		}
		
	}
}