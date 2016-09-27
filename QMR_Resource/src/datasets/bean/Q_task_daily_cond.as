package datasets.bean{
	import com.game.message.Bean;
	
	/** 
	 * @author ExcelUtil Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * Q_task_daily_cond
	 */
	public class Q_task_daily_cond extends Bean{
	
		//任务编号
		private var _q_id: int;
		
		//玩家等级区间min
		private var _q_mingrade: int;
		
		//玩家等级区间max
		private var _q_maxgrade: int;
		
		//任务完成条件检测物品（物品ID_需求数量_寻径链接(地图ID_x_y);物品ID_需求数量_寻径链接(地图ID_x_y);）
		private var _q_end_needgoods: String;
		
		//任务完成条件检测杀死怪物序列（怪物ID_需求数量_寻径链接(地图ID_x_y);怪物ID_需求数量_寻径链接(地图ID_x_y);）
		private var _q_end_needkillmonster: String;
		
		//任务完成条件检测成就序列（成就编号_寻径链接;成就编号_寻径链接）
		private var _q_end_needachieve: String;
		
		//任务目标难度级别
		private var _q_hard: int;
		
		//完成时收取物品序列(物品ID_数量;物品ID_数量）
		private var _q_resume_goods: String;
		
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//任务编号
			_q_id = readInt();
			//玩家等级区间min
			_q_mingrade = readInt();
			//玩家等级区间max
			_q_maxgrade = readInt();
			//任务完成条件检测物品（物品ID_需求数量_寻径链接(地图ID_x_y);物品ID_需求数量_寻径链接(地图ID_x_y);）
			_q_end_needgoods = readString();
			//任务完成条件检测杀死怪物序列（怪物ID_需求数量_寻径链接(地图ID_x_y);怪物ID_需求数量_寻径链接(地图ID_x_y);）
			_q_end_needkillmonster = readString();
			//任务完成条件检测成就序列（成就编号_寻径链接;成就编号_寻径链接）
			_q_end_needachieve = readString();
			//任务目标难度级别
			_q_hard = readInt();
			//完成时收取物品序列(物品ID_数量;物品ID_数量）
			_q_resume_goods = readString();
			return true;
		}
		
		/**
		 * get 任务编号
		 * @return 
		 */
		public function get q_id(): int{
			return _q_id;
		}
		
		/**
		 * set 任务编号
		 */
		public function set q_id(value: int): void{
			this._q_id = value;
		}
		
		/**
		 * get 玩家等级区间min
		 * @return 
		 */
		public function get q_mingrade(): int{
			return _q_mingrade;
		}
		
		/**
		 * set 玩家等级区间min
		 */
		public function set q_mingrade(value: int): void{
			this._q_mingrade = value;
		}
		
		/**
		 * get 玩家等级区间max
		 * @return 
		 */
		public function get q_maxgrade(): int{
			return _q_maxgrade;
		}
		
		/**
		 * set 玩家等级区间max
		 */
		public function set q_maxgrade(value: int): void{
			this._q_maxgrade = value;
		}
		
		/**
		 * get 任务完成条件检测物品（物品ID_需求数量_寻径链接(地图ID_x_y);物品ID_需求数量_寻径链接(地图ID_x_y);）
		 * @return 
		 */
		public function get q_end_needgoods(): String{
			return _q_end_needgoods;
		}
		
		/**
		 * set 任务完成条件检测物品（物品ID_需求数量_寻径链接(地图ID_x_y);物品ID_需求数量_寻径链接(地图ID_x_y);）
		 */
		public function set q_end_needgoods(value: String): void{
			this._q_end_needgoods = value;
		}
		
		/**
		 * get 任务完成条件检测杀死怪物序列（怪物ID_需求数量_寻径链接(地图ID_x_y);怪物ID_需求数量_寻径链接(地图ID_x_y);）
		 * @return 
		 */
		public function get q_end_needkillmonster(): String{
			return _q_end_needkillmonster;
		}
		
		/**
		 * set 任务完成条件检测杀死怪物序列（怪物ID_需求数量_寻径链接(地图ID_x_y);怪物ID_需求数量_寻径链接(地图ID_x_y);）
		 */
		public function set q_end_needkillmonster(value: String): void{
			this._q_end_needkillmonster = value;
		}
		
		/**
		 * get 任务完成条件检测成就序列（成就编号_寻径链接;成就编号_寻径链接）
		 * @return 
		 */
		public function get q_end_needachieve(): String{
			return _q_end_needachieve;
		}
		
		/**
		 * set 任务完成条件检测成就序列（成就编号_寻径链接;成就编号_寻径链接）
		 */
		public function set q_end_needachieve(value: String): void{
			this._q_end_needachieve = value;
		}
		
		/**
		 * get 任务目标难度级别
		 * @return 
		 */
		public function get q_hard(): int{
			return _q_hard;
		}
		
		/**
		 * set 任务目标难度级别
		 */
		public function set q_hard(value: int): void{
			this._q_hard = value;
		}
		
		/**
		 * get 完成时收取物品序列(物品ID_数量;物品ID_数量）
		 * @return 
		 */
		public function get q_resume_goods(): String{
			return _q_resume_goods;
		}
		
		/**
		 * set 完成时收取物品序列(物品ID_数量;物品ID_数量）
		 */
		public function set q_resume_goods(value: String): void{
			this._q_resume_goods = value;
		}
		
	}
}