package com.game.prompt.message{
	import com.game.chat.bean.GoodsInfoRes;
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 个人通知
	 */
	public class PersonalNoticeMessage extends Message {
	
		//信息类型 1-错误， 2-提示， 3-成功 （暂定，具体见文档）
		private var _type: int;
		
		//信息内容
		private var _content: String;
		
		//付费引导类型
		private var _subtype: int;
		
		//数值
		private var _values: Vector.<String> = new Vector.<String>();
		//物品内容
		private var _goodsinfos: Vector.<com.game.chat.bean.GoodsInfoRes> = new Vector.<com.game.chat.bean.GoodsInfoRes>();
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			var i: int = 0;
			//信息类型 1-错误， 2-提示， 3-成功 （暂定，具体见文档）
			writeByte(_type);
			//信息内容
			writeString(_content);
			//付费引导类型
			writeInt(_subtype);
			//数值
			writeShort(_values.length);
			for (i = 0; i < _values.length; i++) {
				writeString(_values[i]);
			}
			//物品内容
			writeShort(_goodsinfos.length);
			for (i = 0; i < _goodsinfos.length; i++) {
				writeBean(_goodsinfos[i]);
			}
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			var i: int = 0;
			//信息类型 1-错误， 2-提示， 3-成功 （暂定，具体见文档）
			_type = readByte();
			//信息内容
			_content = readString();
			//付费引导类型
			_subtype = readInt();
			//数值
			var values_length : int = readShort();
			for (i = 0; i < values_length; i++) {
				_values[i] = readString();
			}
			//物品内容
			var goodsinfos_length : int = readShort();
			for (i = 0; i < goodsinfos_length; i++) {
				_goodsinfos[i] = readBean(com.game.chat.bean.GoodsInfoRes) as com.game.chat.bean.GoodsInfoRes;
			}
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 109102;
		}
		
		/**
		 * get 信息类型 1-错误， 2-提示， 3-成功 （暂定，具体见文档）
		 * @return 
		 */
		public function get type(): int{
			return _type;
		}
		
		/**
		 * set 信息类型 1-错误， 2-提示， 3-成功 （暂定，具体见文档）
		 */
		public function set type(value: int): void{
			this._type = value;
		}
		
		/**
		 * get 信息内容
		 * @return 
		 */
		public function get content(): String{
			return _content;
		}
		
		/**
		 * set 信息内容
		 */
		public function set content(value: String): void{
			this._content = value;
		}
		
		/**
		 * get 付费引导类型
		 * @return 
		 */
		public function get subtype(): int{
			return _subtype;
		}
		
		/**
		 * set 付费引导类型
		 */
		public function set subtype(value: int): void{
			this._subtype = value;
		}
		
		/**
		 * get 数值
		 * @return 
		 */
		public function get values(): Vector.<String>{
			return _values;
		}
		
		/**
		 * set 数值
		 */
		public function set values(value: Vector.<String>): void{
			this._values = value;
		}
		
		/**
		 * get 物品内容
		 * @return 
		 */
		public function get goodsinfos(): Vector.<com.game.chat.bean.GoodsInfoRes>{
			return _goodsinfos;
		}
		
		/**
		 * set 物品内容
		 */
		public function set goodsinfos(value: Vector.<com.game.chat.bean.GoodsInfoRes>): void{
			this._goodsinfos = value;
		}
		
	}
}