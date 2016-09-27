package com.game.hiddenweapon.message{
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 发送暗器升阶面板信息
	 */
	public class ResHiddenWeaponStageUpPanelMessage extends Message {
	
		//当前升阶经验
		private var _exp: int;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//当前升阶经验
			writeInt(_exp);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//当前升阶经验
			_exp = readInt();
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 162103;
		}
		
		/**
		 * get 当前升阶经验
		 * @return 
		 */
		public function get exp(): int{
			return _exp;
		}
		
		/**
		 * set 当前升阶经验
		 */
		public function set exp(value: int): void{
			this._exp = value;
		}
		
	}
}