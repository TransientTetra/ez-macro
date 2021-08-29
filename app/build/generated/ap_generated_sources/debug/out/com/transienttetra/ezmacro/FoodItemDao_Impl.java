package com.transienttetra.ezmacro;

import android.database.Cursor;
import androidx.lifecycle.LiveData;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.transienttetra.ezmacro.entities.FoodItem;
import com.transienttetra.ezmacro.entities.Nutrition;

import java.lang.Class;
import java.lang.Exception;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;

@SuppressWarnings({"unchecked", "deprecation"})
public final class FoodItemDao_Impl implements FoodItemDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<FoodItem> __insertionAdapterOfFoodItem;

  private final EntityDeletionOrUpdateAdapter<FoodItem> __deletionAdapterOfFoodItem;

  private final EntityDeletionOrUpdateAdapter<FoodItem> __updateAdapterOfFoodItem;

  private final SharedSQLiteStatement __preparedStmtOfDeleteAll;

  public FoodItemDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfFoodItem = new EntityInsertionAdapter<FoodItem>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR REPLACE INTO `FoodItem` (`foodItemId`,`name`,`description`,`barcode`,`servings`,`weight`,`isFavorite`,`energy`,`protein`,`fats`,`carbohydrates`) VALUES (nullif(?, 0),?,?,?,?,?,?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, FoodItem value) {
        stmt.bindLong(1, value.getFoodItemId());
        if (value.getName() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getName());
        }
        if (value.getDescription() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getDescription());
        }
        if (value.getBarcode() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getBarcode());
        }
        stmt.bindDouble(5, value.getServings());
        stmt.bindDouble(6, value.getWeight());
        final int _tmp;
        _tmp = value.isFavorite() ? 1 : 0;
        stmt.bindLong(7, _tmp);
        final Nutrition _tmpNutrition = value.getNutrition();
        if(_tmpNutrition != null) {
          stmt.bindDouble(8, _tmpNutrition.getEnergy());
          stmt.bindDouble(9, _tmpNutrition.getProtein());
          stmt.bindDouble(10, _tmpNutrition.getFats());
          stmt.bindDouble(11, _tmpNutrition.getCarbohydrates());
        } else {
          stmt.bindNull(8);
          stmt.bindNull(9);
          stmt.bindNull(10);
          stmt.bindNull(11);
        }
      }
    };
    this.__deletionAdapterOfFoodItem = new EntityDeletionOrUpdateAdapter<FoodItem>(__db) {
      @Override
      public String createQuery() {
        return "DELETE FROM `FoodItem` WHERE `foodItemId` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, FoodItem value) {
        stmt.bindLong(1, value.getFoodItemId());
      }
    };
    this.__updateAdapterOfFoodItem = new EntityDeletionOrUpdateAdapter<FoodItem>(__db) {
      @Override
      public String createQuery() {
        return "UPDATE OR ABORT `FoodItem` SET `foodItemId` = ?,`name` = ?,`description` = ?,`barcode` = ?,`servings` = ?,`weight` = ?,`isFavorite` = ?,`energy` = ?,`protein` = ?,`fats` = ?,`carbohydrates` = ? WHERE `foodItemId` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, FoodItem value) {
        stmt.bindLong(1, value.getFoodItemId());
        if (value.getName() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getName());
        }
        if (value.getDescription() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getDescription());
        }
        if (value.getBarcode() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getBarcode());
        }
        stmt.bindDouble(5, value.getServings());
        stmt.bindDouble(6, value.getWeight());
        final int _tmp;
        _tmp = value.isFavorite() ? 1 : 0;
        stmt.bindLong(7, _tmp);
        final Nutrition _tmpNutrition = value.getNutrition();
        if(_tmpNutrition != null) {
          stmt.bindDouble(8, _tmpNutrition.getEnergy());
          stmt.bindDouble(9, _tmpNutrition.getProtein());
          stmt.bindDouble(10, _tmpNutrition.getFats());
          stmt.bindDouble(11, _tmpNutrition.getCarbohydrates());
        } else {
          stmt.bindNull(8);
          stmt.bindNull(9);
          stmt.bindNull(10);
          stmt.bindNull(11);
        }
        stmt.bindLong(12, value.getFoodItemId());
      }
    };
    this.__preparedStmtOfDeleteAll = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "DELETE FROM FoodItem";
        return _query;
      }
    };
  }

  @Override
  public void insert(final FoodItem foodItem) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfFoodItem.insert(foodItem);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void delete(final FoodItem foodItem) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __deletionAdapterOfFoodItem.handle(foodItem);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void update(final FoodItem foodItem) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __updateAdapterOfFoodItem.handle(foodItem);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void deleteAll() {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteAll.acquire();
    __db.beginTransaction();
    try {
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfDeleteAll.release(_stmt);
    }
  }

  @Override
  public LiveData<List<FoodItem>> getAll() {
    final String _sql = "SELECT * FROM FoodItem ORDER BY foodItemId";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return __db.getInvalidationTracker().createLiveData(new String[]{"FoodItem"}, false, new Callable<List<FoodItem>>() {
      @Override
      public List<FoodItem> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfFoodItemId = CursorUtil.getColumnIndexOrThrow(_cursor, "foodItemId");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
          final int _cursorIndexOfBarcode = CursorUtil.getColumnIndexOrThrow(_cursor, "barcode");
          final int _cursorIndexOfServings = CursorUtil.getColumnIndexOrThrow(_cursor, "servings");
          final int _cursorIndexOfWeight = CursorUtil.getColumnIndexOrThrow(_cursor, "weight");
          final int _cursorIndexOfIsFavorite = CursorUtil.getColumnIndexOrThrow(_cursor, "isFavorite");
          final int _cursorIndexOfEnergy = CursorUtil.getColumnIndexOrThrow(_cursor, "energy");
          final int _cursorIndexOfProtein = CursorUtil.getColumnIndexOrThrow(_cursor, "protein");
          final int _cursorIndexOfFats = CursorUtil.getColumnIndexOrThrow(_cursor, "fats");
          final int _cursorIndexOfCarbohydrates = CursorUtil.getColumnIndexOrThrow(_cursor, "carbohydrates");
          final List<FoodItem> _result = new ArrayList<FoodItem>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final FoodItem _item;
            final String _tmpName;
            if (_cursor.isNull(_cursorIndexOfName)) {
              _tmpName = null;
            } else {
              _tmpName = _cursor.getString(_cursorIndexOfName);
            }
            final String _tmpDescription;
            if (_cursor.isNull(_cursorIndexOfDescription)) {
              _tmpDescription = null;
            } else {
              _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
            }
            final String _tmpBarcode;
            if (_cursor.isNull(_cursorIndexOfBarcode)) {
              _tmpBarcode = null;
            } else {
              _tmpBarcode = _cursor.getString(_cursorIndexOfBarcode);
            }
            final float _tmpServings;
            _tmpServings = _cursor.getFloat(_cursorIndexOfServings);
            final float _tmpWeight;
            _tmpWeight = _cursor.getFloat(_cursorIndexOfWeight);
            final boolean _tmpIsFavorite;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIsFavorite);
            _tmpIsFavorite = _tmp != 0;
            final Nutrition _tmpNutrition;
            if (! (_cursor.isNull(_cursorIndexOfEnergy) && _cursor.isNull(_cursorIndexOfProtein) && _cursor.isNull(_cursorIndexOfFats) && _cursor.isNull(_cursorIndexOfCarbohydrates))) {
              final float _tmpEnergy;
              _tmpEnergy = _cursor.getFloat(_cursorIndexOfEnergy);
              final float _tmpProtein;
              _tmpProtein = _cursor.getFloat(_cursorIndexOfProtein);
              final float _tmpFats;
              _tmpFats = _cursor.getFloat(_cursorIndexOfFats);
              final float _tmpCarbohydrates;
              _tmpCarbohydrates = _cursor.getFloat(_cursorIndexOfCarbohydrates);
              _tmpNutrition = new Nutrition(_tmpEnergy,_tmpProtein,_tmpFats,_tmpCarbohydrates);
            }  else  {
              _tmpNutrition = null;
            }
            _item = new FoodItem(_tmpName,_tmpDescription,_tmpNutrition,_tmpBarcode,_tmpServings,_tmpWeight,_tmpIsFavorite);
            final int _tmpFoodItemId;
            _tmpFoodItemId = _cursor.getInt(_cursorIndexOfFoodItemId);
            _item.setFoodItemId(_tmpFoodItemId);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public LiveData<FoodItem> get(final int id) {
    final String _sql = "SELECT * FROM FoodItem WHERE foodItemId = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, id);
    return __db.getInvalidationTracker().createLiveData(new String[]{"FoodItem"}, false, new Callable<FoodItem>() {
      @Override
      public FoodItem call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfFoodItemId = CursorUtil.getColumnIndexOrThrow(_cursor, "foodItemId");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
          final int _cursorIndexOfBarcode = CursorUtil.getColumnIndexOrThrow(_cursor, "barcode");
          final int _cursorIndexOfServings = CursorUtil.getColumnIndexOrThrow(_cursor, "servings");
          final int _cursorIndexOfWeight = CursorUtil.getColumnIndexOrThrow(_cursor, "weight");
          final int _cursorIndexOfIsFavorite = CursorUtil.getColumnIndexOrThrow(_cursor, "isFavorite");
          final int _cursorIndexOfEnergy = CursorUtil.getColumnIndexOrThrow(_cursor, "energy");
          final int _cursorIndexOfProtein = CursorUtil.getColumnIndexOrThrow(_cursor, "protein");
          final int _cursorIndexOfFats = CursorUtil.getColumnIndexOrThrow(_cursor, "fats");
          final int _cursorIndexOfCarbohydrates = CursorUtil.getColumnIndexOrThrow(_cursor, "carbohydrates");
          final FoodItem _result;
          if(_cursor.moveToFirst()) {
            final String _tmpName;
            if (_cursor.isNull(_cursorIndexOfName)) {
              _tmpName = null;
            } else {
              _tmpName = _cursor.getString(_cursorIndexOfName);
            }
            final String _tmpDescription;
            if (_cursor.isNull(_cursorIndexOfDescription)) {
              _tmpDescription = null;
            } else {
              _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
            }
            final String _tmpBarcode;
            if (_cursor.isNull(_cursorIndexOfBarcode)) {
              _tmpBarcode = null;
            } else {
              _tmpBarcode = _cursor.getString(_cursorIndexOfBarcode);
            }
            final float _tmpServings;
            _tmpServings = _cursor.getFloat(_cursorIndexOfServings);
            final float _tmpWeight;
            _tmpWeight = _cursor.getFloat(_cursorIndexOfWeight);
            final boolean _tmpIsFavorite;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIsFavorite);
            _tmpIsFavorite = _tmp != 0;
            final Nutrition _tmpNutrition;
            if (! (_cursor.isNull(_cursorIndexOfEnergy) && _cursor.isNull(_cursorIndexOfProtein) && _cursor.isNull(_cursorIndexOfFats) && _cursor.isNull(_cursorIndexOfCarbohydrates))) {
              final float _tmpEnergy;
              _tmpEnergy = _cursor.getFloat(_cursorIndexOfEnergy);
              final float _tmpProtein;
              _tmpProtein = _cursor.getFloat(_cursorIndexOfProtein);
              final float _tmpFats;
              _tmpFats = _cursor.getFloat(_cursorIndexOfFats);
              final float _tmpCarbohydrates;
              _tmpCarbohydrates = _cursor.getFloat(_cursorIndexOfCarbohydrates);
              _tmpNutrition = new Nutrition(_tmpEnergy,_tmpProtein,_tmpFats,_tmpCarbohydrates);
            }  else  {
              _tmpNutrition = null;
            }
            _result = new FoodItem(_tmpName,_tmpDescription,_tmpNutrition,_tmpBarcode,_tmpServings,_tmpWeight,_tmpIsFavorite);
            final int _tmpFoodItemId;
            _tmpFoodItemId = _cursor.getInt(_cursorIndexOfFoodItemId);
            _result.setFoodItemId(_tmpFoodItemId);
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
