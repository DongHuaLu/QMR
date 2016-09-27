package com.game.marriage.message{
	import com.game.marriage.bean.RedEnvelopeInfo;
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 红包信息
	 */
	public class ResRedEnvelopeToClientMessage extends Message {
	
		//红包信息列表
		private var _redenvelopelist: Vector.<RedEnvelopeInfo> = new Vector.<RedEnvelopeInfo>();
		//红包总收益
		private var _redsum: int;
		
		//1已经领取，0没有领取
		private var _isreceive: int;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			var i: int = 0;
			//红包信息列表
			writeShort(_redenvelopelist.length);
			for (i = 0; i < _redenvelopelist.length; i++) {
				writeBean(_redenvelopelist[i]);
			}
			//红包总收益
			writeInt(_redsum);
			//1已经领取，0没有领取
			writeByte(_isreceive);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			var i: int = 0;
			//红包信息列表
			var redenvelopelist_length : int = readShort();
			for (i = 0; i < redenvelopelist_length; i++) {
				_redenvelopelist[i] = readBean(RedEnvelopeInfo) as RedEnvelopeInfo;
			}
			//红包总收益
			_redsum = readInt();
			//1已经领取，0没有领取
			_isreceive = readByte();
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 163107;
		}
		
		/**
		 * get 红包信息列表
		 * @return 
		 */
		public function get redenvelopelist(): Vector.<RedEnvelopeInfo>{
			return _redenvelopelist;
		}
		
		/**
		 * set 红包信息列表
		 */
		public function set redenvelopelist(value: Vector.<RedEnvelopeInfo>): void{
			this._redenvelopelist = value;
		}
		
		/**
		 * get 红包总收益
		 * @return 
		 */
		public function get redsum(): int{
			return _redsum;
		}
		
		/**
		 * set 红包总收益
		 */
		public function set redsum(value: int): void{
			this._redsum = value;
		}
		
		/**
		 * get 1已经领取，0没有领取
		 * @return 
		 */
		public function get isreceive(): int{
			return _isreceive;
		}
		
		/**
		 * set 1已经领取，0没有领取
		 */
		public function set isreceive(value: int): void{
			this._isreceive = value;
		}
		
	}
}