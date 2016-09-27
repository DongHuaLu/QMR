package com.game.task.message{
	import com.game.task.bean.ConquerTaskInfo;
	import com.game.backpack.bean.ItemInfo;
	import com.game.task.bean.MainTaskInfo;
	import com.game.task.bean.DailyTaskInfo;
	import com.game.task.bean.TreasureHuntTaskInfo;
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 任务列表
	 */
	public class ResTaskListMessage extends Message {
	
		//当日日常任务己接取次数
		private var _daylyTaskacceptcount: int;
		
		//当日讨伐接取次数
		private var _conquerTaskAcceptCount: int;
		
		//当日讨伐接取最大次数
		private var _conquerTaskAcceptMaxCount: int;
		
		//今日吞噬数
		private var _devourCount: int;
		
		//日常任务列表
		private var _DailyTask: Vector.<DailyTaskInfo> = new Vector.<DailyTaskInfo>();
		//讨伐任务
		private var _conqueryTask: Vector.<ConquerTaskInfo> = new Vector.<ConquerTaskInfo>();
		//主线任务
		private var _mainTask: Vector.<MainTaskInfo> = new Vector.<MainTaskInfo>();
		//寻宝任务
		private var _treasureHuntTask: Vector.<TreasureHuntTaskInfo> = new Vector.<TreasureHuntTaskInfo>();
		//可领取
		private var _ableAct: Vector.<com.game.backpack.bean.ItemInfo> = new Vector.<com.game.backpack.bean.ItemInfo>();
		//是否有新的日常任务1是 0否
		private var _ishasnewdailytask: int;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			var i: int = 0;
			//当日日常任务己接取次数
			writeInt(_daylyTaskacceptcount);
			//当日讨伐接取次数
			writeInt(_conquerTaskAcceptCount);
			//当日讨伐接取最大次数
			writeInt(_conquerTaskAcceptMaxCount);
			//今日吞噬数
			writeInt(_devourCount);
			//日常任务列表
			writeShort(_DailyTask.length);
			for (i = 0; i < _DailyTask.length; i++) {
				writeBean(_DailyTask[i]);
			}
			//讨伐任务
			writeShort(_conqueryTask.length);
			for (i = 0; i < _conqueryTask.length; i++) {
				writeBean(_conqueryTask[i]);
			}
			//主线任务
			writeShort(_mainTask.length);
			for (i = 0; i < _mainTask.length; i++) {
				writeBean(_mainTask[i]);
			}
			//寻宝任务
			writeShort(_treasureHuntTask.length);
			for (i = 0; i < _treasureHuntTask.length; i++) {
				writeBean(_treasureHuntTask[i]);
			}
			//可领取
			writeShort(_ableAct.length);
			for (i = 0; i < _ableAct.length; i++) {
				writeBean(_ableAct[i]);
			}
			//是否有新的日常任务1是 0否
			writeInt(_ishasnewdailytask);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			var i: int = 0;
			//当日日常任务己接取次数
			_daylyTaskacceptcount = readInt();
			//当日讨伐接取次数
			_conquerTaskAcceptCount = readInt();
			//当日讨伐接取最大次数
			_conquerTaskAcceptMaxCount = readInt();
			//今日吞噬数
			_devourCount = readInt();
			//日常任务列表
			var DailyTask_length : int = readShort();
			for (i = 0; i < DailyTask_length; i++) {
				_DailyTask[i] = readBean(DailyTaskInfo) as DailyTaskInfo;
			}
			//讨伐任务
			var conqueryTask_length : int = readShort();
			for (i = 0; i < conqueryTask_length; i++) {
				_conqueryTask[i] = readBean(ConquerTaskInfo) as ConquerTaskInfo;
			}
			//主线任务
			var mainTask_length : int = readShort();
			for (i = 0; i < mainTask_length; i++) {
				_mainTask[i] = readBean(MainTaskInfo) as MainTaskInfo;
			}
			//寻宝任务
			var treasureHuntTask_length : int = readShort();
			for (i = 0; i < treasureHuntTask_length; i++) {
				_treasureHuntTask[i] = readBean(TreasureHuntTaskInfo) as TreasureHuntTaskInfo;
			}
			//可领取
			var ableAct_length : int = readShort();
			for (i = 0; i < ableAct_length; i++) {
				_ableAct[i] = readBean(com.game.backpack.bean.ItemInfo) as com.game.backpack.bean.ItemInfo;
			}
			//是否有新的日常任务1是 0否
			_ishasnewdailytask = readInt();
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 120101;
		}
		
		/**
		 * get 当日日常任务己接取次数
		 * @return 
		 */
		public function get daylyTaskacceptcount(): int{
			return _daylyTaskacceptcount;
		}
		
		/**
		 * set 当日日常任务己接取次数
		 */
		public function set daylyTaskacceptcount(value: int): void{
			this._daylyTaskacceptcount = value;
		}
		
		/**
		 * get 当日讨伐接取次数
		 * @return 
		 */
		public function get conquerTaskAcceptCount(): int{
			return _conquerTaskAcceptCount;
		}
		
		/**
		 * set 当日讨伐接取次数
		 */
		public function set conquerTaskAcceptCount(value: int): void{
			this._conquerTaskAcceptCount = value;
		}
		
		/**
		 * get 当日讨伐接取最大次数
		 * @return 
		 */
		public function get conquerTaskAcceptMaxCount(): int{
			return _conquerTaskAcceptMaxCount;
		}
		
		/**
		 * set 当日讨伐接取最大次数
		 */
		public function set conquerTaskAcceptMaxCount(value: int): void{
			this._conquerTaskAcceptMaxCount = value;
		}
		
		/**
		 * get 今日吞噬数
		 * @return 
		 */
		public function get devourCount(): int{
			return _devourCount;
		}
		
		/**
		 * set 今日吞噬数
		 */
		public function set devourCount(value: int): void{
			this._devourCount = value;
		}
		
		/**
		 * get 日常任务列表
		 * @return 
		 */
		public function get DailyTask(): Vector.<DailyTaskInfo>{
			return _DailyTask;
		}
		
		/**
		 * set 日常任务列表
		 */
		public function set DailyTask(value: Vector.<DailyTaskInfo>): void{
			this._DailyTask = value;
		}
		
		/**
		 * get 讨伐任务
		 * @return 
		 */
		public function get conqueryTask(): Vector.<ConquerTaskInfo>{
			return _conqueryTask;
		}
		
		/**
		 * set 讨伐任务
		 */
		public function set conqueryTask(value: Vector.<ConquerTaskInfo>): void{
			this._conqueryTask = value;
		}
		
		/**
		 * get 主线任务
		 * @return 
		 */
		public function get mainTask(): Vector.<MainTaskInfo>{
			return _mainTask;
		}
		
		/**
		 * set 主线任务
		 */
		public function set mainTask(value: Vector.<MainTaskInfo>): void{
			this._mainTask = value;
		}
		
		/**
		 * get 寻宝任务
		 * @return 
		 */
		public function get treasureHuntTask(): Vector.<TreasureHuntTaskInfo>{
			return _treasureHuntTask;
		}
		
		/**
		 * set 寻宝任务
		 */
		public function set treasureHuntTask(value: Vector.<TreasureHuntTaskInfo>): void{
			this._treasureHuntTask = value;
		}
		
		/**
		 * get 可领取
		 * @return 
		 */
		public function get ableAct(): Vector.<com.game.backpack.bean.ItemInfo>{
			return _ableAct;
		}
		
		/**
		 * set 可领取
		 */
		public function set ableAct(value: Vector.<com.game.backpack.bean.ItemInfo>): void{
			this._ableAct = value;
		}
		
		/**
		 * get 是否有新的日常任务1是 0否
		 * @return 
		 */
		public function get ishasnewdailytask(): int{
			return _ishasnewdailytask;
		}
		
		/**
		 * set 是否有新的日常任务1是 0否
		 */
		public function set ishasnewdailytask(value: int): void{
			this._ishasnewdailytask = value;
		}
		
	}
}