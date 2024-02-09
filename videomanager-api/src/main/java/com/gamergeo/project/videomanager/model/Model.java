package com.gamergeo.project.videomanager.model;

import jakarta.persistence.Access;
import jakarta.persistence.AccessType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.MappedSuperclass;
import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleLongProperty;

@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@MappedSuperclass
@Access(AccessType.PROPERTY)
public abstract class Model {
	
	private LongProperty id = new SimpleLongProperty();
	
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Model model = (Model) o;
        
        return id.get() == model.idProperty().get();
    }
    
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	public long getId() {
		return this.idProperty().get();
	}
	
	public LongProperty idProperty() {
		return this.id;
	}
	
	public void setId(final long id) {
		this.idProperty().set(id);
	}
	
	@Override
	public String toString() {
		return "Model " + this.getClass().getSimpleName().toLowerCase() + " (id="+ getId() + ")";
	}
}
