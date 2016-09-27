package com.game.emperorcity.message{
	import com.game.emperorcity.bean.EmperorWarTopInfo;
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 皇城争霸战斩将排行榜信息
	 */
	public class ResEmperorWarTopInfoToClientMessage extends Message {
	
		//皇城争霸战斩将排行榜信息
		private var _emperorWarTopInfolist: Vector.<EmperorWarTopInfo> = new Vector.<EmperorWarTopInfo>();
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			var i: int = 0;
			//皇城争霸战斩将排行榜信息
			writeShort(_emperorWarTopInfolist.length);
			for (i = 0; i < _emperorWarTopInfolist.length; i++) {
				writeBean(_emperorWarTopInfolist[i]);
			}
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			var i: int = 0;
			//皇城争霸战斩将排行榜信息
			var emperorWarTopInfolist_length : int = readShort();
			for (i = 0; i < emperorWarTopInfolist_length; i++) {
				_emperorWarTopInfolist[i] = readBean(EmperorWarTopInfo) as EmperorWarTopInfo;
			}
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 169103;
		}
		
		/**
		 * get 皇城争霸战斩将排行榜信息
		 * @return 
		 */
		public function get emperorWarTopInfolist(): Vector.<EmperorWarTopInfo>{
			return _emperorWarTopInfolist;
		}
		
		/**
		 * set 皇城争霸战斩将排行榜信息
		 */
		public function set emperorWarTopInfolist(value: Vector.<EmperorWarTopInfo>): void{
			this._emperorWarTopInfolist = value;
		}
		
	}
}