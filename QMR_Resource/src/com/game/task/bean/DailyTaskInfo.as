package com.game.task.bean{
	import com.game.backpack.bean.ItemInfo;
	import com.game.task.bean.TaskAttribute;
	import net.Bean;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 日常任务
	 */
	public class DailyTaskInfo extends Bean {
	
		//奖励模型ID
		private var _jlId: int;
		
		//完成条件模型ID
		private var _premiseId: int;
		
		//额外奖励模型ID
		private var _otherjlId: int;
		
		//达成物品成数
		private var _permiseGoods: Vector.<TaskAttribute> = new Vector.<TaskAttribute>();
		//达成怪物
		private var _permiseMonster: Vector.<TaskAttribute> = new Vector.<TaskAttribute>();
		//达成成就
		private var _permiseAchieve: Vector.<int> = new Vector.<int>();
		//任务完成奖励物品
		private var _rewards: Vector.<com.game.backpack.bean.ItemInfo> = new Vector.<com.game.backpack.bean.ItemInfo>();
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//奖励模型ID
			writeInt(_jlId);
			//完成条件模型ID
			writeInt(_premiseId);
			//额外奖励模型ID
			writeInt(_otherjlId);
			//达成物品成数
			writeShort(_permiseGoods.length);
			for (var i: int = 0; i < _permiseGoods.length; i++) {
				writeBean(_permiseGoods[i]);
			}
			//达成怪物
			writeShort(_permiseMonster.length);
			for (var i: int = 0; i < _permiseMonster.length; i++) {
				writeBean(_permiseMonster[i]);
			}
			//达成成就
			writeShort(_permiseAchieve.length);
			for (var i: int = 0; i < _permiseAchieve.length; i++) {
				writeInt(_permiseAchieve[i]);
			}
			//任务完成奖励物品
			writeShort(_rewards.length);
			for (var i: int = 0; i < _rewards.length; i++) {
				writeBean(_rewards[i]);
			}
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//奖励模型ID
			_jlId = readInt();
			//完成条件模型ID
			_premiseId = readInt();
			//额外奖励模型ID
			_otherjlId = readInt();
			//达成物品成数
			var permiseGoods_length : int = readShort();
			for (var i: int = 0; i < permiseGoods_length; i++) {
				_permiseGoods[i] = readBean(TaskAttribute) as TaskAttribute;
			}
			//达成怪物
			var permiseMonster_length : int = readShort();
			for (var i: int = 0; i < permiseMonster_length; i++) {
				_permiseMonster[i] = readBean(TaskAttribute) as TaskAttribute;
			}
			//达成成就
			var permiseAchieve_length : int = readShort();
			for (var i: int = 0; i < permiseAchieve_length; i++) {
				_permiseAchieve[i] = readInt();
			}
			//任务完成奖励物品
			var rewards_length : int = readShort();
			for (var i: int = 0; i < rewards_length; i++) {
				_rewards[i] = readBean(com.game.backpack.bean.ItemInfo) as com.game.backpack.bean.ItemInfo;
			}
			return true;
		}
		
		/**
		 * get 奖励模型ID
		 * @return 
		 */
		public function get jlId(): int{
			return _jlId;
		}
		
		/**
		 * set 奖励模型ID
		 */
		public function set jlId(value: int): void{
			this._jlId = value;
		}
		
		/**
		 * get 完成条件模型ID
		 * @return 
		 */
		public function get premiseId(): int{
			return _premiseId;
		}
		
		/**
		 * set 完成条件模型ID
		 */
		public function set premiseId(value: int): void{
			this._premiseId = value;
		}
		
		/**
		 * get 额外奖励模型ID
		 * @return 
		 */
		public function get otherjlId(): int{
			return _otherjlId;
		}
		
		/**
		 * set 额外奖励模型ID
		 */
		public function set otherjlId(value: int): void{
			this._otherjlId = value;
		}
		
		/**
		 * get 达成物品成数
		 * @return 
		 */
		public function get permiseGoods(): Vector.<TaskAttribute>{
			return _permiseGoods;
		}
		
		/**
		 * set 达成物品成数
		 */
		public function set permiseGoods(value: Vector.<TaskAttribute>): void{
			this._permiseGoods = value;
		}
		
		/**
		 * get 达成怪物
		 * @return 
		 */
		public function get permiseMonster(): Vector.<TaskAttribute>{
			return _permiseMonster;
		}
		
		/**
		 * set 达成怪物
		 */
		public function set permiseMonster(value: Vector.<TaskAttribute>): void{
			this._permiseMonster = value;
		}
		
		/**
		 * get 达成成就
		 * @return 
		 */
		public function get permiseAchieve(): Vector.<int>{
			return _permiseAchieve;
		}
		
		/**
		 * set 达成成就
		 */
		public function set permiseAchieve(value: Vector.<int>): void{
			this._permiseAchieve = value;
		}
		
		/**
		 * get 任务完成奖励物品
		 * @return 
		 */
		public function get rewards(): Vector.<com.game.backpack.bean.ItemInfo>{
			return _rewards;
		}
		
		/**
		 * set 任务完成奖励物品
		 */
		public function set rewards(value: Vector.<com.game.backpack.bean.ItemInfo>): void{
			this._rewards = value;
		}
		
	}
}