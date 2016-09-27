package com.game.task.message{
	import com.game.task.bean.RankTaskInfo;
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
	public class ResRankTaskListMessage extends Message {
	
		//军衔任务列表
		private var _tasklist: Vector.<RankTaskInfo> = new Vector.<RankTaskInfo>();
		//完成军衔任务列表
		private var _completetasklist: Vector.<int> = new Vector.<int>();
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			var i: int = 0;
			//军衔任务列表
			writeShort(_tasklist.length);
			for (i = 0; i < _tasklist.length; i++) {
				writeBean(_tasklist[i]);
			}
			//完成军衔任务列表
			writeShort(_completetasklist.length);
			for (i = 0; i < _completetasklist.length; i++) {
				writeInt(_completetasklist[i]);
			}
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			var i: int = 0;
			//军衔任务列表
			var tasklist_length : int = readShort();
			for (i = 0; i < tasklist_length; i++) {
				_tasklist[i] = readBean(RankTaskInfo) as RankTaskInfo;
			}
			//完成军衔任务列表
			var completetasklist_length : int = readShort();
			for (i = 0; i < completetasklist_length; i++) {
				_completetasklist[i] = readInt();
			}
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 120114;
		}
		
		/**
		 * get 军衔任务列表
		 * @return 
		 */
		public function get tasklist(): Vector.<RankTaskInfo>{
			return _tasklist;
		}
		
		/**
		 * set 军衔任务列表
		 */
		public function set tasklist(value: Vector.<RankTaskInfo>): void{
			this._tasklist = value;
		}
		
		/**
		 * get 完成军衔任务列表
		 * @return 
		 */
		public function get completetasklist(): Vector.<int>{
			return _completetasklist;
		}
		
		/**
		 * set 完成军衔任务列表
		 */
		public function set completetasklist(value: Vector.<int>): void{
			this._completetasklist = value;
		}
		
	}
}