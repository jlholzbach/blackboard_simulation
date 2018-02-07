import java.io.Serializable;
import java.util.*;

public class Category implements Serializable
	{
		private String name;
		private String strategy; 
		private int weight;
		private String prefixName;
		private boolean hasPrefix;
		private boolean hasMaxScore;
		private int maxScore;
		private int numOfDrops;
		private int ID;
		
		/*
		 * If hasPrefix is false, prefix name can be left blank
		 */
		public Category(String name, String strategy, int weight,boolean hasPrefix, String prefixName, boolean hasMaxScore, int maxScore, int numOfDrops)
		{
			this.name = name;
			this.strategy = strategy;
			this.weight = weight;
			this.hasPrefix=hasPrefix;
			if(hasPrefix)
				this.prefixName = prefixName;
			else
				this.prefixName="";
			this.hasMaxScore = hasMaxScore;
			this.maxScore = maxScore;
			this.numOfDrops = numOfDrops;
			this.ID=-1;
		}
		
		/*
		 * If hasPrefix is false, prefix name can be left blank
		 */
		public Category(int id,String name, String strategy, int weight,boolean hasPrefix, String prefixName,boolean hasMaxScore, int maxScore, int numOfDrops)
		{
			this.name = name;
			this.strategy = strategy;
			this.weight = weight;
			this.hasPrefix=hasPrefix;
			if(hasPrefix)
				this.prefixName = prefixName;
			else
				this.prefixName="";
			this.hasMaxScore = hasMaxScore;
			this.maxScore = maxScore;
			this.numOfDrops = numOfDrops;
			this.ID=id;
		}
		
		public void setCategoryName(String name)
			{
				this.name = name;
			}
		
		public void setCategoryStrategy(String strategy)
			{
				this.strategy = strategy;
			}
		
		public String getCategoryStrategy()
			{
				return this.strategy;
			}
		
		public void setCategoryWeight(int weight)
			{
				this.weight = weight;
			}
		
		public void setPrefixStatus(boolean prefix)
			{
				this.hasPrefix = prefix;
			}
		
		public boolean getPrefixStatus()
			{
				return this.hasPrefix;
			}
				
		public void setPrefixName(String prefixName)
			{
				this.prefixName = prefixName;
			}
		
		public String getPrefix()
			{
				return this.prefixName;
			}
				
		public void setScore(int score)
			{
				this.maxScore = score;
			}
		
		public void setNumofDrops(int numOfDrops)
			{
				this.numOfDrops = numOfDrops;
					
			}
		
		public void setHasMaxScore(boolean have)
			{
				this.hasMaxScore = have;
			}
		
		public boolean getHasMaxScore()
			{
				return this.hasMaxScore;
			}
		
		public int getScore()
			{
				return this.maxScore;
			}
				
		public String getCategoryName()
			{
				return this.name;
			}
		public void setID(int id)
		{
			this.ID=id;
		}
		
		public int getCategoryWeight()
			{
				return this.weight;
			}
		
		public double getCategoryWeightPercent()
		{
			return (getCategoryWeight())/(100.0);
		}
		
		public int getMaxScore()
		{
			return this.maxScore;
		}

		public int getNumDrops() {
			return this.numOfDrops;
		}

		public int getID() {
			return ID;
		}

		@Override
		
		public String toString()
		{
			return "Category name=" + name+"";		
		}
		
		public String toStringVerbose() {
			return "Category [name=" + name + ", strategy=" + strategy
					+ ", weight=" + weight + ", prefixName=" + prefixName
					+ ", hasPrefix=" + hasPrefix + ", hasMaxScore="
					+ hasMaxScore + ", maxScore=" + maxScore + ", numOfDrops="
					+ numOfDrops + ", ID=" + ID + "]";
		}
		
	}