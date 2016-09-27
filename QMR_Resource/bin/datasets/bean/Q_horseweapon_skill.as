package datasets.bean{
	import com.game.message.Bean;
	
	/** 
	 * @author ExcelUtil Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * Q_horseweapon_skill
	 */
	public class Q_horseweapon_skill extends Bean{
	
		//技能格子
		private var _q_grid: int;
		
		//技能类型（1,：龙元武学组合技能，2：固定组合技能）
		private var _q_skilltype: int;
		
		//骑兵技能编号（1：龙元武学组合技能，固定组合技能ID）
		private var _q_skillid: String;
		
		//显示所需骑兵阶数
		private var _q_show_rank: int;
		
		//激活所需骑兵阶数
		private var _q_active_rank: int;
		
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//技能格子
			_q_grid = readInt();
			//技能类型（1,：龙元武学组合技能，2：固定组合技能）
			_q_skilltype = readInt();
			//骑兵技能编号（1：龙元武学组合技能，固定组合技能ID）
			_q_skillid = readString();
			//显示所需骑兵阶数
			_q_show_rank = readInt();
			//激活所需骑兵阶数
			_q_active_rank = readInt();
			return true;
		}
		
		/**
		 * get 技能格子
		 * @return 
		 */
		public function get q_grid(): int{
			return _q_grid;
		}
		
		/**
		 * set 技能格子
		 */
		public function set q_grid(value: int): void{
			this._q_grid = value;
		}
		
		/**
		 * get 技能类型（1,：龙元武学组合技能，2：固定组合技能）
		 * @return 
		 */
		public function get q_skilltype(): int{
			return _q_skilltype;
		}
		
		/**
		 * set 技能类型（1,：龙元武学组合技能，2：固定组合技能）
		 */
		public function set q_skilltype(value: int): void{
			this._q_skilltype = value;
		}
		
		/**
		 * get 骑兵技能编号（1：龙元武学组合技能，固定组合技能ID）
		 * @return 
		 */
		public function get q_skillid(): String{
			return _q_skillid;
		}
		
		/**
		 * set 骑兵技能编号（1：龙元武学组合技能，固定组合技能ID）
		 */
		public function set q_skillid(value: String): void{
			this._q_skillid = value;
		}
		
		/**
		 * get 显示所需骑兵阶数
		 * @return 
		 */
		public function get q_show_rank(): int{
			return _q_show_rank;
		}
		
		/**
		 * set 显示所需骑兵阶数
		 */
		public function set q_show_rank(value: int): void{
			this._q_show_rank = value;
		}
		
		/**
		 * get 激活所需骑兵阶数
		 * @return 
		 */
		public function get q_active_rank(): int{
			return _q_active_rank;
		}
		
		/**
		 * set 激活所需骑兵阶数
		 */
		public function set q_active_rank(value: int): void{
			this._q_active_rank = value;
		}
		
	}
}