package com.game.marriage.message{
	import com.game.utils.long;
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 弹出食用婚宴界面
	 */
	public class ResEdibleInfoToClientMessage extends Message {
	
		//新郎名字
		private var _bridegroom: String;
		
		//新娘名字
		private var _bride: String;
		
		//食物数量
		private var _foodnum: int;
		
		//今日累计获得真气
		private var _totalzhenqi: int;
		
		//今日累计获得经验
		private var _totalexp: int;
		
		//结婚id
		private var _marriageid: long;
		
		//NPC唯一id
		private var _npcid: long;
		
		//婚宴类型，1普通，2大型，3豪华
		private var _type: int;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//新郎名字
			writeString(_bridegroom);
			//新娘名字
			writeString(_bride);
			//食物数量
			writeInt(_foodnum);
			//今日累计获得真气
			writeInt(_totalzhenqi);
			//今日累计获得经验
			writeInt(_totalexp);
			//结婚id
			writeLong(_marriageid);
			//NPC唯一id
			writeLong(_npcid);
			//婚宴类型，1普通，2大型，3豪华
			writeByte(_type);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//新郎名字
			_bridegroom = readString();
			//新娘名字
			_bride = readString();
			//食物数量
			_foodnum = readInt();
			//今日累计获得真气
			_totalzhenqi = readInt();
			//今日累计获得经验
			_totalexp = readInt();
			//结婚id
			_marriageid = readLong();
			//NPC唯一id
			_npcid = readLong();
			//婚宴类型，1普通，2大型，3豪华
			_type = readByte();
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 163113;
		}
		
		/**
		 * get 新郎名字
		 * @return 
		 */
		public function get bridegroom(): String{
			return _bridegroom;
		}
		
		/**
		 * set 新郎名字
		 */
		public function set bridegroom(value: String): void{
			this._bridegroom = value;
		}
		
		/**
		 * get 新娘名字
		 * @return 
		 */
		public function get bride(): String{
			return _bride;
		}
		
		/**
		 * set 新娘名字
		 */
		public function set bride(value: String): void{
			this._bride = value;
		}
		
		/**
		 * get 食物数量
		 * @return 
		 */
		public function get foodnum(): int{
			return _foodnum;
		}
		
		/**
		 * set 食物数量
		 */
		public function set foodnum(value: int): void{
			this._foodnum = value;
		}
		
		/**
		 * get 今日累计获得真气
		 * @return 
		 */
		public function get totalzhenqi(): int{
			return _totalzhenqi;
		}
		
		/**
		 * set 今日累计获得真气
		 */
		public function set totalzhenqi(value: int): void{
			this._totalzhenqi = value;
		}
		
		/**
		 * get 今日累计获得经验
		 * @return 
		 */
		public function get totalexp(): int{
			return _totalexp;
		}
		
		/**
		 * set 今日累计获得经验
		 */
		public function set totalexp(value: int): void{
			this._totalexp = value;
		}
		
		/**
		 * get 结婚id
		 * @return 
		 */
		public function get marriageid(): long{
			return _marriageid;
		}
		
		/**
		 * set 结婚id
		 */
		public function set marriageid(value: long): void{
			this._marriageid = value;
		}
		
		/**
		 * get NPC唯一id
		 * @return 
		 */
		public function get npcid(): long{
			return _npcid;
		}
		
		/**
		 * set NPC唯一id
		 */
		public function set npcid(value: long): void{
			this._npcid = value;
		}
		
		/**
		 * get 婚宴类型，1普通，2大型，3豪华
		 * @return 
		 */
		public function get type(): int{
			return _type;
		}
		
		/**
		 * set 婚宴类型，1普通，2大型，3豪华
		 */
		public function set type(value: int): void{
			this._type = value;
		}
		
	}
}