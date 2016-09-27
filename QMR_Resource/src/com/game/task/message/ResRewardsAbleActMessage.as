package com.game.task.message{
	import com.game.backpack.bean.ItemInfo;
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 更新在可领取区域物品变更
	 */
	public class ResRewardsAbleActMessage extends Message {
	
		//任务完成待领取的物品
		private var _ableAct: Vector.<com.game.backpack.bean.ItemInfo> = new Vector.<com.game.backpack.bean.ItemInfo>();
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			var i: int = 0;
			//任务完成待领取的物品
			writeShort(_ableAct.length);
			for (i = 0; i < _ableAct.length; i++) {
				writeBean(_ableAct[i]);
			}
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			var i: int = 0;
			//任务完成待领取的物品
			var ableAct_length : int = readShort();
			for (i = 0; i < ableAct_length; i++) {
				_ableAct[i] = readBean(com.game.backpack.bean.ItemInfo) as com.game.backpack.bean.ItemInfo;
			}
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 120103;
		}
		
		/**
		 * get 任务完成待领取的物品
		 * @return 
		 */
		public function get ableAct(): Vector.<com.game.backpack.bean.ItemInfo>{
			return _ableAct;
		}
		
		/**
		 * set 任务完成待领取的物品
		 */
		public function set ableAct(value: Vector.<com.game.backpack.bean.ItemInfo>): void{
			this._ableAct = value;
		}
		
	}
}