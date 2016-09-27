package com.game.gem.message{
	import com.game.gem.bean.PosGemInfo;
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 返回客户端全部宝石信息
	 */
	public class ResOpenGemPanelMessage extends Message {
	
		//装备部位全部宝石信息
		private var _posallgeminfo: Vector.<PosGemInfo> = new Vector.<PosGemInfo>();
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			var i: int = 0;
			//装备部位全部宝石信息
			writeShort(_posallgeminfo.length);
			for (i = 0; i < _posallgeminfo.length; i++) {
				writeBean(_posallgeminfo[i]);
			}
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			var i: int = 0;
			//装备部位全部宝石信息
			var posallgeminfo_length : int = readShort();
			for (i = 0; i < posallgeminfo_length; i++) {
				_posallgeminfo[i] = readBean(PosGemInfo) as PosGemInfo;
			}
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 132101;
		}
		
		/**
		 * get 装备部位全部宝石信息
		 * @return 
		 */
		public function get posallgeminfo(): Vector.<PosGemInfo>{
			return _posallgeminfo;
		}
		
		/**
		 * set 装备部位全部宝石信息
		 */
		public function set posallgeminfo(value: Vector.<PosGemInfo>): void{
			this._posallgeminfo = value;
		}
		
	}
}