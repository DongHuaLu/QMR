package com.game.longyuan.message{
	import com.game.longyuan.bean.LongYuanInfo;
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 激活星位
	 */
	public class ResLongYuanActivationMessage extends Message {
	
		//真气值
		private var _zhenqi: int;
		
		//龙元心法信息
		private var _longyuaninfo: LongYuanInfo;
		
		//是否成功,0成功，1失败
		private var _status: int;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//真气值
			writeInt(_zhenqi);
			//龙元心法信息
			writeBean(_longyuaninfo);
			//是否成功,0成功，1失败
			writeByte(_status);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//真气值
			_zhenqi = readInt();
			//龙元心法信息
			_longyuaninfo = readBean(LongYuanInfo) as LongYuanInfo;
			//是否成功,0成功，1失败
			_status = readByte();
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 115102;
		}
		
		/**
		 * get 真气值
		 * @return 
		 */
		public function get zhenqi(): int{
			return _zhenqi;
		}
		
		/**
		 * set 真气值
		 */
		public function set zhenqi(value: int): void{
			this._zhenqi = value;
		}
		
		/**
		 * get 龙元心法信息
		 * @return 
		 */
		public function get longyuaninfo(): LongYuanInfo{
			return _longyuaninfo;
		}
		
		/**
		 * set 龙元心法信息
		 */
		public function set longyuaninfo(value: LongYuanInfo): void{
			this._longyuaninfo = value;
		}
		
		/**
		 * get 是否成功,0成功，1失败
		 * @return 
		 */
		public function get status(): int{
			return _status;
		}
		
		/**
		 * set 是否成功,0成功，1失败
		 */
		public function set status(value: int): void{
			this._status = value;
		}
		
	}
}