package com.game.arrow.message{
	import com.game.arrow.bean.ArrowInfo;
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 返回弓箭信息
	 */
	public class ResArrowInfoMessage extends Message {
	
		//通知类型
		private var _notifytype: int;
		
		//弓箭信息
		private var _arrowinfo: ArrowInfo;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//通知类型
			writeInt(_notifytype);
			//弓箭信息
			writeBean(_arrowinfo);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//通知类型
			_notifytype = readInt();
			//弓箭信息
			_arrowinfo = readBean(ArrowInfo) as ArrowInfo;
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 151101;
		}
		
		/**
		 * get 通知类型
		 * @return 
		 */
		public function get notifytype(): int{
			return _notifytype;
		}
		
		/**
		 * set 通知类型
		 */
		public function set notifytype(value: int): void{
			this._notifytype = value;
		}
		
		/**
		 * get 弓箭信息
		 * @return 
		 */
		public function get arrowinfo(): ArrowInfo{
			return _arrowinfo;
		}
		
		/**
		 * set 弓箭信息
		 */
		public function set arrowinfo(value: ArrowInfo): void{
			this._arrowinfo = value;
		}
		
	}
}