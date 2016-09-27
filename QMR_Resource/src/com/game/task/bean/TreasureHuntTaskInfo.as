package com.game.task.bean{
	import com.game.utils.long;
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
	 * 寻宝任务
	 */
	public class TreasureHuntTaskInfo extends Bean {
	
		//任务ID
		private var _id: long;
		
		//任务模型
		private var _modelId: int;
		
		//达成物品成数
		private var _permiseGoods: Vector.<TaskAttribute> = new Vector.<TaskAttribute>();
		//达成怪物
		private var _permiseMonster: Vector.<TaskAttribute> = new Vector.<TaskAttribute>();
		//达成成就
		private var _permiseAchieve: Vector.<int> = new Vector.<int>();
		//奖励经验
		private var _rewardsExp: int;
		
		//奖励铜币
		private var _rewardsCopper: int;
		
		//奖励真气
		private var _rewardsZQ: int;
		
		//奖励声望
		private var _rewardsSW: int;
		
		//奖励绑定元宝
		private var _rewardsBindGold: int;
		
		//任务完成奖励物品
		private var _rewards: Vector.<com.game.backpack.bean.ItemInfo> = new Vector.<com.game.backpack.bean.ItemInfo>();
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//任务ID
			writeLong(_id);
			//任务模型
			writeInt(_modelId);
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
			//奖励经验
			writeInt(_rewardsExp);
			//奖励铜币
			writeInt(_rewardsCopper);
			//奖励真气
			writeInt(_rewardsZQ);
			//奖励声望
			writeInt(_rewardsSW);
			//奖励绑定元宝
			writeInt(_rewardsBindGold);
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
			//任务ID
			_id = readLong();
			//任务模型
			_modelId = readInt();
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
			//奖励经验
			_rewardsExp = readInt();
			//奖励铜币
			_rewardsCopper = readInt();
			//奖励真气
			_rewardsZQ = readInt();
			//奖励声望
			_rewardsSW = readInt();
			//奖励绑定元宝
			_rewardsBindGold = readInt();
			//任务完成奖励物品
			var rewards_length : int = readShort();
			for (var i: int = 0; i < rewards_length; i++) {
				_rewards[i] = readBean(com.game.backpack.bean.ItemInfo) as com.game.backpack.bean.ItemInfo;
			}
			return true;
		}
		
		/**
		 * get 任务ID
		 * @return 
		 */
		public function get id(): long{
			return _id;
		}
		
		/**
		 * set 任务ID
		 */
		public function set id(value: long): void{
			this._id = value;
		}
		
		/**
		 * get 任务模型
		 * @return 
		 */
		public function get modelId(): int{
			return _modelId;
		}
		
		/**
		 * set 任务模型
		 */
		public function set modelId(value: int): void{
			this._modelId = value;
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
		 * get 奖励经验
		 * @return 
		 */
		public function get rewardsExp(): int{
			return _rewardsExp;
		}
		
		/**
		 * set 奖励经验
		 */
		public function set rewardsExp(value: int): void{
			this._rewardsExp = value;
		}
		
		/**
		 * get 奖励铜币
		 * @return 
		 */
		public function get rewardsCopper(): int{
			return _rewardsCopper;
		}
		
		/**
		 * set 奖励铜币
		 */
		public function set rewardsCopper(value: int): void{
			this._rewardsCopper = value;
		}
		
		/**
		 * get 奖励真气
		 * @return 
		 */
		public function get rewardsZQ(): int{
			return _rewardsZQ;
		}
		
		/**
		 * set 奖励真气
		 */
		public function set rewardsZQ(value: int): void{
			this._rewardsZQ = value;
		}
		
		/**
		 * get 奖励声望
		 * @return 
		 */
		public function get rewardsSW(): int{
			return _rewardsSW;
		}
		
		/**
		 * set 奖励声望
		 */
		public function set rewardsSW(value: int): void{
			this._rewardsSW = value;
		}
		
		/**
		 * get 奖励绑定元宝
		 * @return 
		 */
		public function get rewardsBindGold(): int{
			return _rewardsBindGold;
		}
		
		/**
		 * set 奖励绑定元宝
		 */
		public function set rewardsBindGold(value: int): void{
			this._rewardsBindGold = value;
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