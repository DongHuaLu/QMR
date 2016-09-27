package com.game.friend.message{
	import com.game.friend.bean.RelationInfo;
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 返回关系列表
	 */
	public class ResRelationGetListToClientMessage extends Message {
	
		//自己的关系信息
		private var _relationMyInfo: RelationInfo;
		
		//关系信息列表
		private var _relationList: Vector.<RelationInfo> = new Vector.<RelationInfo>();
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			var i: int = 0;
			//自己的关系信息
			writeBean(_relationMyInfo);
			//关系信息列表
			writeShort(_relationList.length);
			for (i = 0; i < _relationList.length; i++) {
				writeBean(_relationList[i]);
			}
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			var i: int = 0;
			//自己的关系信息
			_relationMyInfo = readBean(RelationInfo) as RelationInfo;
			//关系信息列表
			var relationList_length : int = readShort();
			for (i = 0; i < relationList_length; i++) {
				_relationList[i] = readBean(RelationInfo) as RelationInfo;
			}
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 119101;
		}
		
		/**
		 * get 自己的关系信息
		 * @return 
		 */
		public function get relationMyInfo(): RelationInfo{
			return _relationMyInfo;
		}
		
		/**
		 * set 自己的关系信息
		 */
		public function set relationMyInfo(value: RelationInfo): void{
			this._relationMyInfo = value;
		}
		
		/**
		 * get 关系信息列表
		 * @return 
		 */
		public function get relationList(): Vector.<RelationInfo>{
			return _relationList;
		}
		
		/**
		 * set 关系信息列表
		 */
		public function set relationList(value: Vector.<RelationInfo>): void{
			this._relationList = value;
		}
		
	}
}