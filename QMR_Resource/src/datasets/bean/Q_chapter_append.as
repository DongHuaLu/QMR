package datasets.bean{
	import com.game.message.Bean;
	
	/** 
	 * @author ExcelUtil Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * Q_chapter_append
	 */
	public class Q_chapter_append extends Bean{
	
		//章节ID
		private var _q_chapter: int;
		
		//本章节完成后获得
		private var _q_name: String;
		
		//增加血量上限
		private var _q_maxhp: int;
		
		//增加内力上限
		private var _q_maxmp: int;
		
		//增加体力上限
		private var _q_maxsp: int;
		
		//增加攻击
		private var _q_attack: int;
		
		//增加防御
		private var _q_defence: int;
		
		//增加闪避
		private var _q_dodge: int;
		
		//增加暴击
		private var _q_crit: int;
		
		//增加幸运
		private var _q_luck: int;
		
		//增加攻速
		private var _q_attack_speed: int;
		
		//增加移速
		private var _q_movespeed: int;
		
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//章节ID
			_q_chapter = readInt();
			//本章节完成后获得
			_q_name = readString();
			//增加血量上限
			_q_maxhp = readInt();
			//增加内力上限
			_q_maxmp = readInt();
			//增加体力上限
			_q_maxsp = readInt();
			//增加攻击
			_q_attack = readInt();
			//增加防御
			_q_defence = readInt();
			//增加闪避
			_q_dodge = readInt();
			//增加暴击
			_q_crit = readInt();
			//增加幸运
			_q_luck = readInt();
			//增加攻速
			_q_attack_speed = readInt();
			//增加移速
			_q_movespeed = readInt();
			return true;
		}
		
		/**
		 * get 章节ID
		 * @return 
		 */
		public function get q_chapter(): int{
			return _q_chapter;
		}
		
		/**
		 * set 章节ID
		 */
		public function set q_chapter(value: int): void{
			this._q_chapter = value;
		}
		
		/**
		 * get 本章节完成后获得
		 * @return 
		 */
		public function get q_name(): String{
			return _q_name;
		}
		
		/**
		 * set 本章节完成后获得
		 */
		public function set q_name(value: String): void{
			this._q_name = value;
		}
		
		/**
		 * get 增加血量上限
		 * @return 
		 */
		public function get q_maxhp(): int{
			return _q_maxhp;
		}
		
		/**
		 * set 增加血量上限
		 */
		public function set q_maxhp(value: int): void{
			this._q_maxhp = value;
		}
		
		/**
		 * get 增加内力上限
		 * @return 
		 */
		public function get q_maxmp(): int{
			return _q_maxmp;
		}
		
		/**
		 * set 增加内力上限
		 */
		public function set q_maxmp(value: int): void{
			this._q_maxmp = value;
		}
		
		/**
		 * get 增加体力上限
		 * @return 
		 */
		public function get q_maxsp(): int{
			return _q_maxsp;
		}
		
		/**
		 * set 增加体力上限
		 */
		public function set q_maxsp(value: int): void{
			this._q_maxsp = value;
		}
		
		/**
		 * get 增加攻击
		 * @return 
		 */
		public function get q_attack(): int{
			return _q_attack;
		}
		
		/**
		 * set 增加攻击
		 */
		public function set q_attack(value: int): void{
			this._q_attack = value;
		}
		
		/**
		 * get 增加防御
		 * @return 
		 */
		public function get q_defence(): int{
			return _q_defence;
		}
		
		/**
		 * set 增加防御
		 */
		public function set q_defence(value: int): void{
			this._q_defence = value;
		}
		
		/**
		 * get 增加闪避
		 * @return 
		 */
		public function get q_dodge(): int{
			return _q_dodge;
		}
		
		/**
		 * set 增加闪避
		 */
		public function set q_dodge(value: int): void{
			this._q_dodge = value;
		}
		
		/**
		 * get 增加暴击
		 * @return 
		 */
		public function get q_crit(): int{
			return _q_crit;
		}
		
		/**
		 * set 增加暴击
		 */
		public function set q_crit(value: int): void{
			this._q_crit = value;
		}
		
		/**
		 * get 增加幸运
		 * @return 
		 */
		public function get q_luck(): int{
			return _q_luck;
		}
		
		/**
		 * set 增加幸运
		 */
		public function set q_luck(value: int): void{
			this._q_luck = value;
		}
		
		/**
		 * get 增加攻速
		 * @return 
		 */
		public function get q_attack_speed(): int{
			return _q_attack_speed;
		}
		
		/**
		 * set 增加攻速
		 */
		public function set q_attack_speed(value: int): void{
			this._q_attack_speed = value;
		}
		
		/**
		 * get 增加移速
		 * @return 
		 */
		public function get q_movespeed(): int{
			return _q_movespeed;
		}
		
		/**
		 * set 增加移速
		 */
		public function set q_movespeed(value: int): void{
			this._q_movespeed = value;
		}
		
	}
}