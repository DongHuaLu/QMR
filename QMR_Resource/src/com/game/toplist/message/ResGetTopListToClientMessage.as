package com.game.toplist.message{
	import com.game.toplist.bean.TopInfo;
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 发送给客户端获取排行榜信息
	 */
	public class ResGetTopListToClientMessage extends Message {
	
		//错误代码
		private var _errorcode: int;
		
		//自己今天膜拜次数
		private var _worshipnum: int;
		
		//排行类型 1等级 2坐骑 3武功 4龙元 5 连斩 6 侍宠 7 战斗力
		private var _toptype: int;
		
		//前5排行榜信息列表(世界)
		private var _top5infoWorldList: Vector.<TopInfo> = new Vector.<TopInfo>();
		//自己排行榜信息列表(世界)
		private var _topselfinfoWorldList: Vector.<TopInfo> = new Vector.<TopInfo>();
		//前5排行榜信息列表(本国)
		private var _top5infoCountryList: Vector.<TopInfo> = new Vector.<TopInfo>();
		//自己排行榜信息列表(本国)
		private var _topselfinfoCountryList: Vector.<TopInfo> = new Vector.<TopInfo>();
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			var i: int = 0;
			//错误代码
			writeByte(_errorcode);
			//自己今天膜拜次数
			writeByte(_worshipnum);
			//排行类型 1等级 2坐骑 3武功 4龙元 5 连斩 6 侍宠 7 战斗力
			writeByte(_toptype);
			//前5排行榜信息列表(世界)
			writeShort(_top5infoWorldList.length);
			for (i = 0; i < _top5infoWorldList.length; i++) {
				writeBean(_top5infoWorldList[i]);
			}
			//自己排行榜信息列表(世界)
			writeShort(_topselfinfoWorldList.length);
			for (i = 0; i < _topselfinfoWorldList.length; i++) {
				writeBean(_topselfinfoWorldList[i]);
			}
			//前5排行榜信息列表(本国)
			writeShort(_top5infoCountryList.length);
			for (i = 0; i < _top5infoCountryList.length; i++) {
				writeBean(_top5infoCountryList[i]);
			}
			//自己排行榜信息列表(本国)
			writeShort(_topselfinfoCountryList.length);
			for (i = 0; i < _topselfinfoCountryList.length; i++) {
				writeBean(_topselfinfoCountryList[i]);
			}
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			var i: int = 0;
			//错误代码
			_errorcode = readByte();
			//自己今天膜拜次数
			_worshipnum = readByte();
			//排行类型 1等级 2坐骑 3武功 4龙元 5 连斩 6 侍宠 7 战斗力
			_toptype = readByte();
			//前5排行榜信息列表(世界)
			var top5infoWorldList_length : int = readShort();
			for (i = 0; i < top5infoWorldList_length; i++) {
				_top5infoWorldList[i] = readBean(TopInfo) as TopInfo;
			}
			//自己排行榜信息列表(世界)
			var topselfinfoWorldList_length : int = readShort();
			for (i = 0; i < topselfinfoWorldList_length; i++) {
				_topselfinfoWorldList[i] = readBean(TopInfo) as TopInfo;
			}
			//前5排行榜信息列表(本国)
			var top5infoCountryList_length : int = readShort();
			for (i = 0; i < top5infoCountryList_length; i++) {
				_top5infoCountryList[i] = readBean(TopInfo) as TopInfo;
			}
			//自己排行榜信息列表(本国)
			var topselfinfoCountryList_length : int = readShort();
			for (i = 0; i < topselfinfoCountryList_length; i++) {
				_topselfinfoCountryList[i] = readBean(TopInfo) as TopInfo;
			}
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 142101;
		}
		
		/**
		 * get 错误代码
		 * @return 
		 */
		public function get errorcode(): int{
			return _errorcode;
		}
		
		/**
		 * set 错误代码
		 */
		public function set errorcode(value: int): void{
			this._errorcode = value;
		}
		
		/**
		 * get 自己今天膜拜次数
		 * @return 
		 */
		public function get worshipnum(): int{
			return _worshipnum;
		}
		
		/**
		 * set 自己今天膜拜次数
		 */
		public function set worshipnum(value: int): void{
			this._worshipnum = value;
		}
		
		/**
		 * get 排行类型 1等级 2坐骑 3武功 4龙元 5 连斩 6 侍宠 7 战斗力
		 * @return 
		 */
		public function get toptype(): int{
			return _toptype;
		}
		
		/**
		 * set 排行类型 1等级 2坐骑 3武功 4龙元 5 连斩 6 侍宠 7 战斗力
		 */
		public function set toptype(value: int): void{
			this._toptype = value;
		}
		
		/**
		 * get 前5排行榜信息列表(世界)
		 * @return 
		 */
		public function get top5infoWorldList(): Vector.<TopInfo>{
			return _top5infoWorldList;
		}
		
		/**
		 * set 前5排行榜信息列表(世界)
		 */
		public function set top5infoWorldList(value: Vector.<TopInfo>): void{
			this._top5infoWorldList = value;
		}
		
		/**
		 * get 自己排行榜信息列表(世界)
		 * @return 
		 */
		public function get topselfinfoWorldList(): Vector.<TopInfo>{
			return _topselfinfoWorldList;
		}
		
		/**
		 * set 自己排行榜信息列表(世界)
		 */
		public function set topselfinfoWorldList(value: Vector.<TopInfo>): void{
			this._topselfinfoWorldList = value;
		}
		
		/**
		 * get 前5排行榜信息列表(本国)
		 * @return 
		 */
		public function get top5infoCountryList(): Vector.<TopInfo>{
			return _top5infoCountryList;
		}
		
		/**
		 * set 前5排行榜信息列表(本国)
		 */
		public function set top5infoCountryList(value: Vector.<TopInfo>): void{
			this._top5infoCountryList = value;
		}
		
		/**
		 * get 自己排行榜信息列表(本国)
		 * @return 
		 */
		public function get topselfinfoCountryList(): Vector.<TopInfo>{
			return _topselfinfoCountryList;
		}
		
		/**
		 * set 自己排行榜信息列表(本国)
		 */
		public function set topselfinfoCountryList(value: Vector.<TopInfo>): void{
			this._topselfinfoCountryList = value;
		}
		
	}
}