package com.game.toplist.message{
	import com.game.toplist.bean.ArenaTimesInfo;
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 发送给前端竞技场排行
	 */
	public class ResToplistArenaMessage extends Message {
	
		//错误代码
		private var _errorcode: int;
		
		//排行榜信息
		private var _infos: Vector.<ArenaTimesInfo> = new Vector.<ArenaTimesInfo>();
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			var i: int = 0;
			//错误代码
			writeByte(_errorcode);
			//排行榜信息
			writeShort(_infos.length);
			for (i = 0; i < _infos.length; i++) {
				writeBean(_infos[i]);
			}
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			var i: int = 0;
			//错误代码
			_errorcode = readByte();
			//排行榜信息
			var infos_length : int = readShort();
			for (i = 0; i < infos_length; i++) {
				_infos[i] = readBean(ArenaTimesInfo) as ArenaTimesInfo;
			}
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 142103;
		}
		
		/**
		 * get 错误代码
		 * @return 
		 */
		public function get errorcode(): int{
			return _errorcode;
		}
		
		/**
		 * set 错误代码
		 */
		public function set errorcode(value: int): void{
			this._errorcode = value;
		}
		
		/**
		 * get 排行榜信息
		 * @return 
		 */
		public function get infos(): Vector.<ArenaTimesInfo>{
			return _infos;
		}
		
		/**
		 * set 排行榜信息
		 */
		public function set infos(value: Vector.<ArenaTimesInfo>): void{
			this._infos = value;
		}
		
	}
}