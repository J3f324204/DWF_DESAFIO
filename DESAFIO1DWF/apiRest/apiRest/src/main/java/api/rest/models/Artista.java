package api.rest.models;
public class Artista {
 private int id;
 private String name;
private String desc;
 /**
 * @return the id
 */
 public int getId() {
 return id;
 }
 /**
 * @param id the id to set
 */
 public void setId(int id) {
 this.id = id;
 }
 /**
 * @return the name
 */
 public String getName() {
 return name;
 }
 /**
 * @param name the name to set
 */
 public void setName(String name) {
 this.name = name;
 }
 
 public String getDesc() {
	 return desc;
 }
 /**
 * @param name the name to set
 */
 public void setDesc(String desc) {
 this.desc = desc;
 }
}