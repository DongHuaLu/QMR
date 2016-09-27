package com.game.classicbattle.message{
	import com.game.classicbattle.bean.ClassicBattleInfo;
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 经典战役面板内容消息
	 */
	public class ResClassicBattleInfoToClientMessage extends Message {
	
		//挑战次数
		private var _darenum: int;
		
		//已经过关层数
		private var _clearancelayer: int;
		
		//战役信息
		private var _classicbattleInfolist: Vector.<ClassicBattleInfo> = new Vector.<ClassicBattleInfo>();
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			var i: int = 0;
			//挑战次数
			writeInt(_darenum);
			//已经过关层数
			writeInt(_clearancelayer);
			//战役信息
			writeShort(_classicbattleInfolist.length);
			for (i = 0; i < _classicbattleInfolist.length; i++) {
				writeBean(_classicbattleInfolist[i]);
			}
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			var i: int = 0;
			//挑战次数
			_darenum = readInt();
			//已经过关层数
			_clearancelayer = readInt();
			//战役信息
			var classicbattleInfolist_length : int = readShort();
			for (i = 0; i < classicbattleInfolist_length; i++) {
				_classicbattleInfolist[i] = readBean(ClassicBattleInfo) as ClassicBattleInfo;
			}
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 165101;
		}
		
		/**
		 * get 挑战次数
		 * @return 
		 */
		public function get darenum(): int{
			return _darenum;
		}
		
		/**
		 * set 挑战次数
		 */
		public function set darenum(value: int): void{
			this._darenum = value;
		}
		
		/**
		 * get 已经过关层数
		 * @return 
		 */
		public function get clearancelayer(): int{
			return _clearancelayer;
		}
		
		/**
		 * set 已经过关层数
		 */
		public function set clearancelayer(value: int): void{
			this._clearancelayer = value;
		}
		
		/**
		 * get 战役信息
		 * @return 
		 */
		public function get classicbattleInfolist(): Vector.<ClassicBattleInfo>{
			return _classicbattleInfolist;
		}
		
		/**
		 * set 战役信息
		 */
		public function set classicbattleInfolist(value: Vector.<ClassicBattleInfo>): void{
			this._classicbattleInfolist = value;
		}
		
	}
}