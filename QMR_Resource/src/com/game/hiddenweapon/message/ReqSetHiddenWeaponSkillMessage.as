package com.game.hiddenweapon.message{
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 开始设置暗器技能
	 */
	public class ReqSetHiddenWeaponSkillMessage extends Message {
	
		//设置技能格子
		private var _grid: int;
		
		//设置技能ID
		private var _skillId: int;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//设置技能格子
			writeInt(_grid);
			//设置技能ID
			writeInt(_skillId);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//设置技能格子
			_grid = readInt();
			//设置技能ID
			_skillId = readInt();
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 162205;
		}
		
		/**
		 * get 设置技能格子
		 * @return 
		 */
		public function get grid(): int{
			return _grid;
		}
		
		/**
		 * set 设置技能格子
		 */
		public function set grid(value: int): void{
			this._grid = value;
		}
		
		/**
		 * get 设置技能ID
		 * @return 
		 */
		public function get skillId(): int{
			return _skillId;
		}
		
		/**
		 * set 设置技能ID
		 */
		public function set skillId(value: int): void{
			this._skillId = value;
		}
		
	}
}