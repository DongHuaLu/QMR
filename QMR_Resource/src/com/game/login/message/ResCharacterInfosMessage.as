package com.game.login.message{
	import com.game.login.bean.CharacterInfo;
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 角色信息列表
	 */
	public class ResCharacterInfosMessage extends Message {
	
		//角色信息列表
		private var _characters: Vector.<CharacterInfo> = new Vector.<CharacterInfo>();
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			var i: int = 0;
			//角色信息列表
			writeShort(_characters.length);
			for (i = 0; i < _characters.length; i++) {
				writeBean(_characters[i]);
			}
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			var i: int = 0;
			//角色信息列表
			var characters_length : int = readShort();
			for (i = 0; i < characters_length; i++) {
				_characters[i] = readBean(CharacterInfo) as CharacterInfo;
			}
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 100101;
		}
		
		/**
		 * get 角色信息列表
		 * @return 
		 */
		public function get characters(): Vector.<CharacterInfo>{
			return _characters;
		}
		
		/**
		 * set 角色信息列表
		 */
		public function set characters(value: Vector.<CharacterInfo>): void{
			this._characters = value;
		}
		
	}
}