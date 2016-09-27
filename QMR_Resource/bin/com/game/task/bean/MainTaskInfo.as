package com.game.task.bean{
	import com.game.task.bean.TaskAttribute;
	import net.Bean;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 主线任务
	 */
	public class MainTaskInfo extends Bean {
	
		//任务模型
		private var _modelId: int;
		
		//是否完成指定动作 1完成 2未完成 无动作条件时默认为完成
		private var _isFinshAction: int;
		
		//达成物品成数
		private var _permiseGoods: Vector.<TaskAttribute> = new Vector.<TaskAttribute>();
		//达成怪物
		private var _permiseMonster: Vector.<TaskAttribute> = new Vector.<TaskAttribute>();
		//达成成就
		private var _permiseAchieve: Vector.<int> = new Vector.<int>();
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//任务模型
			writeInt(_modelId);
			//是否完成指定动作 1完成 2未完成 无动作条件时默认为完成
			writeByte(_isFinshAction);
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
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//任务模型
			_modelId = readInt();
			//是否完成指定动作 1完成 2未完成 无动作条件时默认为完成
			_isFinshAction = readByte();
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
			return true;
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
		 * get 是否完成指定动作 1完成 2未完成 无动作条件时默认为完成
		 * @return 
		 */
		public function get isFinshAction(): int{
			return _isFinshAction;
		}
		
		/**
		 * set 是否完成指定动作 1完成 2未完成 无动作条件时默认为完成
		 */
		public function set isFinshAction(value: int): void{
			this._isFinshAction = value;
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
		
	}
}