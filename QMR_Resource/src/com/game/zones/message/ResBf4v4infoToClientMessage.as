package com.game.zones.message{
	import com.game.zones.bean.BfStructsInfo;
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 发送4v4副本战场战况列表
	 */
	public class ResBf4v4infoToClientMessage extends Message {
	
		//战场战况列表
		private var _BfStructsInfolist: Vector.<BfStructsInfo> = new Vector.<BfStructsInfo>();
		//倒计时（秒）
		private var _time: int;
		
		//阵营A当前占旗数量
		private var _seizeflag_a: int;
		
		//阵营B当前占旗数量
		private var _seizeflag_b: int;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			var i: int = 0;
			//战场战况列表
			writeShort(_BfStructsInfolist.length);
			for (i = 0; i < _BfStructsInfolist.length; i++) {
				writeBean(_BfStructsInfolist[i]);
			}
			//倒计时（秒）
			writeInt(_time);
			//阵营A当前占旗数量
			writeInt(_seizeflag_a);
			//阵营B当前占旗数量
			writeInt(_seizeflag_b);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			var i: int = 0;
			//战场战况列表
			var BfStructsInfolist_length : int = readShort();
			for (i = 0; i < BfStructsInfolist_length; i++) {
				_BfStructsInfolist[i] = readBean(BfStructsInfo) as BfStructsInfo;
			}
			//倒计时（秒）
			_time = readInt();
			//阵营A当前占旗数量
			_seizeflag_a = readInt();
			//阵营B当前占旗数量
			_seizeflag_b = readInt();
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 128121;
		}
		
		/**
		 * get 战场战况列表
		 * @return 
		 */
		public function get BfStructsInfolist(): Vector.<BfStructsInfo>{
			return _BfStructsInfolist;
		}
		
		/**
		 * set 战场战况列表
		 */
		public function set BfStructsInfolist(value: Vector.<BfStructsInfo>): void{
			this._BfStructsInfolist = value;
		}
		
		/**
		 * get 倒计时（秒）
		 * @return 
		 */
		public function get time(): int{
			return _time;
		}
		
		/**
		 * set 倒计时（秒）
		 */
		public function set time(value: int): void{
			this._time = value;
		}
		
		/**
		 * get 阵营A当前占旗数量
		 * @return 
		 */
		public function get seizeflag_a(): int{
			return _seizeflag_a;
		}
		
		/**
		 * set 阵营A当前占旗数量
		 */
		public function set seizeflag_a(value: int): void{
			this._seizeflag_a = value;
		}
		
		/**
		 * get 阵营B当前占旗数量
		 * @return 
		 */
		public function get seizeflag_b(): int{
			return _seizeflag_b;
		}
		
		/**
		 * set 阵营B当前占旗数量
		 */
		public function set seizeflag_b(value: int): void{
			this._seizeflag_b = value;
		}
		
	}
}