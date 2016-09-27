package com.game.dataserver.message{
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 返回前端勋章
	 */
	public class ResGetMedalToClientMessage extends Message {
	
		//勋章
		private var _medal: int;
		
		//荣誉
		private var _honor: int;
		
		//胜负记录
		private var _matchinfo: int;
		
		//今日荣誉
		private var _todayHonor: int;
		
		//今日连胜荣誉
		private var _continuousHonor: int;
		
		//今日连胜次数
		private var _dayconvictory: int;
		
		//今日最大连胜次数
		private var _dayconvictorymax: int;
		
		//历史最大连胜次数
		private var _convictorymax: int;
		
		//当前连胜次数
		private var _currwinningstreak: int;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//勋章
			writeInt(_medal);
			//荣誉
			writeInt(_honor);
			//胜负记录
			writeInt(_matchinfo);
			//今日荣誉
			writeInt(_todayHonor);
			//今日连胜荣誉
			writeInt(_continuousHonor);
			//今日连胜次数
			writeInt(_dayconvictory);
			//今日最大连胜次数
			writeInt(_dayconvictorymax);
			//历史最大连胜次数
			writeInt(_convictorymax);
			//当前连胜次数
			writeInt(_currwinningstreak);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//勋章
			_medal = readInt();
			//荣誉
			_honor = readInt();
			//胜负记录
			_matchinfo = readInt();
			//今日荣誉
			_todayHonor = readInt();
			//今日连胜荣誉
			_continuousHonor = readInt();
			//今日连胜次数
			_dayconvictory = readInt();
			//今日最大连胜次数
			_dayconvictorymax = readInt();
			//历史最大连胜次数
			_convictorymax = readInt();
			//当前连胜次数
			_currwinningstreak = readInt();
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 203112;
		}
		
		/**
		 * get 勋章
		 * @return 
		 */
		public function get medal(): int{
			return _medal;
		}
		
		/**
		 * set 勋章
		 */
		public function set medal(value: int): void{
			this._medal = value;
		}
		
		/**
		 * get 荣誉
		 * @return 
		 */
		public function get honor(): int{
			return _honor;
		}
		
		/**
		 * set 荣誉
		 */
		public function set honor(value: int): void{
			this._honor = value;
		}
		
		/**
		 * get 胜负记录
		 * @return 
		 */
		public function get matchinfo(): int{
			return _matchinfo;
		}
		
		/**
		 * set 胜负记录
		 */
		public function set matchinfo(value: int): void{
			this._matchinfo = value;
		}
		
		/**
		 * get 今日荣誉
		 * @return 
		 */
		public function get todayHonor(): int{
			return _todayHonor;
		}
		
		/**
		 * set 今日荣誉
		 */
		public function set todayHonor(value: int): void{
			this._todayHonor = value;
		}
		
		/**
		 * get 今日连胜荣誉
		 * @return 
		 */
		public function get continuousHonor(): int{
			return _continuousHonor;
		}
		
		/**
		 * set 今日连胜荣誉
		 */
		public function set continuousHonor(value: int): void{
			this._continuousHonor = value;
		}
		
		/**
		 * get 今日连胜次数
		 * @return 
		 */
		public function get dayconvictory(): int{
			return _dayconvictory;
		}
		
		/**
		 * set 今日连胜次数
		 */
		public function set dayconvictory(value: int): void{
			this._dayconvictory = value;
		}
		
		/**
		 * get 今日最大连胜次数
		 * @return 
		 */
		public function get dayconvictorymax(): int{
			return _dayconvictorymax;
		}
		
		/**
		 * set 今日最大连胜次数
		 */
		public function set dayconvictorymax(value: int): void{
			this._dayconvictorymax = value;
		}
		
		/**
		 * get 历史最大连胜次数
		 * @return 
		 */
		public function get convictorymax(): int{
			return _convictorymax;
		}
		
		/**
		 * set 历史最大连胜次数
		 */
		public function set convictorymax(value: int): void{
			this._convictorymax = value;
		}
		
		/**
		 * get 当前连胜次数
		 * @return 
		 */
		public function get currwinningstreak(): int{
			return _currwinningstreak;
		}
		
		/**
		 * set 当前连胜次数
		 */
		public function set currwinningstreak(value: int): void{
			this._currwinningstreak = value;
		}
		
	}
}