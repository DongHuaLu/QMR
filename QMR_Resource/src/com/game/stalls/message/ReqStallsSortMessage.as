package com.game.stalls.message{
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 摊位条件排序（目前是前端做排序，这里返回新数据给前端）
	 */
	public class ReqStallsSortMessage extends Message {
	
		//按照类型排序，0，不做处理，1，玩家等级从大到小，2，物品数量，3在售货币，4玩家等级从小到大，5搜索结果
		private var _type: int;
		
		//索引起点
		private var _indexlittle: int;
		
		//索引终点
		private var _indexLarge: int;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//按照类型排序，0，不做处理，1，玩家等级从大到小，2，物品数量，3在售货币，4玩家等级从小到大，5搜索结果
			writeByte(_type);
			//索引起点
			writeInt(_indexlittle);
			//索引终点
			writeInt(_indexLarge);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//按照类型排序，0，不做处理，1，玩家等级从大到小，2，物品数量，3在售货币，4玩家等级从小到大，5搜索结果
			_type = readByte();
			//索引起点
			_indexlittle = readInt();
			//索引终点
			_indexLarge = readInt();
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 123203;
		}
		
		/**
		 * get 按照类型排序，0，不做处理，1，玩家等级从大到小，2，物品数量，3在售货币，4玩家等级从小到大，5搜索结果
		 * @return 
		 */
		public function get type(): int{
			return _type;
		}
		
		/**
		 * set 按照类型排序，0，不做处理，1，玩家等级从大到小，2，物品数量，3在售货币，4玩家等级从小到大，5搜索结果
		 */
		public function set type(value: int): void{
			this._type = value;
		}
		
		/**
		 * get 索引起点
		 * @return 
		 */
		public function get indexlittle(): int{
			return _indexlittle;
		}
		
		/**
		 * set 索引起点
		 */
		public function set indexlittle(value: int): void{
			this._indexlittle = value;
		}
		
		/**
		 * get 索引终点
		 * @return 
		 */
		public function get indexLarge(): int{
			return _indexLarge;
		}
		
		/**
		 * set 索引终点
		 */
		public function set indexLarge(value: int): void{
			this._indexLarge = value;
		}
		
	}
}