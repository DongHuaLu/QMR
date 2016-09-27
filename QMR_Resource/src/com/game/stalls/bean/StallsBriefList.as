package com.game.stalls.bean{
	import com.game.stalls.bean.StallsBriefInfo;
	import net.Bean;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 全部摊位简要信息列表（供前端排序用）
	 */
	public class StallsBriefList extends Bean {
	
		//索引起点
		private var _indexlittle: int;
		
		//索引终点
		private var _indexLarge: int;
		
		//本国在线摊位总数
		private var _stallssnum: int;
		
		//按照类型排序，0，不做处理，1，玩家等级从大到小，2，物品数量，3在售货币，4玩家等级从小到大，5搜索结果
		private var _type: int;
		
		//摊位物品信息列表
		private var _stallsbrieflist: Vector.<StallsBriefInfo> = new Vector.<StallsBriefInfo>();
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//索引起点
			writeInt(_indexlittle);
			//索引终点
			writeInt(_indexLarge);
			//本国在线摊位总数
			writeShort(_stallssnum);
			//按照类型排序，0，不做处理，1，玩家等级从大到小，2，物品数量，3在售货币，4玩家等级从小到大，5搜索结果
			writeByte(_type);
			//摊位物品信息列表
			writeShort(_stallsbrieflist.length);
			for (var i: int = 0; i < _stallsbrieflist.length; i++) {
				writeBean(_stallsbrieflist[i]);
			}
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//索引起点
			_indexlittle = readInt();
			//索引终点
			_indexLarge = readInt();
			//本国在线摊位总数
			_stallssnum = readShort();
			//按照类型排序，0，不做处理，1，玩家等级从大到小，2，物品数量，3在售货币，4玩家等级从小到大，5搜索结果
			_type = readByte();
			//摊位物品信息列表
			var stallsbrieflist_length : int = readShort();
			for (var i: int = 0; i < stallsbrieflist_length; i++) {
				_stallsbrieflist[i] = readBean(StallsBriefInfo) as StallsBriefInfo;
			}
			return true;
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
		
		/**
		 * get 本国在线摊位总数
		 * @return 
		 */
		public function get stallssnum(): int{
			return _stallssnum;
		}
		
		/**
		 * set 本国在线摊位总数
		 */
		public function set stallssnum(value: int): void{
			this._stallssnum = value;
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
		 * get 摊位物品信息列表
		 * @return 
		 */
		public function get stallsbrieflist(): Vector.<StallsBriefInfo>{
			return _stallsbrieflist;
		}
		
		/**
		 * set 摊位物品信息列表
		 */
		public function set stallsbrieflist(value: Vector.<StallsBriefInfo>): void{
			this._stallsbrieflist = value;
		}
		
	}
}