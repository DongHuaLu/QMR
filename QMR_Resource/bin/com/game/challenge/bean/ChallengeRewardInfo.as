package com.game.challenge.bean{
	import net.Bean;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 挑战奖励信息
	 */
	public class ChallengeRewardInfo extends Bean {
	
		//奖励id -1铜币，-2元宝，-3真气，-4经验  -5礼金
		private var _id: int;
		
		//奖励数量
		private var _num: int;
		
		//奖励类型，0通关或者优胜，1参与奖
		private var _type: int;
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//奖励id -1铜币，-2元宝，-3真气，-4经验  -5礼金
			writeInt(_id);
			//奖励数量
			writeInt(_num);
			//奖励类型，0通关或者优胜，1参与奖
			writeInt(_type);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//奖励id -1铜币，-2元宝，-3真气，-4经验  -5礼金
			_id = readInt();
			//奖励数量
			_num = readInt();
			//奖励类型，0通关或者优胜，1参与奖
			_type = readInt();
			return true;
		}
		
		/**
		 * get 奖励id -1铜币，-2元宝，-3真气，-4经验  -5礼金
		 * @return 
		 */
		public function get id(): int{
			return _id;
		}
		
		/**
		 * set 奖励id -1铜币，-2元宝，-3真气，-4经验  -5礼金
		 */
		public function set id(value: int): void{
			this._id = value;
		}
		
		/**
		 * get 奖励数量
		 * @return 
		 */
		public function get num(): int{
			return _num;
		}
		
		/**
		 * set 奖励数量
		 */
		public function set num(value: int): void{
			this._num = value;
		}
		
		/**
		 * get 奖励类型，0通关或者优胜，1参与奖
		 * @return 
		 */
		public function get type(): int{
			return _type;
		}
		
		/**
		 * set 奖励类型，0通关或者优胜，1参与奖
		 */
		public function set type(value: int): void{
			this._type = value;
		}
		
	}
}