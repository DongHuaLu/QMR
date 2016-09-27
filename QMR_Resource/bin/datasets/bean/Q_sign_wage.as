package datasets.bean{
	import com.game.message.Bean;
	
	/** 
	 * @author ExcelUtil Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * Q_sign_wage
	 */
	public class Q_sign_wage extends Bean{
	
		//签到次数
		private var _q_sign_num: int;
		
		//奖励道具（道具ID,数量,性别（0通，1男，2女）,绑定（0,1绑定）,(1212311059消失时间),强化等级,附加属性(类型|值);道具ID,数量）
		private var _q_reward: String;
		
		//VIP额外奖励
		private var _q_vip_reward: String;
		
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//签到次数
			_q_sign_num = readInt();
			//奖励道具（道具ID,数量,性别（0通，1男，2女）,绑定（0,1绑定）,(1212311059消失时间),强化等级,附加属性(类型|值);道具ID,数量）
			_q_reward = readString();
			//VIP额外奖励
			_q_vip_reward = readString();
			return true;
		}
		
		/**
		 * get 签到次数
		 * @return 
		 */
		public function get q_sign_num(): int{
			return _q_sign_num;
		}
		
		/**
		 * set 签到次数
		 */
		public function set q_sign_num(value: int): void{
			this._q_sign_num = value;
		}
		
		/**
		 * get 奖励道具（道具ID,数量,性别（0通，1男，2女）,绑定（0,1绑定）,(1212311059消失时间),强化等级,附加属性(类型|值);道具ID,数量）
		 * @return 
		 */
		public function get q_reward(): String{
			return _q_reward;
		}
		
		/**
		 * set 奖励道具（道具ID,数量,性别（0通，1男，2女）,绑定（0,1绑定）,(1212311059消失时间),强化等级,附加属性(类型|值);道具ID,数量）
		 */
		public function set q_reward(value: String): void{
			this._q_reward = value;
		}
		
		/**
		 * get VIP额外奖励
		 * @return 
		 */
		public function get q_vip_reward(): String{
			return _q_vip_reward;
		}
		
		/**
		 * set VIP额外奖励
		 */
		public function set q_vip_reward(value: String): void{
			this._q_vip_reward = value;
		}
		
	}
}