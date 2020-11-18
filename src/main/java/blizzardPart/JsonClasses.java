package blizzardPart;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class JsonClasses {
	public static class AccessToken {
		String access_token;
		String token_type;
		int expires_in;

		public AccessToken() {
			// TODO Auto-generated constructor stub
		}

		public String getAccess_token() {
			return access_token;
		}

		public int getExpires_in() {
			return expires_in;
		}

		public String getToken_type() {
			return token_type;
		}

		public void setAccess_token(String accessToken) {
			this.access_token = accessToken;
		}

		public void setExpires_in(int expiresIn) {
			this.expires_in = expiresIn;
		}

		public void setToken_type(String tokenType) {
			this.token_type = tokenType;
		}
	}

	@JsonIgnoreProperties(ignoreUnknown = true)
	public static class Card implements HearthstoneBean{
		int id;
		int collectible;
		String slug;
		int classId;
		int[] multiClassIds;
		int cardTypeId;
		int cardSetId;
		int rarityId;
		String artistName;
		int health;
		int attack;
		int manaCost;
		String name;
		String text;
		String image;
		String imageGold;
		String flavorText;
		String cropImage;
		int parentId;
		int[] keywordIds;

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public int getCollectible() {
			return collectible;
		}

		public void setCollectible(int collectible) {
			this.collectible = collectible;
		}

		public String getSlug() {
			return slug;
		}

		public void setSlug(String slug) {
			this.slug = slug;
		}

		public int getClassId() {
			return classId;
		}

		public void setClassId(int classId) {
			this.classId = classId;
		}

		public int[] getMultiClassIds() {
			return multiClassIds;
		}

		public void setMultiClassIds(int[] multiClassIds) {
			this.multiClassIds = multiClassIds;
		}

		public int getCardTypeId() {
			return cardTypeId;
		}

		public void setCardTypeId(int cardTypeId) {
			this.cardTypeId = cardTypeId;
		}

		public int getCardSetId() {
			return cardSetId;
		}

		public void setCardSetId(int cardSetId) {
			this.cardSetId = cardSetId;
		}

		public int getRarityId() {
			return rarityId;
		}

		public void setRarityId(int rarityId) {
			this.rarityId = rarityId;
		}

		public String getArtistName() {
			return artistName;
		}

		public void setArtistName(String artistName) {
			this.artistName = artistName;
		}

		public int getHealth() {
			return health;
		}

		public void setHealth(int health) {
			this.health = health;
		}

		public int getAttack() {
			return attack;
		}

		public void setAttack(int attack) {
			this.attack = attack;
		}

		public int getManaCost() {
			return manaCost;
		}

		public void setManaCost(int manaCost) {
			this.manaCost = manaCost;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getText() {
			return text;
		}

		public void setText(String text) {
			this.text = text;
		}

		public String getImage() {
			return image;
		}

		public void setImage(String image) {
			this.image = image;
		}

		public String getImageGold() {
			return imageGold;
		}

		public void setImageGold(String imageGold) {
			this.imageGold = imageGold;
		}

		public String getFlavorText() {
			return flavorText;
		}

		public void setFlavorText(String flavorText) {
			this.flavorText = flavorText;
		}

		public String getCropImage() {
			return cropImage;
		}

		public void setCropImage(String cropImage) {
			this.cropImage = cropImage;
		}

		public int getParentId() {
			return parentId;
		}

		public void setParentId(int parntId) {
			this.parentId = parntId;
		}

		public int[] getKeywordIds() {
			return keywordIds;
		}

		public void setKeywordIds(int[] keywordIds) {
			this.keywordIds = keywordIds;
		}

		// {"id":678,"collectible":0,"slug":"678-treant","classId":2,"multiClassIds":[],"cardTypeId":4,"cardSetId":3,"rarityId":null,"artistName":null,"health":2,"attack":2,"manaCost":2,
		// "name":"Древень","text":"<b>Провокация</b>","image":"https://d15f34w2p8l1cc.cloudfront.net/hearthstone/b21ddb34e13f7f631a00acaecaaebb5dca3cafa233a7114499149b867e15db02.png",
		// "imageGold":"","flavorText":"","cropImage":null,"parentId":36,"keywordIds":[1]}
		public Card() {
			// TODO Auto-generated constructor stub
		}

	}
	
	@JsonIgnoreProperties(ignoreUnknown = true)
	public static class CardsArray {
		Card[] cards;
		int cardCount;
		
		public Card[] getCards() {
			return cards;
		}
		public void setCards(Card[] cards) {
			this.cards = cards;
		}
		public int getCardCount() {
			return cardCount;
		}
		public void setCardCount(int cardCount) {
			this.cardCount = cardCount;
		}
	}

	@JsonIgnoreProperties(ignoreUnknown = true)
	public static class Cards {
		Card[] cards;
		int cardCount;
		int pageCount;
		int page;

		public Cards() {
			// TODO Auto-generated constructor stub
		}

		public Card[] getCards() {
			return cards;
		}

		public void setCards(Card[] cards) {
			this.cards = cards;
		}

		public int getCardCount() {
			return cardCount;
		}

		public void setCardCount(int cardCount) {
			this.cardCount = cardCount;
		}

		public int getPageCount() {
			return pageCount;
		}

		public void setPageCount(int pageCount) {
			this.pageCount = pageCount;
		}

		public int getPage() {
			return page;
		}

		public void setPage(int page) {
			this.page = page;
		}

	}
	@JsonIgnoreProperties(ignoreUnknown = true)
	public static class CardBacks {
		CardBack[] cardBacks;

		public CardBack[] getBacks() {
			return cardBacks;
		}

		public void setCardBacks(CardBack[] backs) {
			this.cardBacks = backs;
		}

	}
	@JsonIgnoreProperties(ignoreUnknown = true)
	public static class CardBack implements HearthstoneBean{
		int id;
		int sortCategory;
		String text;
		String name;
		String image;
		String slug;

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public int getSortCategory() {
			return sortCategory;
		}

		public void setSortCategory(int sortCategory) {
			this.sortCategory = sortCategory;
		}

		public String getText() {
			return text;
		}

		public void setText(String text) {
			this.text = text;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getImage() {
			return image;
		}

		public void setImage(String image) {
			this.image = image;
		}

		public String getSlug() {
			return slug;
		}

		public void setSlug(String slug) {
			this.slug = slug;
		}
	}
}
