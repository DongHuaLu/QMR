package com.game.version.message{
	import com.game.version.bean.VersionResInfo;
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 通知在线玩家更新RES版本信息
	 */
	public class ResVersionUpdateToClientMessage extends Message {
	
		//RES文件版本信息表
		private var _versionlist: Vector.<VersionResInfo> = new Vector.<VersionResInfo>();
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			var i: int = 0;
			//RES文件版本信息表
			writeShort(_versionlist.length);
			for (i = 0; i < _versionlist.length; i++) {
				writeBean(_versionlist[i]);
			}
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			var i: int = 0;
			//RES文件版本信息表
			var versionlist_length : int = readShort();
			for (i = 0; i < versionlist_length; i++) {
				_versionlist[i] = readBean(VersionResInfo) as VersionResInfo;
			}
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 202101;
		}
		
		/**
		 * get RES文件版本信息表
		 * @return 
		 */
		public function get versionlist(): Vector.<VersionResInfo>{
			return _versionlist;
		}
		
		/**
		 * set RES文件版本信息表
		 */
		public function set versionlist(value: Vector.<VersionResInfo>): void{
			this._versionlist = value;
		}
		
	}
}