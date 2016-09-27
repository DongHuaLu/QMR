package com.game.batter.message{
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 连击消息
	 */
	public class ResMonsterBatterToClientMessage extends Message {
	
		//类型：1普通怪连斩，2BOSS连击
		private var _type: int;
		
		//连击倒计时（秒）
		private var _countdowntime: int;
		
		//增加攻击力
		private var _atk: int;
		
		//连击次数
		private var _num: int;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//类型：1普通怪连斩，2BOSS连击
			writeByte(_type);
			//连击倒计时（秒）
			writeInt(_countdowntime);
			//增加攻击力
			writeInt(_atk);
			//连击次数
			writeInt(_num);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//类型：1普通怪连斩，2BOSS连击
			_type = readByte();
			//连击倒计时（秒）
			_countdowntime = readInt();
			//增加攻击力
			_atk = readInt();
			//连击次数
			_num = readInt();
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 141101;
		}
		
		/**
		 * get 类型：1普通怪连斩，2BOSS连击
		 * @return 
		 */
		public function get type(): int{
			return _type;
		}
		
		/**
		 * set 类型：1普通怪连斩，2BOSS连击
		 */
		public function set type(value: int): void{
			this._type = value;
		}
		
		/**
		 * get 连击倒计时（秒）
		 * @return 
		 */
		public function get countdowntime(): int{
			return _countdowntime;
		}
		
		/**
		 * set 连击倒计时（秒）
		 */
		public function set countdowntime(value: int): void{
			this._countdowntime = value;
		}
		
		/**
		 * get 增加攻击力
		 * @return 
		 */
		public function get atk(): int{
			return _atk;
		}
		
		/**
		 * set 增加攻击力
		 */
		public function set atk(value: int): void{
			this._atk = value;
		}
		
		/**
		 * get 连击次数
		 * @return 
		 */
		public function get num(): int{
			return _num;
		}
		
		/**
		 * set 连击次数
		 */
		public function set num(value: int): void{
			this._num = value;
		}
		
	}
}